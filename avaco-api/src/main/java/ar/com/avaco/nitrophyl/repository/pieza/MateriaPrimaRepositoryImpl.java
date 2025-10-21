package ar.com.avaco.nitrophyl.repository.pieza;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.MateriaPrima;

@Repository("materiaPrimaRepository")
public class MateriaPrimaRepositoryImpl extends NJBaseRepository<Long, MateriaPrima> implements MateriaPrimaRepositoryCustom {

	public MateriaPrimaRepositoryImpl(EntityManager entityManager) {
		super(MateriaPrima.class, entityManager);
	}

}