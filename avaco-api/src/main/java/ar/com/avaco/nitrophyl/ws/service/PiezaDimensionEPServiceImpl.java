package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaDimension;
import ar.com.avaco.nitrophyl.service.pieza.PiezaDimensionService;
import ar.com.avaco.nitrophyl.service.pieza.PiezaService;
import ar.com.avaco.nitrophyl.ws.dto.PiezaDimensionDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("piezaDimensionEPService")
public class PiezaDimensionEPServiceImpl
		extends CRUDAuditableEPBaseService<Long, PiezaDimensionDTO, PiezaDimension, PiezaDimensionService>
		implements PiezaDimensionEPService {

	@Autowired
	private PiezaService piezaService;
	
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
	public PiezaDimensionDTO save(PiezaDimensionDTO dto) throws BusinessException {
		Pieza pieza = piezaService.get(dto.getIdPieza());
		PiezaDimension entity = convertToEntity(dto);
		entity.setPieza(pieza);
		pieza.getDimensiones().add(entity);
		pieza = this.piezaService.update(pieza);
		return dto;
	}

	@Override
	public void remove(Long id) {
		PiezaDimension piezaDimension = this.service.get(id);
		Pieza pieza = piezaDimension.getPieza();
		pieza.getDimensiones().remove(piezaDimension);
		this.piezaService.update(pieza);
	}
	
	@Override
	@Resource(name = "piezaDimensionService")
	protected void setService(PiezaDimensionService service) {
		this.service = service;
	}

}