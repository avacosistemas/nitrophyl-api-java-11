package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.EsquemaPaso;
import ar.com.avaco.nitrophyl.service.pieza.EsquemaPasoService;
import ar.com.avaco.nitrophyl.ws.dto.EsquemaPasoDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("esquemaPasoEPService")
public class EsquemaPasoEPServiceImpl
		extends CRUDAuditableEPBaseService<Long, EsquemaPasoDTO, EsquemaPaso, EsquemaPasoService>
		implements EsquemaPasoEPService {

	public EsquemaPasoEPServiceImpl() {
		super(EsquemaPaso.class, EsquemaPasoDTO.class);
	}

	@Override
	@Resource(name = "esquemaPasoService")
	protected void setService(EsquemaPasoService service) {
		this.service = service;
	}

}