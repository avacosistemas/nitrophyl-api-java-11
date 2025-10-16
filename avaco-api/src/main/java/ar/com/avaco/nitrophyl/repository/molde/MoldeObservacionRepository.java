package ar.com.avaco.nitrophyl.repository.molde;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeObservacion;

public interface MoldeObservacionRepository extends NJRepository<Long, MoldeObservacion>, MoldeObservacionRepositoryCustom {

	List<MoldeObservacion> findByIdMoldeOrderByFechaCreacionDesc(Long idMolde);
	
}
