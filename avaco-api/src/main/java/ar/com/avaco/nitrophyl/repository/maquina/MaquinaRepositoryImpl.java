package ar.com.avaco.nitrophyl.repository.maquina;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.maquina.Maquina;

@Repository("maquinaRepository")
public class MaquinaRepositoryImpl extends NJBaseRepository<Long, Maquina> implements MaquinaRepositoryCustom {

	public MaquinaRepositoryImpl(EntityManager entityManager) {
		super(Maquina.class, entityManager);
	}

}
