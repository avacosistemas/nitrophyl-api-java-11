package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaCliente;
import ar.com.avaco.nitrophyl.service.pieza.PiezaClienteService;
import ar.com.avaco.nitrophyl.ws.dto.CotizacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaClienteDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("piezaClienteEPService")
public class PiezaClienteEPServiceImpl
		extends CRUDAuditableEPBaseService<Long, PiezaClienteDTO, PiezaCliente, PiezaClienteService>
		implements PiezaClienteEPService {

	private CotizacionEPService cotizacionEPService;
	
	public PiezaClienteEPServiceImpl() {
		super(PiezaCliente.class, PiezaClienteDTO.class);
	}

	@Override
	protected PiezaClienteDTO convertToDto(PiezaCliente entity) {
		PiezaClienteDTO dto = super.convertToDto(entity);
		dto.setIdCliente(entity.getCliente().getId());
		dto.setIdPieza(entity.getPieza().getId());
		dto.setNombreCliente(entity.getCliente().getNombre());
		dto.setDenominacion(entity.getPieza().getDenominacion());
		dto.setCodigo(entity.getPieza().getCodigo());
		CotizacionDTO cotizacionVigente = this.cotizacionEPService.getCotizacionVigente(dto.getId());
		if (cotizacionVigente != null) {
			dto.setCotizacion(cotizacionVigente.getValor());
			dto.setFechaCotizacion(cotizacionVigente.getFecha());
			dto.setObservacionesCotizacion(cotizacionVigente.getObservaciones());
		}
		return dto;
	}

	@Override
	protected PiezaCliente convertToEntity(PiezaClienteDTO dto) {
		PiezaCliente entity = super.convertToEntity(dto);
		entity.setCliente(Cliente.ofId(dto.getIdCliente()));
		entity.setPieza(Pieza.ofId(dto.getIdPieza()));
		entity.setNombrePiezaPersonalizado(dto.getNombrePiezaPersonalizado());
		return entity;
	}

	@Override
	public PiezaClienteDTO save(PiezaClienteDTO dto) throws BusinessException {
		PiezaClienteDTO save = super.save(dto);
		if (dto.getCotizacion() != null) {
			CotizacionDTO cdto = new CotizacionDTO();
			cdto.setIdCliente(dto.getIdCliente());
			cdto.setIdPieza(dto.getIdPieza());
			cdto.setObservaciones(dto.getObservacionesCotizacion());
			cdto.setValor(dto.getCotizacion());
			this.cotizacionEPService.save(cdto);
		}
		return save;
	}
	
	@Override
	@Resource(name = "piezaClienteService")
	protected void setService(PiezaClienteService service) {
		this.service = service;
	}

	@Resource(name = "cotizacionEPService")
	public void setCotizacionEPService(CotizacionEPService cotizacionEPService) {
		this.cotizacionEPService = cotizacionEPService;
	}
	
}