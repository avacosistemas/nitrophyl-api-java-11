package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.Adhesivo;
import ar.com.avaco.nitrophyl.service.pieza.AdhesivoService;
import ar.com.avaco.nitrophyl.ws.dto.AdhesivoDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("adhesivoEPService")
public class AdhesivoEPServiceImpl extends CRUDAuditableEPBaseService<Long, AdhesivoDTO, Adhesivo, AdhesivoService>
		implements AdhesivoEPService {

	public AdhesivoEPServiceImpl() {
		super(Adhesivo.class, AdhesivoDTO.class);
	}

	@Override
	@Resource(name = "adhesivoService")
	protected void setService(AdhesivoService service) {
		this.service = service;
	}

}
