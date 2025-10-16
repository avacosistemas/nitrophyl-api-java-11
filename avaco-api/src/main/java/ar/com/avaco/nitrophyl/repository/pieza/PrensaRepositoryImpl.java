package ar.com.avaco.nitrophyl.repository.pieza;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.fabrica.Prensa;

@Repository("prensaRepository")
public class PrensaRepositoryImpl extends NJBaseRepository<Long, Prensa> implements PrensaRepositoryCustom {

	public PrensaRepositoryImpl(EntityManager entityManager) {
		super(Prensa.class, entityManager);
	}

}