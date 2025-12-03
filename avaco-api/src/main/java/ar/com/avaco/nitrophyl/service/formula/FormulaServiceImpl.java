package ar.com.avaco.nitrophyl.service.formula;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPrueba;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.formula.RevisionParametros;
import ar.com.avaco.nitrophyl.repository.material.ConfiguracionPruebaRepository;
import ar.com.avaco.nitrophyl.repository.material.FormulaRepository;
import ar.com.avaco.nitrophyl.repository.material.RevisionParametrosRepository;
import ar.com.avaco.utils.DateUtils;

@Transactional
@Service("formulaService")
public class FormulaServiceImpl extends NJBaseService<Long, Formula, FormulaRepository> implements FormulaService {

	private RevisionParametrosRepository revisionParametrosRepository;

	private ConfiguracionPruebaRepository configuracionPruebaRepository;

	@Override
	public Formula save(Formula entity) {
		entity.setId(null);
		entity.setFecha(DateUtils.getFechaYHoraActual());
		entity.setVersion(1);
		return super.save(entity);
	}

	@Override
	public Formula update(Formula entity) {
		Formula formula = this.get(entity.getId());
		entity.setRevision(formula.getRevision());
		entity.setVersion(entity.getVersion());
		return super.update(entity);
	}

	@Override
	public Formula clone(Formula entity) {
		Formula one = this.repository.getDetachedById(entity.getId());
		entity.setId(null);
		entity.setVersion(one.getVersion() + 1);
		entity.setFecha(DateUtils.getFechaYHoraActual());
		return super.save(entity);
	}

	@Override
	public RevisionParametros marcarRevision(Long idFormula) {

		// Obtengo la formula
		Formula formula = this.repository.getOne(idFormula);

		// Obtengo la revision vigente de la formula
		RevisionParametros revision = formula.getRevision();
		Long rev = 0L;

		// Si tiene revision asociada, le seteo la fecha hasta de su validez y obtengo
		// el nuevo numero de revision.
		if (revision != null) {
			revision.setFechaHasta(DateUtils.getFechaYHoraActual());
			this.revisionParametrosRepository.saveAndFlush(revision);
			rev = revision.getRevision() + 1;
		}

		// Creo una nueva revisin de parametros
		RevisionParametros rp = new RevisionParametros();
		rp.setFecha(DateUtils.getFechaYHoraActual());

		// Busco las configuraciones de maquina con mayor version
		List<ConfiguracionPrueba> configuraciones = this.configuracionPruebaRepository
				.obtenerConfiguracionesMaximaVersionIndividual(idFormula);

		// Le seteo de cada maquina la que tenga version mas alta.
		rp.getConfiguraciones().addAll(configuraciones);
		rp.setFormula(formula);
		rp.setRevision(rev);

		// Lo grabo
		rp = this.revisionParametrosRepository.saveAndFlush(rp);

		// Asocio la revision a la forula
		formula.setRevision(rp);
		this.update(formula);

		List<Long> ids = rp.getConfiguraciones().stream().map(ConfiguracionPrueba::getId).collect(Collectors.toList());

		// Actualizo las configuraciones vigentes. Pongo vigent en false en toda las
		// configuraciones y luego pongo en true todas las de maximo version
		this.configuracionPruebaRepository.actualizarConfiguracionesVigentes(ids, idFormula);

		return rp;
	}

	@Resource(name = "formulaRepository")
	public void setFormulaRepository(FormulaRepository formulaRepository) {
		this.repository = formulaRepository;
	}

	@Resource(name = "revisionParametrosRepository")
	public void setRevisionParametrosRepository(RevisionParametrosRepository revisionParametrosRepository) {
		this.revisionParametrosRepository = revisionParametrosRepository;
	}

	@Resource(name = "configuracionPruebaRepository")
	public void setConfiguracionPruebaRepository(ConfiguracionPruebaRepository configuracionPruebaRepository) {
		this.configuracionPruebaRepository = configuracionPruebaRepository;
	}

}
