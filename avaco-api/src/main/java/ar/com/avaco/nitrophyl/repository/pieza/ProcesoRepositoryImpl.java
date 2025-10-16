package ar.com.avaco.nitrophyl.repository.pieza;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Proceso;

@Repository("procesoRepository")
public class ProcesoRepositoryImpl extends NJBaseRepository<Long, Proceso> implements ProcesoRepositoryCustom {

	public ProcesoRepositoryImpl(EntityManager entityManager) {
		super(Proceso.class, entityManager);
	}

}