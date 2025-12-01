package ar.com.avaco.nitrophyl.ws.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Cotizacion;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaCliente;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaFormula;
import ar.com.avaco.nitrophyl.service.pieza.CotizacionService;
import ar.com.avaco.nitrophyl.service.pieza.PiezaClienteService;
import ar.com.avaco.nitrophyl.ws.dto.CotizacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.CotizacionFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Transactional
@Service("cotizacionEPService")
public class CotizacionEPServiceImpl extends
		CRUDAuditableEPBaseService<Long, CotizacionDTO, Cotizacion, CotizacionService> implements CotizacionEPService {

	private PiezaClienteService pcService;

	public CotizacionEPServiceImpl() {
		super(Cotizacion.class, CotizacionDTO.class);
	}

	@Override
	public CotizacionDTO getCotizacionVigente(Long idPiezaCliente) {
		Cotizacion cotizacionVigente = this.service.getCotizacionVigente(idPiezaCliente);
		CotizacionDTO dto = null;
		if (cotizacionVigente != null) {
			dto = convertToDto(cotizacionVigente);
		}
		return dto;
	}

	@Override
	protected CotizacionDTO convertToDto(Cotizacion entity) {
		CotizacionDTO dto = super.convertToDto(entity);
		dto.setCliente(entity.getPiezaCliente().getCliente().getNombre());
		dto.setIdCliente(entity.getPiezaCliente().getCliente().getId());
		PiezaFormula detalleFormula = entity.getPiezaCliente().getPieza().getDetalleFormula();
		if (detalleFormula != null)
			dto.setFormula(detalleFormula.getFormula().getNombre());
		dto.setValor(entity.getValor());
		dto.setFecha(entity.getFecha());
		dto.setObservaciones(entity.getObservaciones());
		dto.setIdPieza(entity.getPiezaCliente().getPieza().getId());
		dto.setPieza(entity.getPiezaCliente().getPieza().getDenominacion());
		return dto;
	}

	@Override
	protected Cotizacion convertToEntity(CotizacionDTO dto) {
		Cotizacion cotizacion = new Cotizacion();
		cotizacion.setFecha(dto.getFecha());
		cotizacion.setObservaciones(dto.getObservaciones());
		cotizacion.setValor(dto.getValor());

		PiezaCliente piezaCliente = pcService.getByPiezaCliente(dto.getIdCliente(), dto.getIdPieza());
		if (piezaCliente == null) {
			piezaCliente = new PiezaCliente();
			piezaCliente.setCliente(Cliente.ofId(dto.getIdCliente()));
			piezaCliente.setPieza(Pieza.ofId(dto.getIdPieza()));
			piezaCliente.setFechaCreacion(DateUtils.getFechaYHoraActual());
			piezaCliente.setUsuarioCreacion(SecurityContextHolder.getContext().getAuthentication().getName());
			piezaCliente = this.pcService.save(piezaCliente);
		}
		cotizacion.setPiezaCliente(piezaCliente);

		return cotizacion;
	}

//	@Override
//	public CotizacionDTO save(CotizacionDTO dto) throws BusinessException {
//		Cotizacion convertToEntity = this.convertToEntity(dto);
//		this.service.save(convertToEntity);
//		return dto;
//	}

	@Override
	public PageDTO<CotizacionDTO> list(CotizacionFilterDTO filterDTO) {
		return this.service.listCotizaciones(filterDTO);
	}

	@Override
	public CotizacionDTO update(CotizacionDTO dto) throws BusinessException {
		throw new RuntimeException("No se puede actualizar las cotizaciones");
	}

	@Override
	protected void validationSave(CotizacionDTO dto) {
		CotizacionFilterDTO filter = new CotizacionFilterDTO();
		filter.setFirst(1);
		filter.setIdCliente(dto.getIdCliente());
		filter.setIdPieza(dto.getIdPieza());
		filter.setSoloVigentes(true);
		filter.setRows(1);
		List<CotizacionDTO> page = this.service.listCotizaciones(filter).getPage();
		if (page != null && !page.isEmpty()) {
			CotizacionDTO cotizacion = page.get(0);
			if (dto.getFecha().before(cotizacion.getFecha())) {
				throw new ErrorValidationException("La fecha de la nueva cotizacin debe ser posterior a "
						+ DateUtils.toString(cotizacion.getFecha(), "dd/MM/yyyy"));
			}
		}
	}

	@Override
	@Resource(name = "cotizacionService")
	protected void setService(CotizacionService service) {
		this.service = service;
	}

	@Resource(name = "piezaClienteService")
	public void setPcService(PiezaClienteService pcService) {
		this.pcService = pcService;
	}

}