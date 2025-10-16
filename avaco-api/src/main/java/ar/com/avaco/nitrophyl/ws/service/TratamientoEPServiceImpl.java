package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.Tratamiento;
import ar.com.avaco.nitrophyl.service.pieza.TratamientoService;
import ar.com.avaco.nitrophyl.ws.dto.TratamientoDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("tratamientoEPService")
public class TratamientoEPServiceImpl
		extends CRUDAuditableEPBaseService<Long, TratamientoDTO, Tratamiento, TratamientoService>
		implements TratamientoEPService {

	public TratamientoEPServiceImpl() {
		super(Tratamiento.class, TratamientoDTO.class);
	}

	@Override
	@Resource(name = "tratamientoService")
	protected void setService(TratamientoService service) {
		this.service = service;
	}

}