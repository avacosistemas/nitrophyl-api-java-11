package ar.com.avaco.nitrophyl.repository.material;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.formula.Material;

@Repository("materialRepository")
public class MaterialRepositoryImpl extends NJBaseRepository<Long, Material> implements MaterialRepositoryCustom {

	public MaterialRepositoryImpl(EntityManager entityManager) {
		super(Material.class, entityManager);
	}

}
