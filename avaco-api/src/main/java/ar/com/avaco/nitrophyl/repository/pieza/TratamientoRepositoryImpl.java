package ar.com.avaco.nitrophyl.repository.pieza;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Tratamiento;

@Repository("tratamientoRepository")
public class TratamientoRepositoryImpl extends NJBaseRepository<Long, Tratamiento> implements TratamientoRepositoryCustom {

	public TratamientoRepositoryImpl(EntityManager entityManager) {
		super(Tratamiento.class, entityManager);
	}

}