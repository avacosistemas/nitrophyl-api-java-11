package ar.com.avaco.nitrophyl.repository.pieza;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.MateriaPrimaStockHistorial;

@Repository("materiaPrimaStockHistorialRepository")
public class MateriaPrimaStockHistorialRepositoryImpl extends NJBaseRepository<Long, MateriaPrimaStockHistorial> implements MateriaPrimaStockHistorialRepositoryCustom {

	public MateriaPrimaStockHistorialRepositoryImpl(EntityManager entityManager) {
		super(MateriaPrimaStockHistorial.class, entityManager);
	}

}