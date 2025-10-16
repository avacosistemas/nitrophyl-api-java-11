package ar.com.avaco.nitrophyl.repository.lote;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.lote.Ensayo;

@Repository("ensayoRepository")
public class EnsayoRepositoryImpl extends NJBaseRepository<Long, Ensayo> implements EnsayoRepositoryCustom {

	public EnsayoRepositoryImpl(EntityManager entityManager) {
		super(Ensayo.class, entityManager);
	}

}
