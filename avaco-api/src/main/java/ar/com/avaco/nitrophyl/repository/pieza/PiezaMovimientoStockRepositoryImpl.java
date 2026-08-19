package ar.com.avaco.nitrophyl.repository.pieza;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaMovimientoStock;

@Repository("piezaMovimientoStockRepository")
public class PiezaMovimientoStockRepositoryImpl extends NJBaseRepository<Long, PiezaMovimientoStock> implements PiezaMovimientoStockRepositoryCustom {

	public PiezaMovimientoStockRepositoryImpl(EntityManager entityManager) {
		super(PiezaMovimientoStock.class, entityManager);
	}

		
}
