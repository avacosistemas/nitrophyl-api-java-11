package ar.com.avaco.nitrophyl.repository.molde;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeDimension;

public interface MoldeDimensionRepository extends NJRepository<Long, MoldeDimension>, MoldeDimensionRepositoryCustom {

	List<MoldeDimension> findByIdMolde(Long idMolde);
	
	long deleteByIdMolde(Long idMolde);

}
