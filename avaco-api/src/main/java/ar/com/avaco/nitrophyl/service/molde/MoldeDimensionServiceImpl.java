package ar.com.avaco.nitrophyl.service.molde;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.molde.MoldeDimension;
import ar.com.avaco.nitrophyl.repository.molde.MoldeDimensionRepository;

@Transactional
@Service("moldeDimensionService")
public class MoldeDimensionServiceImpl extends NJBaseService<Long, MoldeDimension, MoldeDimensionRepository>
		implements MoldeDimensionService {

	private Logger logger = LogManager.getLogger(getClass());		

	@Resource(name = "moldeDimensionRepository")
	void setClienteRepository(MoldeDimensionRepository moldeDimensionRepository) {
		this.repository = moldeDimensionRepository;
	}

	@Override
	public List<MoldeDimension> getByMolde(Long idMolde) {
		return this.repository.findByIdMolde(idMolde);
	}

	@Override
	public void removeByMolde(Long idMolde) {
		this.repository.deleteByIdMolde(idMolde);
		
	}

}
