package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Bombeo;
import ar.com.avaco.nitrophyl.repository.pieza.BombeoRepository;

@Transactional
@Service("bombeoService")
public class BombeoServiceImpl extends NJBaseService<Long, Bombeo, BombeoRepository> implements BombeoService {

	@Resource(name = "bombeoRepository")
	void setRepository(BombeoRepository bombeoRepository) {
		this.repository = bombeoRepository;
	}

}
