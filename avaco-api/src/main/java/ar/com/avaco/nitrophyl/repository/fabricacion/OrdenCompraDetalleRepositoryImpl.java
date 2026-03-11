package ar.com.avaco.nitrophyl.repository.fabricacion;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompraDetalle;

@Repository("ordenCompraDetalleRepository")
public class OrdenCompraDetalleRepositoryImpl extends NJBaseRepository<Long, OrdenCompraDetalle> implements OrdenCompraDetalleRepositoryCustom {

	public OrdenCompraDetalleRepositoryImpl(EntityManager entityManager) {
		super(OrdenCompraDetalle.class, entityManager);
	}

}