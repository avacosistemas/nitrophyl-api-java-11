package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaDimension;
import ar.com.avaco.nitrophyl.repository.pieza.PiezaDimensionRepository;

@Transactional
@Service("piezaDimensionService")
public class PiezaDimensionServiceImpl extends NJBaseService<Long, PiezaDimension, PiezaDimensionRepository> implements PiezaDimensionService {

	@Resource(name = "piezaDimensionRepository")
	void setRepository(PiezaDimensionRepository piezaDimensionRepository) {
		this.repository = piezaDimensionRepository;
	}

}
