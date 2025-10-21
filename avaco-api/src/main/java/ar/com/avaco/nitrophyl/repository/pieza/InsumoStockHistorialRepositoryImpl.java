package ar.com.avaco.nitrophyl.repository.pieza;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.InsumoStockHistorial;

@Repository("insumoStockHistorialRepository")
public class InsumoStockHistorialRepositoryImpl extends NJBaseRepository<Long, InsumoStockHistorial> implements InsumoStockHistorialRepositoryCustom {

	public InsumoStockHistorialRepositoryImpl(EntityManager entityManager) {
		super(InsumoStockHistorial.class, entityManager);
	}

}