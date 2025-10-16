package ar.com.avaco.nitrophyl.service.maquina;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPrueba;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.formula.RevisionParametros;
import ar.com.avaco.nitrophyl.repository.lote.LoteRepository;
import ar.com.avaco.nitrophyl.repository.material.ConfiguracionPruebaRepository;
import ar.com.avaco.nitrophyl.repository.material.RevisionParametrosRepository;
import ar.com.avaco.utils.DateUtils;

@Transactional
@Service("configuracionPruebaService")
public class ConfiguracionPruebaServiceImpl extends
		NJBaseService<Long, ConfiguracionPrueba, ConfiguracionPruebaRepository> implements ConfiguracionPruebaService {

	private LoteRepository loteRepository;

	private RevisionParametrosRepository revParamRepository;

	@Override
	public List<ConfiguracionPrueba> listByFormulaId(Long idFormula) {
		return this.repository.findAllByFormulaIdOrderByMaquinaNombre(idFormula);
	}

	@Override
	public ConfiguracionPrueba save(ConfiguracionPrueba entity) {

		// Busco si hay una configuracion previa.
		Optional<ConfiguracionPrueba> confprevia = this.repository.findTopByFormulaIdAndMaquinaIdOrderByVersionDesc(
				entity.getFormula().getId(), entity.getMaquina().getId());
		Long version = 0L;

		// Si hay una configuracion previa, le pongo una version mas.
		if (confprevia.isPresent()) {
			ConfiguracionPrueba configuracionPrueba = confprevia.get();
			configuracionPrueba.setFechaHasta(DateUtils.getFechaYHoraActual());
			// configuracionPrueba.setVigente(false);
			version = configuracionPrueba.getVersion() + 1;
			this.repository.saveAndFlush(configuracionPrueba);
		}

		entity.setFecha(DateUtils.getFechaYHoraActual());
		entity.setFechaHasta(null);
		entity.setVersion(version);
		entity.setVigente(false);
		ConfiguracionPrueba saveAndFlush = this.repository.saveAndFlush(entity);
		return saveAndFlush;
	}

	@Override
	public ConfiguracionPrueba update(ConfiguracionPrueba entity) {

		// Obtengo la entidad de la base
//		ConfiguracionPrueba one = this.repository.getOne(entity.getId());
//
//		one.setObservacionesReporte(entity.getObservacionesReporte());
//
//		// Recorro los parametros que recibi para actualizar los existentes
//		Iterator<ConfiguracionPruebaParametro> iterator = entity.getParametros().iterator();
//		while (iterator.hasNext()) {
//			ConfiguracionPruebaParametro next = iterator.next();
//
//			Predicate<? super ConfiguracionPruebaParametro> predicate = x -> {
//				Long idConf1 = x.getConfiguracionPrueba().getId();
//				Long idConf2 = next.getConfiguracionPrueba().getId();
//				return idConf1.equals(idConf2);
//			};
//
//			// Busco la prueba para actualizarla
//			ConfiguracionPruebaParametro configuracionPruebaParametro = one.getParametros().parallelStream()
//					.filter(predicate).findFirst().get();
//
//			// Seteo los valores de maximo, minimo y norma
//			configuracionPruebaParametro.setMinimo(next.getMinimo());
//			configuracionPruebaParametro.setMaximo(next.getMaximo());
//			configuracionPruebaParametro.setNorma(next.getNorma());
//
//		}

		return super.update(entity);
	}

	@Override
	public List<ConfiguracionPrueba> listByLote(Long idLote) {
		return new ArrayList<ConfiguracionPrueba>(
				this.loteRepository.findById(idLote).orElseThrow().getRevisionParametros().getConfiguraciones());
	}

	@Override
	public void setearVigente(Long idConfiguracionPrueba) {

		// Busco la configuracion
		ConfiguracionPrueba cp = this.repository.getOne(idConfiguracionPrueba);

		// Busco la formula de la configuracion
		Formula formula = cp.getFormula();

		// Busco la revision vigente de la formula
		RevisionParametros revision = formula.getRevision();

		// Seteo la configuracion como vigente
		cp.setVigente(true);

		// Agrego la configuracion a la revision vigente
		revision.getConfiguraciones().add(cp);

		// Actualizo la revision
		this.revParamRepository.saveAndFlush(revision);

	}

	@Resource(name = "configuracionPruebaRepository")
	void setMoldeRepository(ConfiguracionPruebaRepository configuracionPruebaRepository) {
		this.repository = configuracionPruebaRepository;
	}

	@Resource(name = "loteRepository")
	public void setLoteRepository(LoteRepository loteRepository) {
		this.loteRepository = loteRepository;
	}

	@Resource(name = "revisionParametrosRepository")
	public void setRevParamService(RevisionParametrosRepository revParamRepository) {
		this.revParamRepository = revParamRepository;
	}

}
