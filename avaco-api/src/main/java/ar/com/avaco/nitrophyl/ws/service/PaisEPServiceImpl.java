package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.nitrophyl.domain.entities.administracion.Pais;
import ar.com.avaco.nitrophyl.service.administracion.PaisService;
import ar.com.avaco.nitrophyl.ws.dto.PaisDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Transactional
@Service("paisEPService")
public class PaisEPServiceImpl extends CRUDAuditableEPBaseService<Long, PaisDTO, Pais, PaisService>
		implements PaisEPService {

	public PaisEPServiceImpl() {
		super(Pais.class, PaisDTO.class);
	}

	@Override
	@Resource(name = "paisService")
	protected void setService(PaisService service) {
		this.service = service;
	}

}