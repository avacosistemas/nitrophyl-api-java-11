package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Adhesivo;
import ar.com.avaco.nitrophyl.repository.pieza.AdhesivoRepository;

@Transactional
@Service("adhesivoService")
public class AdhesivoServiceImpl extends NJBaseService<Long, Adhesivo, AdhesivoRepository> implements AdhesivoService {

	@Resource(name = "adhesivoRepository")
	void setClienteRepository(AdhesivoRepository adhesivoRepository) {
		this.repository = adhesivoRepository;
	}

}
