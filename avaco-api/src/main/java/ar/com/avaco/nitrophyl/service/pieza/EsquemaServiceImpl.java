package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Esquema;
import ar.com.avaco.nitrophyl.repository.pieza.EsquemaRepository;

@Transactional
@Service("esquemaService")
public class EsquemaServiceImpl extends NJBaseService<Long, Esquema, EsquemaRepository> implements EsquemaService {

	@Resource(name = "esquemaRepository")
	void setRepository(EsquemaRepository esquemaRepository) {
		this.repository = esquemaRepository;
	}

}
