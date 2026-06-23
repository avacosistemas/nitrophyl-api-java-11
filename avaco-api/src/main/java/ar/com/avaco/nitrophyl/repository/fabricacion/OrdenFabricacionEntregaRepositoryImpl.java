package ar.com.avaco.nitrophyl.repository.fabricacion;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenFabricacionEntrega;

@Repository("ordenFabricacionEntregaRepository")
public class OrdenFabricacionEntregaRepositoryImpl extends NJBaseRepository<Long, OrdenFabricacionEntrega> implements OrdenFabricacionEntregaRepositoryCustom {

	public OrdenFabricacionEntregaRepositoryImpl(EntityManager entityManager) {
		super(OrdenFabricacionEntrega.class, entityManager);
	}

	
	
}