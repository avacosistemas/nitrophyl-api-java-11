package ar.com.avaco.nitrophyl.service.formula;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.formula.RevisionParametros;


public interface FormulaService extends NJService<Long, Formula> {

	Formula clone(Formula entity);

	RevisionParametros marcarRevision(Long idFormula);
	
}
