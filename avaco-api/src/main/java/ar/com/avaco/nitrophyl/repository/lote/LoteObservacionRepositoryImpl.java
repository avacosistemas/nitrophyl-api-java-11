package ar.com.avaco.nitrophyl.repository.lote;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.lote.LoteObservacion;

@Repository("loteObservacionRepository")
public class LoteObservacionRepositoryImpl extends NJBaseRepository<Long, LoteObservacion> implements LoteObservacionRepositoryCustom {

	public LoteObservacionRepositoryImpl(EntityManager entityManager) {
		super(LoteObservacion.class, entityManager);
	}

	

}
