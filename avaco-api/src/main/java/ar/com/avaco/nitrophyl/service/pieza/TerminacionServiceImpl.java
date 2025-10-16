package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Terminacion;
import ar.com.avaco.nitrophyl.repository.pieza.TerminacionRepository;

@Transactional
@Service("terminacionService")
public class TerminacionServiceImpl extends NJBaseService<Long, Terminacion, TerminacionRepository> implements TerminacionService {

	@Resource(name = "terminacionRepository")
	void setRepository(TerminacionRepository terminacionRepository) {
		this.repository = terminacionRepository;
	}

}
