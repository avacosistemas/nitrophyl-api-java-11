package ar.com.avaco.nitrophyl.repository.pieza;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaControl;

@Repository("piezaControlRepository")
public class PiezaControlRepositoryImpl extends NJBaseRepository<Long, PiezaControl> implements PiezaControlRepositoryCustom {

	public PiezaControlRepositoryImpl(EntityManager entityManager) {
		super(PiezaControl.class, entityManager);
	}

		
}
