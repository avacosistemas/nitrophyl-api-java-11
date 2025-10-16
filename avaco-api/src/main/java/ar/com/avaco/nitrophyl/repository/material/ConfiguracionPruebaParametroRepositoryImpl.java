package ar.com.avaco.nitrophyl.repository.material;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPruebaParametro;

@Repository("configuracionPruebaParametroRepository")
public class ConfiguracionPruebaParametroRepositoryImpl extends NJBaseRepository<Long, ConfiguracionPruebaParametro>
		implements ConfiguracionPruebaParametroRepositoryCustom {

	public ConfiguracionPruebaParametroRepositoryImpl(EntityManager entityManager) {
		super(ConfiguracionPruebaParametro.class, entityManager);
	}

}
