package ar.com.avaco.nitrophyl.repository.material;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;

@Repository("formulaRepository")
public class FormulaRepositoryImpl extends NJBaseRepository<Long, Formula> implements FormulaRepositoryCustom {

	public FormulaRepositoryImpl(EntityManager entityManager) {
		super(Formula.class, entityManager);
	}

}
