package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaDimension;
import ar.com.avaco.nitrophyl.service.pieza.PiezaDimensionService;
import ar.com.avaco.nitrophyl.ws.dto.PiezaDimensionDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("piezaDimensionEPService")
public class PiezaDimensionEPServiceImpl
		extends CRUDAuditableEPBaseService<Long, PiezaDimensionDTO, PiezaDimension, PiezaDimensionService>
		implements PiezaDimensionEPService {

	public PiezaDimensionEPServiceImpl() {
		super(PiezaDimension.class, PiezaDimensionDTO.class);
	}

	@Override
	protected PiezaDimensionDTO convertToDto(PiezaDimension entity) {
		PiezaDimensionDTO dto = super.convertToDto(entity);
		dto.setIdPieza(entity.getPieza().getId());
		return dto;
	}
	
	@Override
	protected PiezaDimension convertToEntity(PiezaDimensionDTO dto) {
		PiezaDimension entity = super.convertToEntity(dto);
		entity.setPieza(Pieza.ofId(dto.getIdPieza()));
		return entity;
	}
	
	@Override
	@Resource(name = "piezaDimensionService")
	protected void setService(PiezaDimensionService service) {
		this.service = service;
	}

}