package ar.com.avaco.nitrophyl.ws.service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.sec.domain.Usuario;
import ar.com.avaco.nitrophyl.domain.entities.administracion.TipoDespacho;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.EstadoOrdenCompra;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.EstadoOrdenFabricacion;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.MaquinaFabrica;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompra;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenFabricacion;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenFabricacionEntrega;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.SectorFabrica;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.nitrophyl.domain.entities.molde.Molde;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaControl;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaPlano;
import ar.com.avaco.nitrophyl.domain.entities.pieza.cliente.Cotizacion;
import ar.com.avaco.nitrophyl.service.fabricacion.OrdenCompraService;
import ar.com.avaco.nitrophyl.service.fabricacion.OrdenFabricacionService;
import ar.com.avaco.nitrophyl.service.pieza.CotizacionService;
import ar.com.avaco.nitrophyl.service.pieza.PiezaControlService;
import ar.com.avaco.nitrophyl.ws.dto.CotizacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.CotizacionFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.ListadoOrdenFabricacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenFabricacionAsignacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenFabricacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenFabricacionEntregaDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenFabricacionFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.dto.ordenfabricacion.CabeceraOrdenTabajoDTO;
import ar.com.avaco.nitrophyl.ws.dto.ordenfabricacion.ControlCalidadDTO;
import ar.com.avaco.nitrophyl.ws.dto.ordenfabricacion.ItemOrdenTrabajoDTO;
import ar.com.avaco.nitrophyl.ws.dto.ordenfabricacion.OrdenTrabajoResponseDTO;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Transactional
@Service("ordenFabricacionEPService")
public class OrdenFabricacionEPServiceImpl
		extends CRUDAuditableEPBaseService<Long, OrdenFabricacionDTO, OrdenFabricacion, OrdenFabricacionService>
		implements OrdenFabricacionEPService {

	public OrdenFabricacionEPServiceImpl() {
		super(OrdenFabricacion.class, OrdenFabricacionDTO.class);
	}

	@Autowired
	private OrdenCompraService ordenCompraService;

	@Autowired
	private PiezaControlService piezaControlService;
	
	@Override
	@Resource(name = "ordenFabricacionService")
	protected void setService(OrdenFabricacionService service) {
		this.service = service;
	}

	@Override
	public PageDTO<ListadoOrdenFabricacionDTO> listFilterCount(OrdenFabricacionFilterDTO ordenFabricacionFilterDTO) {
		return this.service.listFilterCountCustom(ordenFabricacionFilterDTO);
	}

	@Override
	public void create(Long idOrdenCompra) {
		OrdenCompra ordenCompra = this.ordenCompraService.get(idOrdenCompra);
		this.service.generarOrdenes(ordenCompra.getDetalle());
	}

	@Override
	public void asignar(Long idOrdenFabricacion, OrdenFabricacionAsignacionDTO asignacion) {
		OrdenFabricacion ordenFabricacion = this.service.get(idOrdenFabricacion);
		if (asignacion.getIdMaquina() != null)
			ordenFabricacion.setMaquina(MaquinaFabrica.ofId(asignacion.getIdMaquina()));
		if (asignacion.getIdUsuario() != null)
			ordenFabricacion.setOperario(Usuario.ofId(asignacion.getIdUsuario()));
		if (asignacion.getIdSector() != null)
			ordenFabricacion.setSector(SectorFabrica.ofId(asignacion.getIdSector()));
		ordenFabricacion.setEstado(EstadoOrdenFabricacion.EN_PROCESO);
		this.service.update(ordenFabricacion);

		OrdenCompra ordenCompra = ordenFabricacion.getOrdenCompraDetalle().getOrdenCompraDetalle().getOrdenCompra();
		ordenCompra.setEstado(EstadoOrdenCompra.EN_PROCESO);
		this.ordenCompraService.update(ordenCompra);

	}

	private OrdenFabricacionEntrega generarEntrega(Long idOrdenFabricacion, OrdenFabricacionEntregaDTO entrega) {
		OrdenFabricacionEntrega ofe = new OrdenFabricacionEntrega();
		ofe.setCantidad(entrega.getCantidad());
		ofe.setFecha(entrega.getFecha());
		ofe.setLote(Lote.ofId(entrega.getIdLote()));
		ofe.setOperario(Usuario.ofId(entrega.getIdUsuario()));
		return ofe;
	}
	
	@Override
	public void registrarEntrega(Long idOrdenFabricacion, OrdenFabricacionEntregaDTO entrega) {

		// Obtengo la OF
		OrdenFabricacion ordenFabricacion = this.service.get(idOrdenFabricacion);
		
		// Genero la entrega
		OrdenFabricacionEntrega generarEntrega = generarEntrega(idOrdenFabricacion, entrega);
		
		// Le asocio la OF
		generarEntrega.setOrdenFabricacion(ordenFabricacion);
		
		// La agrego a la OF
		ordenFabricacion.getEntregas().add(generarEntrega);
		
		// Calculo el total de las entregas de la OF
		int total = ordenFabricacion.getEntregas().stream().mapToInt(OrdenFabricacionEntrega::getCantidad).sum();

		OrdenCompra ordenCompra = ordenFabricacion.getOrdenCompraDetalle().getOrdenCompraDetalle().getOrdenCompra();

		// Si esta completa, reviso si las otras ordenes de fabricacion asociadas a la orden de compra estan finalizadas
		if (total == ordenFabricacion.getOrdenCompraDetalle().getCantidad()) {
			// Seteo la orden de fabricacion como finalizada
			ordenFabricacion.setEstado(EstadoOrdenFabricacion.FINALIZADA);
			
			// Busco si todas las otras ordenes de fabricacion estan finalizadas
			
			if (!this.service.hayPendientes(idOrdenFabricacion)) {
				ordenCompra.setEstado(EstadoOrdenCompra.FABRICADA);
			} else {
				ordenCompra.setEstado(EstadoOrdenCompra.EN_PROCESO);
			}
			
		} else {
			ordenFabricacion.setEstado(EstadoOrdenFabricacion.EN_PROCESO);
		}

		this.service.update(ordenFabricacion);
		this.ordenCompraService.update(ordenCompra);
	}

	@Override
	public OrdenTrabajoResponseDTO generarOrdenTrabajo(Long idOrdenFabricacion) {
		
		
		OrdenFabricacion ordenFabricacion = this.service.get(idOrdenFabricacion);
		
		
		OrdenCompra oc = ordenFabricacion.getOrdenCompraDetalle().getOrdenCompraDetalle().getOrdenCompra();

		Cliente clienteOF = oc.getCliente();
		String cliente = clienteOF.getNombre();
		String fechaEmision = ordenFabricacion.getFecha().format(DateUtils.DATE_TIME_FORMATTER_DD_MM_YYYY);
		String fechaEntrega = ordenFabricacion.getOrdenCompraDetalle().getFechaEntregaSolicitada().format(DateUtils.DATE_TIME_FORMATTER_DD_MM_YYYY);
		String numeroOT = StringUtils.leftPad(ordenFabricacion.getNumero().toString(), 3, "0") + "/" + ordenFabricacion.getAnio();
		String observaciones = oc.getObservaciones();
		String ordenCompra = oc.getComprobante();
		String prensa = ordenFabricacion.getMaquina() != null ? ordenFabricacion.getMaquina().getNombre() : "";
		List<String> mediosEnvio = StringUtils.isNotBlank(oc.getMediosEnvio()) ? Arrays.asList(oc.getMediosEnvio().split(","))
						: new ArrayList<String>();
		CabeceraOrdenTabajoDTO cabecera = CabeceraOrdenTabajoDTO.builder()
				.cliente(cliente)
				.fechaEmision(fechaEmision)
				.fechaEntrega(fechaEntrega)
				.numeroOt(numeroOT)
				.observaciones(observaciones)
				.oc(ordenCompra)
				.prensa(prensa)
				.sector(ordenFabricacion.getSector().getNombre())				
				.telefonoCliente(clienteOF.getTelefono())
				.emailCliente(clienteOF.getEmail())
				.tipoDespacho(oc.getTipoDespacho())
				.empresaTransporte(oc.getEmpresaTransporte() != null ? oc.getEmpresaTransporte().getNombre() : null)
				.mediosEnvio(mediosEnvio)
				.domicilioEnvio(oc.getDomicilioEnvio() != null ? oc.getDomicilioEnvio().getDomicilio() : null)
				.build();
		
		Pieza piezaOC = ordenFabricacion.getOrdenCompraDetalle().getOrdenCompraDetalle().getPieza();

		List<PiezaControl> controlesConfigurados = piezaControlService.listControlesConfigurados(piezaOC.getId());
		
		List<ControlCalidadDTO> controles = new ArrayList<>();
		controlesConfigurados.stream().forEach(c -> {
			ControlCalidadDTO cc = ControlCalidadDTO.builder()
					.tipo(c.getTipo().getLabel())
					.valor(c.getControl())
					.build();
			controles.add(cc);
		});
		
		Molde moldePieza = piezaOC.getMoldes().iterator().next().getMolde();
		
		Integer cantidad = ordenFabricacion.getOrdenCompraDetalle().getCantidad();
		Formula formulaPieza = piezaOC.getDetalleFormula().getFormula();
		String formula = formulaPieza.getNombre();
		String hojaProceso = piezaOC.getProceso().getHojaProceso();
		String pieza = piezaOC.getDenominacion();
		String material = formulaPieza.getMaterial().getNombre();
		String molde = moldePieza.getCodigo();
		String postCura= piezaOC.getProceso().getPostCura();
		PiezaPlano piezaPlano = piezaOC.getPlanos().iterator().next();
		String plano = piezaPlano.getCodigo() + "/" + piezaPlano.getRevision();
		String ubicacion = moldePieza.getUbicacion();
		String identificacion = piezaOC.getProceso().getTerminacion().getIdentificacion();

		Cotizacion vigente = ordenFabricacion.getOrdenCompraDetalle().getOrdenCompraDetalle().getCotizacion();
		
		
		ItemOrdenTrabajoDTO item = ItemOrdenTrabajoDTO.builder()
				.identficacion(identificacion)
				.cantidadTotal(cantidad)
				.controlCalidad(controles)
				.formula(formula)
				.hp(hojaProceso)
				.idItem(1L)
				.material(material)
				.matriz(molde)
				.observacionesItem("")
				.pc(postCura)
				.planoRev(plano)
				.titulo(pieza)
				.ubicacion(ubicacion)
				.cotizacion(vigente.getValor())
				.fechaCotizacion(vigente.getFecha())
				.build();
		List<ItemOrdenTrabajoDTO> itemsOT = new ArrayList<ItemOrdenTrabajoDTO>();
		itemsOT.add(item);
		
		
		OrdenTrabajoResponseDTO ot = OrdenTrabajoResponseDTO.builder().cabecera(cabecera).items(itemsOT).build();
		
		return ot;
		
	}
}