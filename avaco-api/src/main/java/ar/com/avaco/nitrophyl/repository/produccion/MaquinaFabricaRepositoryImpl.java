package ar.com.avaco.nitrophyl.repository.produccion;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.MaquinaFabrica;

@Repository("maquinaFabricaRepository")
public class MaquinaFabricaRepositoryImpl extends NJBaseRepository<Long, MaquinaFabrica> implements MaquinaFabricaRepositoryCustom {

	public MaquinaFabricaRepositoryImpl(EntityManager entityManager) {
		super(MaquinaFabrica.class, entityManager);
	}

}