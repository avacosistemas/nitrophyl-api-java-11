package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Tratamiento;
import ar.com.avaco.nitrophyl.repository.pieza.TratamientoRepository;

@Transactional
@Service("tratamientoService")
public class TratamientoServiceImpl extends NJBaseService<Long, Tratamiento, TratamientoRepository> implements TratamientoService {

	@Resource(name = "tratamientoRepository")
	void setRepository(TratamientoRepository tratamientoRepository) {
		this.repository = tratamientoRepository;
	}

}
