package ar.com.avaco.nitrophyl.repository.molde;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeObservacion;

@Repository("moldeObservacionRepository")
public class MoldeObservacionRepositoryImpl extends NJBaseRepository<Long, MoldeObservacion> implements MoldeObservacionRepositoryCustom {

	public MoldeObservacionRepositoryImpl(EntityManager entityManager) {
		super(MoldeObservacion.class, entityManager);
	}

}
