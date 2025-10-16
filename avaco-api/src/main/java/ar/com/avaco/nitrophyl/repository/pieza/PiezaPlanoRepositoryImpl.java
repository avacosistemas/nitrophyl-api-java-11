package ar.com.avaco.nitrophyl.repository.pieza;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaPlano;

@Repository("piezaPlanoRepository")
public class PiezaPlanoRepositoryImpl extends NJBaseRepository<Long, PiezaPlano> implements PiezaPlanoRepositoryCustom {

	public PiezaPlanoRepositoryImpl(EntityManager entityManager) {
		super(PiezaPlano.class, entityManager);
	}

}