package ar.com.avaco.nitrophyl.repository.produccion;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.SectorFabrica;

@Repository("sectorFabricaRepository")
public class SectorFabricaRepositoryImpl extends NJBaseRepository<Long, SectorFabrica> implements SectorFabricaRepositoryCustom {

	public SectorFabricaRepositoryImpl(EntityManager entityManager) {
		super(SectorFabrica.class, entityManager);
	}

}