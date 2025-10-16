package ar.com.avaco.nitrophyl.repository.pieza;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Esquema;

@Repository("esquemaRepository")
public class EsquemaRepositoryImpl extends NJBaseRepository<Long, Esquema> implements EsquemaRepositoryCustom {

	public EsquemaRepositoryImpl(EntityManager entityManager) {
		super(Esquema.class, entityManager);
	}

}