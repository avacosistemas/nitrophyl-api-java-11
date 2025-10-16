package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaPlano;
import ar.com.avaco.nitrophyl.repository.pieza.PiezaPlanoRepository;

@Transactional
@Service("piezaPlanoService")
public class PiezaPlanoServiceImpl extends NJBaseService<Long, PiezaPlano, PiezaPlanoRepository> implements PiezaPlanoService {

	@Resource(name = "piezaPlanoRepository")
	void setRepository(PiezaPlanoRepository piezaPlanoRepository) {
		this.repository = piezaPlanoRepository;
	}

}
