package ar.com.avaco.nitrophyl.service.produccion;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.molde.Troquel;
import ar.com.avaco.nitrophyl.repository.produccion.TroquelRepository;

@Transactional
@Service("troquelService")
public class TroquelServiceImpl extends NJBaseService<Long, Troquel, TroquelRepository> implements TroquelService {

	@Resource(name = "troquelRepository")
	void setRepository(TroquelRepository troquelRepository) {
		this.repository = troquelRepository;
	}

}
