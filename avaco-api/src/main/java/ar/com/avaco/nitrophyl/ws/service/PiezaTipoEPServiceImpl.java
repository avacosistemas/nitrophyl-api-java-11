package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaTipo;
import ar.com.avaco.nitrophyl.service.pieza.PiezaTipoService;
import ar.com.avaco.nitrophyl.ws.dto.PiezaTipoDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("piezaTipoEPService")
public class PiezaTipoEPServiceImpl extends CRUDAuditableEPBaseService<Long, PiezaTipoDTO, PiezaTipo, PiezaTipoService>
		implements PiezaTipoEPService {

	public PiezaTipoEPServiceImpl() {
		super(PiezaTipo.class, PiezaTipoDTO.class);
	}

	@Override
	@Resource(name = "piezaTipoService")
	protected void setService(PiezaTipoService service) {
		this.service = service;
	}


}