package ar.com.avaco.nitrophyl.repository.molde;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeBoca;

public interface MoldeBocaRepository extends NJRepository<Long, MoldeBoca>, MoldeBocaRepositoryCustom {

	List<MoldeBoca> findByIdMolde(Long idMolde);
	
	void deleteByIdMolde(Long idMolde);

}
