package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.nitrophyl.domain.entities.lote.LoteObservacion;
import ar.com.avaco.nitrophyl.service.lote.LoteObservacionService;
import ar.com.avaco.nitrophyl.ws.dto.LoteObservacionDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("loteObservacionEPService")
public class LoteObservacionEPServiceImpl
		extends CRUDAuditableEPBaseService<Long, LoteObservacionDTO, LoteObservacion, LoteObservacionService>
		implements LoteObservacionEPService {

	public LoteObservacionEPServiceImpl() {
		super(LoteObservacion.class, LoteObservacionDTO.class);
	}

	@Override
	@Resource(name = "loteObservacionService")
	protected void setService(LoteObservacionService service) {
		this.service = service;
	}

	@Override
	protected LoteObservacion convertToEntity(LoteObservacionDTO dto) {
		LoteObservacion entity = new LoteObservacion();
		entity.setMostrarReporte(dto.getMostrarReporte());
		entity.setObservaciones(dto.getObservaciones());
		entity.setLote(Lote.ofId(dto.getIdLote()));
		return entity;
	}
	
	@Override
	protected LoteObservacionDTO convertToDto(LoteObservacion entity) {
		LoteObservacionDTO dto = new LoteObservacionDTO();
		dto.setId(entity.getId());
		dto.setMostrarReporte(entity.getMostrarReporte());
		dto.setObservaciones(entity.getObservaciones());
		return dto;
	}
	
	@Override
	public void updateCheck(Long idLoteObservacion) {
		this.service.updateCheck(idLoteObservacion);
	}

}
