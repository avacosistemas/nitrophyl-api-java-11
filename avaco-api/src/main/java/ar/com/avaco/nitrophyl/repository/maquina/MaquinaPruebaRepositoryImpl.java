package ar.com.avaco.nitrophyl.repository.maquina;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.maquina.MaquinaPrueba;

@Repository("maquinaPruebaRepository")
public class MaquinaPruebaRepositoryImpl extends NJBaseRepository<Long, MaquinaPrueba>
		implements MaquinaPruebaRepositoryCustom {

	public MaquinaPruebaRepositoryImpl(EntityManager entityManager) {
		super(MaquinaPrueba.class, entityManager);
	}

}
