package ar.com.avaco.nitrophyl.repository.pieza;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaMolde;

@Repository("piezaMoldeRepository")
public class PiezaMoldeRepositoryImpl extends NJBaseRepository<Long, PiezaMolde> implements PiezaMoldeRepositoryCustom {

	public PiezaMoldeRepositoryImpl(EntityManager entityManager) {
		super(PiezaMolde.class, entityManager);
	}

}