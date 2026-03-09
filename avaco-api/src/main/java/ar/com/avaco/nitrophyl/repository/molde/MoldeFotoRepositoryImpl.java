package ar.com.avaco.nitrophyl.repository.molde;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.molde.MoldeFoto;

@Repository("moldeFotoRepository")
public class MoldeFotoRepositoryImpl extends NJBaseRepository<Long, MoldeFoto> implements MoldeFotoRepositoryCustom {

	public MoldeFotoRepositoryImpl(EntityManager entityManager) {
		super(MoldeFoto.class, entityManager);
	}

}
