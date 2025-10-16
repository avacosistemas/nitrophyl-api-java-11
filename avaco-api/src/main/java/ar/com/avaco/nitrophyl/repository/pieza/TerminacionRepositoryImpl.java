package ar.com.avaco.nitrophyl.repository.pieza;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Terminacion;

@Repository("terminacionRepository")
public class TerminacionRepositoryImpl extends NJBaseRepository<Long, Terminacion> implements TerminacionRepositoryCustom {

	public TerminacionRepositoryImpl(EntityManager entityManager) {
		super(Terminacion.class, entityManager);
	}

}