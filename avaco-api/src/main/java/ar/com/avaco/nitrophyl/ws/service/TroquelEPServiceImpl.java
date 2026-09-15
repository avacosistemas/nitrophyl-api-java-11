package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.nitrophyl.domain.entities.molde.Troquel;
import ar.com.avaco.nitrophyl.service.produccion.TroquelService;
import ar.com.avaco.nitrophyl.ws.dto.TroquelDTO;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Transactional
@Service("troquelEPService")
public class TroquelEPServiceImpl extends CRUDEPBaseService<Long, TroquelDTO, Troquel, TroquelService>
		implements TroquelEPService {

	@Override
	@Resource(name = "troquelService")
	protected void setService(TroquelService service) {
		this.service = service;
	}

	@Override
	protected Troquel convertToEntity(TroquelDTO dto) {
		Troquel t = new Troquel();
		t.setId(dto.getId());
		t.setNombre(dto.getNombre());
		return t;
	}

	@Override
	protected TroquelDTO convertToDto(Troquel entity) {
		TroquelDTO t = new TroquelDTO();
		t.setId(entity.getId());
		t.setNombre(entity.getNombre());
		return t;
	}

}