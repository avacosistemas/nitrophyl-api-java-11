package ar.com.avaco.nitrophyl.service.maquina;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPrueba;

public interface ConfiguracionPruebaService extends NJService<Long, ConfiguracionPrueba> {

	List<ConfiguracionPrueba> listByFormulaId(Long idFormula);

	List<ConfiguracionPrueba> listByLote(Long idLote);

	void setearVigente(Long idConfiguracionPrueba);

	void deleteByFormulaId(Long idFormula);

}
