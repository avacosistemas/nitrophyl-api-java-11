package ar.com.avaco.nitrophyl.ws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.molde.EstadoBoca;
import ar.com.avaco.nitrophyl.domain.entities.molde.EstadoMolde;
import ar.com.avaco.nitrophyl.domain.entities.molde.Molde;
import ar.com.avaco.nitrophyl.domain.entities.molde.MoldeBoca;
import ar.com.avaco.nitrophyl.domain.entities.molde.MoldeCliente;
import ar.com.avaco.nitrophyl.domain.entities.molde.MoldeClienteId;
import ar.com.avaco.nitrophyl.domain.entities.molde.MoldeDimension;
import ar.com.avaco.nitrophyl.domain.entities.molde.MoldeFoto;
import ar.com.avaco.nitrophyl.domain.entities.molde.MoldeObservacion;
import ar.com.avaco.nitrophyl.domain.entities.molde.MoldePlano;
import ar.com.avaco.nitrophyl.domain.entities.molde.MoldeRegistro;
import ar.com.avaco.nitrophyl.domain.entities.molde.PlanoClasificacion;
import ar.com.avaco.nitrophyl.domain.entities.molde.TipoDimension;
import ar.com.avaco.nitrophyl.domain.entities.molde.TipoMolde;
import ar.com.avaco.nitrophyl.domain.entities.molde.TipoRegistroMolde;
import ar.com.avaco.nitrophyl.service.cliente.ClienteService;
import ar.com.avaco.nitrophyl.service.molde.MoldeBocaService;
import ar.com.avaco.nitrophyl.service.molde.MoldeDimensionService;
import ar.com.avaco.nitrophyl.service.molde.MoldeFotoService;
import ar.com.avaco.nitrophyl.service.molde.MoldeObservacionService;
import ar.com.avaco.nitrophyl.service.molde.MoldePlanoService;
import ar.com.avaco.nitrophyl.service.molde.MoldeRegistroService;
import ar.com.avaco.nitrophyl.service.molde.MoldeService;
import ar.com.avaco.nitrophyl.service.pieza.PiezaTipoService;
import ar.com.avaco.nitrophyl.ws.dto.MoldeClienteDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeDimensionListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeFotoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeFotoListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeObservacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldePlanoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldePlanoListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeRegistroDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaTipoDTO;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Transactional
@Service("moldeEPService")
public class MoldeEPServiceImpl extends CRUDAuditableEPBaseService<Long, MoldeDTO, Molde, MoldeService>
		implements MoldeEPService {

	public MoldeEPServiceImpl() {
		super(Molde.class, MoldeDTO.class);
	}

	@Resource(name = "moldeDimensionService")
	private MoldeDimensionService moldeDimensionService;

	@Resource(name = "moldeObservacionService")
	private MoldeObservacionService moldeObservacionService;

	@Resource(name = "moldeBocaService")
	private MoldeBocaService moldeBocaService;

	@Resource(name = "moldeRegistroService")
	private MoldeRegistroService moldeRegistroService;

	@Resource(name = "moldePlanoService")
	private MoldePlanoService moldePlanoService;

	@Resource(name = "moldeFotoService")
	private MoldeFotoService moldeFotoService;

	@Resource(name = "clienteService")
	private ClienteService clienteService;

	@Resource(name = "piezaTipoService")
	private PiezaTipoService piezaTipoService;

	@Override
	public MoldeDTO update(MoldeDTO dto) throws BusinessException {
		String estadoAnterior = this.get(dto.getId()).getEstado();
		String observacionesEstado = dto.getObservacionesEstado();
		MoldeDTO update = super.update(dto);
		if (!update.getEstado().equals(estadoAnterior)) {
			MoldeObservacion observacion = new MoldeObservacion();
			observacion.setFechaActualizacion(DateUtils.getFechaYHoraActual());
			observacion.setFechaCreacion(DateUtils.getFechaYHoraActual());
			observacion.setIdMolde(dto.getId());
			observacion.setObservacion(estadoAnterior + " -> " + update.getEstado() + ": " + observacionesEstado);
			observacion.setUsuarioActualizacion(SecurityContextHolder.getContext().getAuthentication().getName());
			observacion.setUsuarioCreacion(SecurityContextHolder.getContext().getAuthentication().getName());
			moldeObservacionService.save(observacion);
		}
		return update;
	}

	@Override
	protected Molde convertToEntityForSave(MoldeDTO dto) {
		Molde molde = super.convertToEntity(dto);
		molde.setCodigo(dto.getCodigo());
		molde.setPropio(dto.isPropio());
		molde.setEstado(EstadoMolde.valueOf(dto.getEstado()));
		molde.setNombre(dto.getNombre());
		molde.setObservaciones(dto.getObservaciones());
		molde.setUbicacion(dto.getUbicacion());
		molde.setCantidadBocas(dto.getCantidadBocas());
		molde.setTipoMolde(dto.getTipoMolde());
		Cliente duenio = clienteService.get(dto.getIdClienteDuenio());

		if (!dto.isPropio()) {
			molde.setDuenio(duenio);
		}

		molde.getTiposPieza().clear();
		dto.getPiezaTipos().forEach(x -> molde.getTiposPieza().add(piezaTipoService.get(x.getId())));

		return molde;
	}

	private void setearDimensiones(MoldeDTO dto, Molde molde) {
		molde.getDimensiones().clear();
		if (dto.getTipoMolde().equals(TipoMolde.CIRCULAR)) {
			MoldeDimension alto = new MoldeDimension(molde, TipoDimension.ALTO, dto.getAlto());
			MoldeDimension diametro = new MoldeDimension(molde, TipoDimension.DIAMETRO, dto.getDiametro());
			molde.getDimensiones().add(alto);
			molde.getDimensiones().add(diametro);
		} else {
			MoldeDimension alto = new MoldeDimension(molde, TipoDimension.ALTO, dto.getAlto());
			MoldeDimension ancho = new MoldeDimension(molde, TipoDimension.ANCHO, dto.getAncho());
			MoldeDimension profundidad = new MoldeDimension(molde, TipoDimension.PROFUNDIDAD, dto.getProfundidad());
			molde.getDimensiones().add(alto);
			molde.getDimensiones().add(ancho);
			molde.getDimensiones().add(profundidad);
		}
	}

	@Override
	protected Molde convertToEntityForUpdate(MoldeDTO dto) {
		Molde molde = this.service.get(dto.getId());
		molde.setCodigo(dto.getCodigo());
		molde.setPropio(dto.isPropio());
		molde.setEstado(EstadoMolde.valueOf(dto.getEstado()));
		molde.setId(dto.getId());
		molde.setNombre(dto.getNombre());
		molde.setObservaciones(dto.getObservaciones());
		molde.setUbicacion(dto.getUbicacion());
		molde.setTipoMolde(dto.getTipoMolde());
		
		Cliente duenio = null;
		if (dto.getIdClienteDuenio() != null) {
			duenio = clienteService.get(dto.getIdClienteDuenio());
		} 
		
		molde.setDuenio(duenio);

		setearDimensiones(dto, molde);
		
		molde.getTiposPieza().clear();
		dto.getPiezaTipos().forEach(x -> molde.getTiposPieza().add(piezaTipoService.get(x.getId())));

		return molde;
	}

	@Override
	protected MoldeDTO convertToDto(Molde entity) {
		MoldeDTO moldeDto = super.convertToDto(entity);
		moldeDto.setCodigo(entity.getCodigo());
		moldeDto.setEstado(entity.getEstado().toString());
		moldeDto.setPropio(entity.isPropio());
		moldeDto.setId(entity.getId());
		moldeDto.setNombre(entity.getNombre());
		moldeDto.setObservaciones(entity.getObservaciones());
		moldeDto.setUbicacion(entity.getUbicacion());
		moldeDto.setCantidadBocas(entity.getCantidadBocas());
		if (!entity.isPropio()) {
			moldeDto.setClienteDuenio(entity.getDuenio().getNombre());
			moldeDto.setIdClienteDuenio(entity.getDuenio().getId());
		}
		
		Map<TipoDimension, Integer> dimensiones = entity.getDimensiones().stream().collect(Collectors.toMap(MoldeDimension::getTipodimension, MoldeDimension::getValordimension));
		
		moldeDto.setAlto(dimensiones.get(TipoDimension.ALTO));
		moldeDto.setAncho(dimensiones.get(TipoDimension.ANCHO));
		moldeDto.setProfundidad(dimensiones.get(TipoDimension.PROFUNDIDAD));
		moldeDto.setDiametro(dimensiones.get(TipoDimension.DIAMETRO));
		
		entity.getTiposPieza().forEach(x -> moldeDto.getPiezaTipos().add(new PiezaTipoDTO(x)));
		return moldeDto;
	}

	@Override
	@Resource(name = "moldeService")
	protected void setService(MoldeService service) {
		this.service = service;
	}

	@Override
	public List<MoldeDimensionListadoDTO> getMoldesDimension(Long idMolde) {
		List<MoldeDimension> listado = moldeDimensionService.getByMolde(idMolde);
		if (listado != null) {
			List<MoldeDimensionListadoDTO> returnedListado = new ArrayList<MoldeDimensionListadoDTO>();
			for (MoldeDimension moldeDimension : listado) {
				MoldeDimensionListadoDTO dto = new MoldeDimensionListadoDTO();
				dto.setTipoDimension(moldeDimension.getTipodimension());
				dto.setValor(moldeDimension.getValordimension());
				returnedListado.add(dto);
			}
			return returnedListado;
		}
		return null;
	}

	@Override
	public List<MoldeDimensionListadoDTO> updateMoldeDimensiones(Long idMolde,
			List<MoldeDimensionListadoDTO> moldeDimensionListadoDTOs) {

		if (moldeDimensionListadoDTOs != null) {
			List<MoldeDimension> listado = new ArrayList<MoldeDimension>();

			moldeDimensionService.removeByMolde(idMolde);

			for (MoldeDimensionListadoDTO dto : moldeDimensionListadoDTOs) {
				MoldeDimension moldeDimension = new MoldeDimension();
				moldeDimension.setIdMolde(idMolde);
				moldeDimension.setTipodimension(dto.getTipoDimension());
				moldeDimension.setValordimension(dto.getValor());
				listado.add(moldeDimension);
			}

			this.moldeDimensionService.save(listado);
			return moldeDimensionListadoDTOs;
		}
		return null;
	}

	@Override
	public List<MoldeRegistroDTO> getMoldesRegistro(Long idMolde) {
		List<MoldeRegistro> list = moldeRegistroService.listByMoldeId(idMolde);
		List<MoldeRegistroDTO> ret = new ArrayList<>();
		list.forEach(mr -> ret.add(new MoldeRegistroDTO(mr)));
		return ret;
	}

	@Override
	public MoldeRegistroDTO saveMoldeRegistro(MoldeRegistroDTO moldeRegistroDTO) {
		MoldeRegistro mr = moldeRegistroService.getUltimoRegistro(moldeRegistroDTO.getIdMolde());
		MoldeRegistro nmr;
		if (mr == null || (mr != null && mr.getTipoRegistro().equals(TipoRegistroMolde.EGRESO))) {
			nmr = moldeRegistroService.registrarIngreso(moldeRegistroDTO.getComentarios(),
					moldeRegistroDTO.getIdMolde());
		} else {
			nmr = moldeRegistroService.registrarEgreso(moldeRegistroDTO.getComentarios(),
					moldeRegistroDTO.getIdMolde());
		}
		return new MoldeRegistroDTO(nmr);
	}

	@Override
	public List<MoldeFotoListadoDTO> getMoldesFoto(Long idMolde) {
		List<MoldeFoto> list = moldeFotoService.listByMoldeId(idMolde);
		List<MoldeFotoListadoDTO> ret = new ArrayList<>();
		list.forEach(mp -> ret.add(new MoldeFotoListadoDTO(mp)));
		return ret;
	}

	@Override
	public MoldePlanoDTO addMoldePlano(MoldePlanoDTO moldePlanoDTO) throws ErrorValidationException, BusinessException {

		if (moldePlanoDTO != null) {
			MoldePlano mp = new MoldePlano();
			mp.setIdMolde(moldePlanoDTO.getIdMolde());
			mp.setNombreArchivo(moldePlanoDTO.getNombreArchivo());
			mp.setArchivo(moldePlanoDTO.getArchivo());
			mp.setFecha(DateUtils.getFechaYHoraActual());
			mp.setDescripcion(moldePlanoDTO.getDescripcion());
			mp.setVersion(1);
			mp.setClasificacion(PlanoClasificacion.valueOf(moldePlanoDTO.getClasificacion()));
			MoldePlano nmp = this.moldePlanoService.addMoldePlano(mp);
			return new MoldePlanoDTO(nmp);
		}
		return null;
	}

	@Override
	public MoldePlanoDTO downloadMoldePlano(Long idMoldePlano) {
		MoldePlano moldePlano = moldePlanoService.get(idMoldePlano);

		MoldePlanoDTO moldePlanoDTO = new MoldePlanoDTO();
		moldePlanoDTO.setIdMolde(idMoldePlano);
		moldePlanoDTO.setNombreArchivo(moldePlano.getNombreArchivo());
		moldePlanoDTO.setArchivo(moldePlano.getArchivo());

		return moldePlanoDTO;
	}

	@Override
	public List<MoldePlanoListadoDTO> getMoldesPlano(Long idMolde) {
		List<MoldePlano> list = moldePlanoService.listByMoldeId(idMolde);
		List<MoldePlanoListadoDTO> ret = new ArrayList<>();
		list.forEach(mp -> ret.add(new MoldePlanoListadoDTO(mp)));
		return ret;
	}

	@Override
	public MoldeFotoDTO addMoldeFoto(MoldeFotoDTO moldeFotoDTO) throws ErrorValidationException, BusinessException {

		if (moldeFotoDTO != null) {
			MoldeFoto mf = new MoldeFoto();
			mf.setIdMolde(moldeFotoDTO.getIdMolde());
			mf.setNombreArchivo(moldeFotoDTO.getNombreArchivo());
			mf.setArchivo(moldeFotoDTO.getArchivo());
			mf.setFecha(DateUtils.getFechaYHoraActual());
			mf.setDescripcion(moldeFotoDTO.getDescripcion());
			mf.setVersion(1);

			MoldeFoto nmf = this.moldeFotoService.addMoldeFoto(mf);
			return new MoldeFotoDTO(nmf);
		}
		return null;
	}

	@Override
	public MoldeFotoDTO downloadMoldeFoto(Long idMoldeFoto) {
		MoldeFoto moldeFoto = moldeFotoService.get(idMoldeFoto);

		MoldeFotoDTO moldeFotoDTO = new MoldeFotoDTO();
		moldeFotoDTO.setIdMolde(idMoldeFoto);
		moldeFotoDTO.setNombreArchivo(moldeFoto.getNombreArchivo());
		moldeFotoDTO.setArchivo(moldeFoto.getArchivo());

		return moldeFotoDTO;
	}

	@Override
	public List<MoldeClienteDTO> getMoldeClientes(Long idMolde) {
		Molde m = this.service.get(idMolde);
		List<MoldeClienteDTO> clientes = new ArrayList<>();
		m.getClientes().forEach(x -> {
			clientes.add(new MoldeClienteDTO(x.getCliente(), m, x.getObservaciones()));
		});
		return clientes;
	}

	@Override
	public List<MoldeClienteDTO> updateMoldeClientes(Long idMolde, List<MoldeClienteDTO> moldeClientesListadoDTOs) {
		Molde m = this.service.get(idMolde);
		List<Cliente> clientes = this.clienteService.getByIds(
				moldeClientesListadoDTOs.stream().map(MoldeClienteDTO::getIdCliente).collect(Collectors.toList()));
		m.getClientes().clear();

		moldeClientesListadoDTOs.forEach(mcdto -> {
			Cliente c = clientes.stream().filter(x -> x.getId().equals(mcdto.getIdCliente())).findAny().get();
			MoldeCliente mc = new MoldeCliente(m, c);
			mc.setObservaciones(mcdto.getObservaciones());
			m.getClientes().add(mc);
		});
		this.service.update(m);
		return getMoldeClientes(idMolde);
	}

	@Override
	public List<MoldeObservacionDTO> getMoldeObservaciones(Long idMolde) {
		List<MoldeObservacion> observaciones = this.moldeObservacionService.listByMoldeId(idMolde);
		List<MoldeObservacionDTO> list = new ArrayList<>();
		observaciones.forEach(obs -> list.add(new MoldeObservacionDTO(obs)));
		return list;
	}

	@Override
	public MoldeObservacionDTO addMoldeObservacion(MoldeObservacionDTO dto) {
		MoldeObservacion mo = new MoldeObservacion();
		mo.setFechaCreacion(DateUtils.getFechaYHoraActual());
		mo.setUsuarioCreacion(SecurityContextHolder.getContext().getAuthentication().getName());
		mo.setIdMolde(dto.getIdMolde());
		mo.setObservacion(dto.getObservacion());
		MoldeObservacion save = this.moldeObservacionService.save(mo);
		dto.setId(save.getId());
		return dto;
	}

	@Override
	public PageDTO<MoldeListadoDTO> list(MoldeFilterDTO filter) {
		List<MoldeListadoDTO> list = this.service.list(filter);
		int cantidad = list.size() == 0 ? 0 : list.get(0).getTotalRows();
		PageDTO<MoldeListadoDTO> pagedto = new PageDTO<>();
		pagedto.setPage(list);
		pagedto.setTotalReg(cantidad);
		return pagedto;
	}

	@Resource(name = "piezaTipoService")
	public void setPiezaTipoService(PiezaTipoService piezaTipoService) {
		this.piezaTipoService = piezaTipoService;
	}

	@Override
	public MoldeDTO save(MoldeDTO dto) throws BusinessException {

	    // 1. Crear y persistir el Molde
	    Molde entity = convertToEntityForSave(dto);
	    entity = service.save(entity); // ahora entity.getId() EXISTE y está MANAGED

	    // 2. Crear relación MoldeCliente CORRECTAMENTE
	    Cliente cliente = clienteService.get(dto.getIdClienteDuenio());

	    MoldeCliente mc = new MoldeCliente();
	    mc.setId(new MoldeClienteId(entity.getId(), cliente.getId()));
	    mc.setMolde(entity);
	    mc.setCliente(cliente);

	    entity.getClientes().add(mc);

	    // 3. Dimensiones
	    setearDimensiones(dto, entity);

	    // 4. Crear bocas SIN IDs manuales
	    entity.getBocas().clear();

	    for (int i = 1; i <= entity.getCantidadBocas(); i++) {
	        MoldeBoca mb = new MoldeBoca();
	        mb.setEstado(EstadoBoca.ACTIVO);
	        mb.setMolde(entity); // SOLO esto
	        mb.setNroBoca(i);
	        mb.setUsuarioCreacion(
	            SecurityContextHolder.getContext().getAuthentication().getName()
	        );
	        mb.setFechaCreacion(DateUtils.getFechaYHoraActual());

	        entity.getBocas().add(mb);
	    }

	    // 5. Guardar todo por cascada
	    service.save(entity);

	    // 6. Registro
	    MoldeRegistro registro = new MoldeRegistro();
	    registro.setFecha(DateUtils.getFechaYHoraActual());
	    registro.setIdMolde(entity.getId());
	    registro.setTipoRegistro(TipoRegistroMolde.INGRESO);
	    moldeRegistroService.save(registro);

	    return convertToDto(entity);
	}


}
