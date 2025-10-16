package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaMolde;
import ar.com.avaco.nitrophyl.service.molde.MoldeService;
import ar.com.avaco.nitrophyl.service.pieza.PiezaMoldeService;
import ar.com.avaco.nitrophyl.service.pieza.PiezaService;
import ar.com.avaco.nitrophyl.ws.dto.PiezaMoldeDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("piezaMoldeEPService")
public class PiezaMoldeEPServiceImpl extends CRUDAuditableEPBaseService<Long, PiezaMoldeDTO, PiezaMolde, PiezaMoldeService>
		implements PiezaMoldeEPService {

	private MoldeService moldeService;
	
	private PiezaService piezaService;
	
	public PiezaMoldeEPServiceImpl() {
		super(PiezaMolde.class, PiezaMoldeDTO.class);
	}

	@Override
	protected PiezaMolde convertToEntity(PiezaMoldeDTO dto) {
		PiezaMolde entity = super.convertToEntity(dto);
		entity.setId(dto.getId());
		entity.setMolde(moldeService.get(dto.getIdMolde()));
		entity.setObservaciones(dto.getObservaciones());
		entity.setPieza(piezaService.get(dto.getIdPieza()));
		return entity;
	}
	
	@Override
	protected PiezaMoldeDTO convertToDto(PiezaMolde entity) {
		PiezaMoldeDTO dto = super.convertToDto(entity);
		dto.setCodigo(entity.getMolde().getCodigo());
		dto.setId(entity.getId());
		dto.setIdMolde(entity.getMolde().getId());
		dto.setIdPieza(entity.getPieza().getId());
		dto.setObservaciones(entity.getObservaciones());
		return dto;
	}
	
	@Override
	@Resource(name = "piezaMoldeService")
	protected void setService(PiezaMoldeService service) {
		this.service = service;
	}

	@Resource(name = "moldeService")
	public void setMoldeService(MoldeService moldeService) {
		this.moldeService = moldeService;
	}
	
	@Resource(name = "piezaService")
	public void setPiezaService(PiezaService piezaService) {
		this.piezaService = piezaService;
	}
	
}