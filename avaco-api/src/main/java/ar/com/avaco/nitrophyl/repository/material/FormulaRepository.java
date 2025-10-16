package ar.com.avaco.nitrophyl.repository.material;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;

public interface FormulaRepository extends NJRepository<Long, Formula>, FormulaRepositoryCustom {

}
