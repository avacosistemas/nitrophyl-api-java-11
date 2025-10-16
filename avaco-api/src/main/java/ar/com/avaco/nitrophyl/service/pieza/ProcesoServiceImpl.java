package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Proceso;
import ar.com.avaco.nitrophyl.repository.pieza.ProcesoRepository;

@Transactional
@Service("procesoService")
public class ProcesoServiceImpl extends NJBaseService<Long, Proceso, ProcesoRepository> implements ProcesoService {

	@Resource(name = "procesoRepository")
	void setRepository(ProcesoRepository procesoRepository) {
		this.repository = procesoRepository;
	}

}
