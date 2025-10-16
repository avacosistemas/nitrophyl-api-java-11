package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.Bombeo;
import ar.com.avaco.nitrophyl.service.pieza.BombeoService;
import ar.com.avaco.nitrophyl.ws.dto.BombeoDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("bombeoEPService")
public class BombeoEPServiceImpl extends CRUDAuditableEPBaseService<Long, BombeoDTO, Bombeo, BombeoService>
		implements BombeoEPService {

	public BombeoEPServiceImpl() {
		super(Bombeo.class, BombeoDTO.class);
	}

	@Override
	@Resource(name = "bombeoService")
	protected void setService(BombeoService service) {
		this.service = service;
	}

}