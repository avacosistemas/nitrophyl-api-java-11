package ar.com.avaco.nitrophyl.ws.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.fabrica.Prensa;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.molde.Molde;
import ar.com.avaco.nitrophyl.domain.entities.molde.PlanoClasificacion;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaControl;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaDimension;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaEspesor;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaFormula;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaMolde;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaPlano;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaTipo;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Proceso;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Terminacion;
import ar.com.avaco.nitrophyl.domain.entities.pieza.cliente.Cotizacion;
import ar.com.avaco.nitrophyl.domain.entities.pieza.cliente.PiezaCliente;
import ar.com.avaco.nitrophyl.domain.entities.pieza.esquema.Esquema;
import ar.com.avaco.nitrophyl.domain.entities.pieza.esquema.EsquemaPaso;
import ar.com.avaco.nitrophyl.domain.entities.pieza.insumo.InsumoTratado;
import ar.com.avaco.nitrophyl.domain.entities.pieza.insumo.InsumoTratadoObservacionControl;
import ar.com.avaco.nitrophyl.domain.entities.pieza.moldeo.Bombeo;
import ar.com.avaco.nitrophyl.domain.entities.pieza.moldeo.Precalentamiento;
import ar.com.avaco.nitrophyl.domain.entities.pieza.moldeo.Vulcanizacion;
import ar.com.avaco.nitrophyl.service.cliente.ClienteService;
import ar.com.avaco.nitrophyl.service.formula.FormulaService;
import ar.com.avaco.nitrophyl.service.molde.MoldeService;
import ar.com.avaco.nitrophyl.service.pieza.CotizacionService;
import ar.com.avaco.nitrophyl.service.pieza.PiezaControlService;
import ar.com.avaco.nitrophyl.service.pieza.PiezaService;
import ar.com.avaco.nitrophyl.service.pieza.PiezaTipoService;
import ar.com.avaco.nitrophyl.ws.dto.BombeoDTO;
import ar.com.avaco.nitrophyl.ws.dto.EspesorDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaBaseDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaComboDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaCreacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaEdicionDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaGrillaDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaPUTDTO;
import ar.com.avaco.nitrophyl.ws.dto.PrensaDTO;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Transactional
@Service("piezaEPService")
public class PiezaEPServiceImpl extends CRUDAuditableEPBaseService<Long, PiezaDTO, Pieza, PiezaService>
		implements PiezaEPService {

	public PiezaEPServiceImpl() {
		super(Pieza.class, PiezaDTO.class);
	}

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private FormulaService formulaService;

	@Autowired
	private MoldeService moldeService;

	@Autowired
	private PiezaTipoService piezaTipoService;

	@Autowired
	private CotizacionService cotizacionService;

	@Autowired
	private PiezaControlService piezaControlService;

	@Override
	public PageDTO<PiezaGrillaDTO> listGrilla(PiezaFilterDTO filter) {
		List<PiezaGrillaDTO> listGrilla = this.service.listGrilla(filter);
		Integer rows = !listGrilla.isEmpty() ? listGrilla.get(0).getRows() : 0;
		return new PageDTO<PiezaGrillaDTO>(listGrilla, rows);
	}

	@Override
	public void marcarVigente(Long piezaId) {
		// Pieza a marcar
		Pieza pieza = this.service.get(piezaId);

		// Pieza vigente actual
		Pieza piezaVigente = this.service.getVigenteByCodigoInternoFormula(pieza.getCodigo(),
				pieza.getDetalleFormula().getFormula().getId());

		if (piezaVigente != null && pieza.getRevision() != piezaVigente.getRevision() + 1) {
			// Ocurrio un error, no puede setearse como vigente una revisin que no es la
			// posterior a la actual
			throw new ErrorValidationException(
					"No se puede marcar la pieza/proceso como vigente ya que no es la ultima");
		}
		if (piezaVigente != null) {
			piezaVigente.setVigente(false);
			this.service.update(piezaVigente);
		}

		pieza.setVigente(true);
		this.service.update(pieza);
	}

	@Override
	public void nuevaRevision(Long piezaId) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Date fechaHora = DateUtils.getFechaYHoraActual();
		Pieza pieza = this.service.get(piezaId);
		Pieza clonada = pieza.clonar(username, fechaHora);
		this.service.save(clonada);
	}

	@Override
	public PiezaCreacionDTO create(PiezaCreacionDTO dto) {

		// Obtengo fecha y hora actual
		Date fechaYHoraActual = DateUtils.getFechaYHoraActual();

		// Armo la pieza
		Pieza pieza = new Pieza();
		pieza.setCodigo(dto.getCodigo());
		pieza.setDenominacion(dto.getDenominacion());

		// Seteo la revision y fechas
		pieza.setRevision(dto.getRevisionIncial());
		pieza.setFechaRevision(fechaYHoraActual);
		pieza.setFechaCreacionPiezaProceso(fechaYHoraActual);
		pieza.setVigente(false);

		// Asocio la formula
		Formula formula = formulaService.get(dto.getIdFormula());
		PiezaFormula detalle = new PiezaFormula();
		detalle.setFormula(formula);

		// Los espesores
		if (dto.getEspesores().size() > 0) {
			dto.getEspesores().forEach(espesor -> {
				PiezaEspesor e = new PiezaEspesor();
				e.setEspesorMaximo(espesor.getMax());
				e.setEspesorMinimo(espesor.getMin());
				e.setPieza(pieza);
				pieza.getEspesores().add(e);
			});
		}

		// Peso crudo y observaciones
		detalle.setPesoCrudo(dto.getPesoCrudo());
		detalle.setObservacionesPesoCrudo(dto.getObservacionesPesoCrudo());

		pieza.setDetalleFormula(detalle);

		// Id null porque es nueva
		pieza.setId(null);

		// Busco el molde y lo asocio
		Molde molde = moldeService.get(dto.getIdMolde());
		PiezaMolde pm = new PiezaMolde();
		pm.setObservaciones(dto.getObservacionesMolde());
		pm.setMolde(molde);
		pm.setPieza(pieza);
		pieza.getMoldes().add(pm);

		// Si hay plano lo asocio
		if (dto.getPlanoArchivo() != null) {
			PiezaPlano plano = new PiezaPlano();
			plano.setArchivo(dto.getPlanoArchivo());
			plano.setClasificacion(PlanoClasificacion.valueOf(dto.getPlanoClasificacion()));
			plano.setCodigo(dto.getPlanoCodigo());
			plano.setObservaciones(dto.getPlanoObservaciones());
			plano.setRevision(dto.getPlanoRevision());
			plano.setPieza(pieza);
			pieza.getPlanos().add(plano);
		}

		// Si hay cliente lo asocio
		Cliente cliente = this.clienteService.get(dto.getIdCliente());
		PiezaCliente piezaCliente = new PiezaCliente();
		piezaCliente.setCliente(cliente);
		piezaCliente.setNombrePiezaPersonalizado(dto.getNombrePiezaCliente());
		piezaCliente.setPieza(pieza);
		pieza.getClientes().add(piezaCliente);

		// Armo el proceso y le asocio la hoja
		Proceso proceso = new Proceso();
		proceso.setHojaProceso(dto.getHojaProceso());

		// Asocio la terminacion
		Terminacion terminacion = new Terminacion();
		terminacion.setProceso(proceso);
		proceso.setTerminacion(terminacion);
		pieza.setProceso(proceso);
		proceso.setPieza(pieza);

		// Asocio los tipos de pieza
		PiezaTipo tipo = this.piezaTipoService.get(dto.getIdTipoPieza());
		pieza.setTipo(tipo);

		pieza.setRequiereInsumos(true);
		pieza.setCantidadInsumos(0);

		// Guardo la pieza
		this.service.save(pieza);

		// Si tengo una cotizacion del cliente la creo y asocio
		if (dto.getCotizacionCliente() != null && dto.getCotizacionFecha() != null) {
			Cotizacion c = new Cotizacion();
			c.setFecha(DateUtils.toDate(dto.getCotizacionFecha(), "dd/MM/yyyy"));
			c.setObservaciones(dto.getObservacionesCotizacionCliente());
			c.setPiezaCliente(piezaCliente);
			c.setValor(dto.getCotizacionCliente());
			this.cotizacionService.save(c);
		}

		dto.setId(pieza.getId());
		return dto;
	}

	@Override
	protected Pieza convertToEntity(PiezaDTO dto) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	protected PiezaDTO convertToDto(Pieza entity) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	@Resource(name = "piezaService")
	protected void setService(PiezaService service) {
		this.service = service;
	}

	@Override
	public PiezaEdicionDTO getByIdEdicion(Long idPieza) {
		Pieza pieza = this.service.get(idPieza);

		PiezaEdicionDTO dto = new PiezaEdicionDTO();
		dto.setId(pieza.getId());
		dto.setDenominacion(pieza.getDenominacion());
		dto.setTipo(pieza.getTipo().getNombre());
		dto.setCodigo(pieza.getCodigo());
		dto.setNombreFormula(pieza.getDetalleFormula().getFormula().getNombre());

		pieza.getEspesores().forEach(espesor -> {
			EspesorDTO e = new EspesorDTO();
			e.setId(espesor.getId());
			e.setMax(espesor.getEspesorMaximo());
			e.setMin(espesor.getEspesorMinimo());
			dto.getEspesores().add(e);
		});

		dto.setPesoCrudo(pieza.getDetalleFormula().getPesoCrudo());
		dto.setObservacionesPesoCrudo(pieza.getDetalleFormula().getObservacionesPesoCrudo());
		dto.setRevision(pieza.getRevision());
		dto.setFechaRevision(pieza.getFechaRevision());
		dto.setVigente(pieza.getVigente());
		dto.setFechaCreacionPiezaProceso(pieza.getFechaCreacionPiezaProceso());
		dto.setObservacionesRevision(pieza.getObservacionesRevision());

		dto.setRequiereInsumos(pieza.getRequiereInsumos());
		dto.setCantidadInsumos(pieza.getCantidadInsumos());

		Precalentamiento precalentamiento = pieza.getProceso().getPrecalentamiento();
		if (precalentamiento != null) {
			dto.setPrecalentamientoUnidad(precalentamiento.getUnidad());
			dto.setPrecalentamientoValor(pieza.getProceso().getPrecalentamiento().getValor());
		}

		Set<Prensa> prensas = pieza.getProceso().getPrensas();
		if (prensas != null && !prensas.isEmpty())
			prensas.forEach(prensa -> dto.getPrensas().add(super.modelMapper.map(prensa, PrensaDTO.class)));

		Vulcanizacion vulcanizacion = pieza.getProceso().getVulcanizacion();
		if (vulcanizacion != null) {
			dto.setVulcanizacionTemperaturaMin(vulcanizacion.getTemperaturaMin());
			dto.setVulcanizacionTemperaturaMax(pieza.getProceso().getVulcanizacion().getTemperaturaMax());
			dto.setVulcanizacionTiempo(pieza.getProceso().getVulcanizacion().getTiempo());
		}

		Set<Bombeo> bombeos = pieza.getProceso().getBombeos();
		if (bombeos != null && !bombeos.isEmpty())
			bombeos.forEach(bombeo -> dto.getBombeos().add(modelMapper.map(bombeo, BombeoDTO.class)));

		dto.setDesmoldante(pieza.getProceso().getDesmoldante());
		dto.setPostCura(pieza.getProceso().getPostCura());

		dto.setHojaProceso(pieza.getProceso().getHojaProceso());

		return dto;

	}

	@Override
	public void update(Long idPieza, PiezaPUTDTO piezaFormula) {

		Pieza pieza = this.service.get(idPieza);

		Set<PiezaEspesor> espesoresActualizado = new HashSet<>();

		piezaFormula.getEspesores().forEach(espesordto -> {
			Optional<PiezaEspesor> espesor = pieza.getEspesores().stream()
					.filter(x -> x.getId().equals(espesordto.getId())).findFirst();
			PiezaEspesor piezaEspesor;
			if (espesor.isPresent()) {
				piezaEspesor = espesor.get();
			} else {
				piezaEspesor = new PiezaEspesor();
				piezaEspesor.setPieza(pieza);
			}
			piezaEspesor.setEspesorMaximo(espesordto.getMax());
			piezaEspesor.setEspesorMinimo(espesordto.getMin());
			espesoresActualizado.add(piezaEspesor);
		});

		pieza.getEspesores().clear();
		pieza.getEspesores().addAll(espesoresActualizado);

		pieza.getDetalleFormula().setObservacionesPesoCrudo(piezaFormula.getObservacionesPesoCrudo());
		pieza.getDetalleFormula().setPesoCrudo(piezaFormula.getPesoCrudo());
		pieza.setObservacionesRevision(piezaFormula.getObservacionesRevision());
		pieza.getProceso().setHojaProceso(piezaFormula.getHojaProceso());
		this.service.update(pieza);
	}

	@Override
	public List<PiezaComboDTO> listCombo(String nombre, Long idCliente) {
		return this.service.listCombo(nombre, idCliente);
	}

	@Override
	public void copiar(PiezaBaseDTO dto) throws BusinessException {

		String codigo = dto.getCodigo();
		Long idFormula = dto.getIdFormula();

		boolean existe = this.service.existsByCodigoAndDetalleFormulaFormulaId(codigo, idFormula);

		if (existe)
			throw new BusinessException("Ya existe una pieza con ese código y material");

		// Obtengo la pieza base
		Pieza piezaBase = this.service.get(dto.getIdPiezaOriginal());
		Proceso procesoBase = piezaBase.getProceso();

		// Armo la pieza nueva
		Pieza pieza = new Pieza();
		pieza.setCodigo(dto.getCodigo());
		pieza.setDenominacion(dto.getNombre());
		pieza.setTipo(piezaBase.getTipo());

		pieza.setRevision(dto.getRevisionInicial());
		pieza.setFechaRevision(DateUtils.getFechaYHoraActual());
		pieza.setVigente(false);
		
		pieza.setFechaCreacionPiezaProceso(DateUtils.getFechaYHoraActual());
		
		// Obtengo la formula y armo pieza formula
		Formula formula = formulaService.get(idFormula);
		PiezaFormula pf = new PiezaFormula();
		pf.setFormula(formula);

		// Chequeo de espesor
		if (dto.isEspesoresPesoCrudo()) {
			pf.setPesoCrudo(piezaBase.getDetalleFormula().getPesoCrudo());

			if (piezaBase.getEspesores() != null) {

				for (PiezaEspesor pe : piezaBase.getEspesores()) {
					PiezaEspesor npe = new PiezaEspesor();
					npe.setEspesorMaximo(pe.getEspesorMaximo());
					npe.setEspesorMinimo(pe.getEspesorMinimo());
					npe.setPieza(pieza);
					pieza.getEspesores().add(npe);
				}
			}
		}

		pieza.setDetalleFormula(pf);
		
		Cliente cliente = clienteService.get(dto.getIdCliente());
		PiezaCliente pc = new PiezaCliente();
		pc.setCliente(cliente);
		pc.setPieza(pieza);

		pieza.getClientes().add(pc);

		Proceso proceso = new Proceso();
		proceso.setPieza(pieza);
		proceso.setHojaProceso(dto.getHojaProceso());

		// Moldes
		if (dto.isMoldes() && piezaBase.getMoldes() != null) {
			for (PiezaMolde pmbase : piezaBase.getMoldes()) {
				PiezaMolde piezaMolde = new PiezaMolde();
				piezaMolde.setMolde(pmbase.getMolde());
				piezaMolde.setObservaciones(pmbase.getObservaciones());
				piezaMolde.setPieza(pieza);
				pieza.getMoldes().add(piezaMolde);
			}
		}

		// Insumos

		if (dto.isInsumos() && piezaBase.getInsumos() != null) {

			pieza.setCantidadInsumos(piezaBase.getCantidadInsumos());
			pieza.setRequiereInsumos(piezaBase.getRequiereInsumos());
			
			for (InsumoTratado itbase : piezaBase.getInsumos()) {

				InsumoTratado it = new InsumoTratado();
				it.setInsumo(itbase.getInsumo());

				it.getAdhesivos().addAll(itbase.getAdhesivos());

				it.setMedida1(itbase.getMedida1());
				it.setMedida2(itbase.getMedida2());

				for (InsumoTratadoObservacionControl itcbase : itbase.getObservaciones()) {
					InsumoTratadoObservacionControl itc = new InsumoTratadoObservacionControl();
					itc.setControlar(itcbase.getControlar());
					itc.setInsumoTratado(it);
					itc.setObservacion(itcbase.getObservacion());
					it.getObservaciones().add(itc);
				}

				it.setPieza(pieza);
				it.getTratamientos().addAll(itbase.getTratamientos());
				it.setUnidades(itbase.getUnidades());
				it.setUnidadMedida(itbase.getUnidadMedida());
				it.setUnidadMedidaLongitud(itbase.getUnidadMedidaLongitud());

				pieza.getInsumos().add(it);
			}
		}

		// Molde
		// FIXME luego cambiarlo por maquina fabrica
		if (dto.isMoldeo()) {

			proceso.getPrensas().addAll(procesoBase.getPrensas());
			Precalentamiento precalentamientoBase = procesoBase.getPrecalentamiento();
			if (precalentamientoBase != null) {
				Precalentamiento preca = new Precalentamiento();
				preca.setUnidad(precalentamientoBase.getUnidad());
				preca.setValor(precalentamientoBase.getValor());
				proceso.setPrecalentamiento(preca);
			}

			Vulcanizacion vulcanizacionBase = procesoBase.getVulcanizacion();
			if (vulcanizacionBase != null) {
				Vulcanizacion v = new Vulcanizacion();
				v.setTemperaturaMax(vulcanizacionBase.getTemperaturaMax());
				v.setTemperaturaMin(vulcanizacionBase.getTemperaturaMin());
				v.setTiempo(vulcanizacionBase.getTiempo());
				proceso.setVulcanizacion(vulcanizacionBase);
			}

			Set<Bombeo> bombeosBase = procesoBase.getBombeos();
			if (bombeosBase != null && !bombeosBase.isEmpty()) {
				for (Bombeo bb : bombeosBase) {
					Bombeo b = new Bombeo();
					b.setCantidad(bb.getCantidad());
					b.setPresion(bb.getPresion());
					b.setProceso(proceso);
					b.setTipo(bb.getTipo());
					proceso.getBombeos().add(b);
				}
			}

		}

		// Desmoltante postcura
		if (dto.isDesmoldantePostcura()) {
			proceso.setDesmoldante(procesoBase.getDesmoldante());
			proceso.setPostCura(procesoBase.getPostCura());
		}

		// Esquema
		if (dto.isEsquema() && procesoBase.getEsquema() != null) {
			for (Esquema esquemaBase : procesoBase.getEsquema()) {
				Esquema esquema = new Esquema();
				esquema.setImagen(esquemaBase.getImagen());
				esquema.setPosicion(esquemaBase.getPosicion());
				esquema.setProceso(proceso);
				esquema.setTitulo(esquemaBase.getTitulo());

				if (esquemaBase.getPasos() != null && !esquemaBase.getPasos().isEmpty()) {
					for (EsquemaPaso esquemaPasoBase : esquemaBase.getPasos()) {
						EsquemaPaso esquemaPaso = new EsquemaPaso();
						esquemaPaso.setDescripcion(esquemaPasoBase.getDescripcion());
						esquemaPaso.setEsquema(esquema);
						esquemaPaso.setPaso(esquemaPasoBase.getPaso());
						esquemaPaso.setPosicion(esquemaPasoBase.getPosicion());
						esquema.getPasos().add(esquemaPaso);
					}
				}

				proceso.getEsquema().add(esquema);
			}
		}

		// Pieza terminada

		if (dto.isPiezaTerminada() && procesoBase.getTerminacion() != null) {
			Terminacion terminacion = new Terminacion();
			terminacion.setEmbalaje(procesoBase.getTerminacion().getEmbalaje());
			terminacion.setIdentificacion(procesoBase.getTerminacion().getIdentificacion());
			terminacion.setImagenTerminada(procesoBase.getTerminacion().getImagenTerminada());
			terminacion.setProceso(proceso);
			terminacion.setRefilado(procesoBase.getTerminacion().getRefilado());
			proceso.setTerminacion(terminacion);
		}

		// Planos
		if (dto.isPlanos() && piezaBase.getPlanos() != null) {
			for (PiezaPlano planoBase : piezaBase.getPlanos()) {
				PiezaPlano pp = new PiezaPlano();
				pp.setArchivo(planoBase.getArchivo());
				pp.setClasificacion(planoBase.getClasificacion());
				pp.setCodigo(planoBase.getCodigo());
				pp.setObservaciones(planoBase.getObservaciones());
				pp.setPieza(pieza);
				pp.setRevision(planoBase.getRevision());
				pieza.getPlanos().add(pp);
			}
		}

		if (dto.isDimensiones() && piezaBase.getDimensiones() != null) {
			for (PiezaDimension pdBase : piezaBase.getDimensiones()) {
				PiezaDimension pd = new PiezaDimension();
				pd.setControlar(pdBase.getControlar());
				pd.setMaximo(pdBase.getMaximo());
				pd.setMinimo(pdBase.getMinimo());
				pd.setObservaciones(pdBase.getObservaciones());
				pd.setPieza(pieza);
				pd.setTipo(pdBase.getTipo());
				pd.setValor(pdBase.getValor());
				pieza.getDimensiones().add(pd);
			}
		}
		
		pieza.setProceso(proceso);

		pieza = this.service.save(pieza);
		
		// Por ultimo luego de pesistir pieza cliente
		if (dto.getCotizacion() != null) {
			Cotizacion c = new Cotizacion();
			c.setFecha(DateUtils.toDate(dto.getFechaCotizacion()));
			c.setObservaciones(dto.getObservacionesCotizacion());
			c.setPiezaCliente(pc);
			c.setValor(dto.getCotizacion());
			this.cotizacionService.save(c);
		}

		// Grabar luego de grabar la pieza
		List<PiezaControl> controlesBase = piezaControlService.listControlesConfigurados(piezaBase.getId());
		List<PiezaControl> controles = new ArrayList<PiezaControl>();
		if (dto.isControles() && controlesBase != null) {
			for (PiezaControl pcBase : controlesBase) {
				PiezaControl piezaControl = new PiezaControl();
				piezaControl.setControl(pcBase.getControl());
				piezaControl.setPieza(pieza);
				piezaControl.setTipo(pcBase.getTipo());
				controles.add(piezaControl);
			}
			this.piezaControlService.save(controles);
		}

	}

}
