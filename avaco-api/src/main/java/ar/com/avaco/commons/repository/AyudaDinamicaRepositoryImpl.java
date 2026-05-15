package ar.com.avaco.commons.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.commons.domain.AyudaDinamica;

@Repository("ayudaDinamicaRepository")
public class AyudaDinamicaRepositoryImpl extends NJBaseRepository<Long, AyudaDinamica> implements AyudaDinamicaRepositoryCustom {

	public AyudaDinamicaRepositoryImpl(EntityManager entityManager) {
		super(AyudaDinamica.class, entityManager);
	}

}