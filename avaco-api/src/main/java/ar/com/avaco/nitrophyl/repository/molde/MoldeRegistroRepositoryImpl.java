package ar.com.avaco.nitrophyl.repository.molde;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeRegistro;

@Repository("moldeRegistroRepository")
public class MoldeRegistroRepositoryImpl extends NJBaseRepository<Long, MoldeRegistro> implements MoldeRegistroRepositoryCustom {

	public MoldeRegistroRepositoryImpl(EntityManager entityManager) {
		super(MoldeRegistro.class, entityManager);
	}

}
