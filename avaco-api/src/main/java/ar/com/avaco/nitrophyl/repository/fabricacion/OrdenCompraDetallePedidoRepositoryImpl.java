package ar.com.avaco.nitrophyl.repository.fabricacion;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompraDetallePedido;

@Repository("ordenCompraDetallePedidoRepository")
public class OrdenCompraDetallePedidoRepositoryImpl extends NJBaseRepository<Long, OrdenCompraDetallePedido> implements OrdenCompraDetallePedidoRepositoryCustom {

	public OrdenCompraDetallePedidoRepositoryImpl(EntityManager entityManager) {
		super(OrdenCompraDetallePedido.class, entityManager);
	}

}