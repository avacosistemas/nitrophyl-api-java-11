package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaMolde;
import ar.com.avaco.nitrophyl.repository.pieza.PiezaMoldeRepository;

@Transactional
@Service("piezaMoldeService")
public class PiezaMoldeServiceImpl extends NJBaseService<Long, PiezaMolde, PiezaMoldeRepository> implements PiezaMoldeService {

	@Resource(name = "piezaMoldeRepository")
	void setRepository(PiezaMoldeRepository piezaMoldeRepository) {
		this.repository = piezaMoldeRepository;
	}

}
