package ar.com.avaco.nitrophyl.repository.fabricacion;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompra;

@Repository("ordenCompraRepository")
public class OrdenCompraRepositoryImpl extends NJBaseRepository<Long, OrdenCompra> implements OrdenCompraRepositoryCustom {

	public OrdenCompraRepositoryImpl(EntityManager entityManager) {
		super(OrdenCompra.class, entityManager);
	}

	
	
}