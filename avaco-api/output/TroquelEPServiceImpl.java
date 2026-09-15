package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.nitrophyl.domain.entities.produccion.Troquel;
import ar.com.avaco.nitrophyl.service.produccion.TroquelService;
import ar.com.avaco.nitrophyl.ws.dto.TroquelDTO;
import ar.com.avaco.nitrophyl.ws.service.TroquelEPService;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Transactional
@Service("troquelEPService")
public class TroquelEPServiceImpl extends CRUDAuditableEPBaseService<Long, TroquelDTO, Troquel, TroquelService>
		implements TroquelEPService {

	public TroquelEPServiceImpl() {
		super(Troquel.class, TroquelDTO.class);
	}

	@Override
	@Resource(name = "troquelService")
	protected void setService(TroquelService service) {
		this.service = service;
	}

}