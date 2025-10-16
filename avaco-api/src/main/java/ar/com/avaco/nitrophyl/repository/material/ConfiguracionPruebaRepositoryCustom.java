package ar.com.avaco.nitrophyl.repository.material;

import java.util.List;

import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPrueba;

public interface ConfiguracionPruebaRepositoryCustom {

	List<ConfiguracionPrueba> obtenerConfiguracionesMaximaVersionIndividual(Long idFormula);

	void actualizarConfiguracionesVigentes(List<Long> ids, Long idFormula);
	
}
