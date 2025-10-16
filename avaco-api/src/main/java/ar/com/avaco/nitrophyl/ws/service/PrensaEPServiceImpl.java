package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.fabrica.Prensa;
import ar.com.avaco.nitrophyl.service.pieza.PrensaService;
import ar.com.avaco.nitrophyl.ws.dto.PrensaDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("prensaEPService")
public class PrensaEPServiceImpl extends CRUDAuditableEPBaseService<Long, PrensaDTO, Prensa, PrensaService>
		implements PrensaEPService {

	public PrensaEPServiceImpl() {
		super(Prensa.class, PrensaDTO.class);
	}

	@Override
	@Resource(name = "prensaService")
	protected void setService(PrensaService service) {
		this.service = service;
	}

}