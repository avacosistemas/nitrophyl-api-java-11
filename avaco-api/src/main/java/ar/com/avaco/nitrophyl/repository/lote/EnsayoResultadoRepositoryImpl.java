package ar.com.avaco.nitrophyl.repository.lote;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.lote.EnsayoResultado;

@Repository("ensayoResultadoRepository")
public class EnsayoResultadoRepositoryImpl extends NJBaseRepository<Long, EnsayoResultado> implements EnsayoResultadoRepositoryCustom {

	public EnsayoResultadoRepositoryImpl(EntityManager entityManager) {
		super(EnsayoResultado.class, entityManager);
	}

}
