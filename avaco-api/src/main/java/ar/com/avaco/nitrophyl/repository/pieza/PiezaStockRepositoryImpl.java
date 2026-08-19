package ar.com.avaco.nitrophyl.repository.pieza;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaStock;

@Repository("piezaStockRepository")
public class PiezaStockRepositoryImpl extends NJBaseRepository<Long, PiezaStock> implements PiezaStockRepositoryCustom {

	public PiezaStockRepositoryImpl(EntityManager entityManager) {
		super(PiezaStock.class, entityManager);
	}

		
}
