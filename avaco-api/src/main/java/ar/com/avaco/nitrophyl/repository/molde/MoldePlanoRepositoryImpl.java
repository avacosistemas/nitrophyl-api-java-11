package ar.com.avaco.nitrophyl.repository.molde;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldePlano;

@Repository("moldePlanoRepository")
public class MoldePlanoRepositoryImpl extends NJBaseRepository<Long, MoldePlano> implements MoldePlanoRepositoryCustom {

	public MoldePlanoRepositoryImpl(EntityManager entityManager) {
		super(MoldePlano.class, entityManager);
	}

}
