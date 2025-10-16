package ar.com.avaco.nitrophyl.repository.pieza;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Insumo;

@Repository("insumoRepository")
public class InsumoRepositoryImpl extends NJBaseRepository<Long, Insumo> implements InsumoRepositoryCustom {

	public InsumoRepositoryImpl(EntityManager entityManager) {
		super(Insumo.class, entityManager);
	}

}
