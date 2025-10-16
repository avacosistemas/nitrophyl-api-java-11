package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.Esquema;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Proceso;
import ar.com.avaco.nitrophyl.service.pieza.EsquemaService;
import ar.com.avaco.nitrophyl.ws.dto.EsquemaDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("esquemaEPService")
public class EsquemaEPServiceImpl extends CRUDAuditableEPBaseService<Long, EsquemaDTO, Esquema, EsquemaService>
		implements EsquemaEPService {

	public EsquemaEPServiceImpl() {
		super(Esquema.class, EsquemaDTO.class);
	}

	@Override
	protected Esquema convertToEntity(EsquemaDTO dto) {
		Esquema entity = super.convertToEntity(dto);
		entity.setProceso(Proceso.ofId(dto.getIdProceso()));
		entity.getPasos().forEach(paso -> paso.setEsquema(entity));
		return entity;
	}

	@Override
	@Resource(name = "esquemaService")
	protected void setService(EsquemaService service) {
		this.service = service;
	}

}