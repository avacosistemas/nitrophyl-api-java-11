package ar.com.avaco.nitrophyl.service.formula;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.formula.RevisionParametros;
import ar.com.avaco.nitrophyl.repository.material.RevisionParametrosRepository;

@Transactional
@Service("revisionParametroService")
public class RevisionParametroServiceImpl extends NJBaseService<Long, RevisionParametros, RevisionParametrosRepository> implements RevisionParametroService {


	@Override
	public void deleteByFormula(Long idFormula) {
		this.repository.deleteByFormulaId(idFormula);
	}

	@Resource(name = "revisionParametrosRepository")
	public void setRevisionParametrosRepository(RevisionParametrosRepository revisionParametrosRepository) {
		this.repository = revisionParametrosRepository;
	}

}
