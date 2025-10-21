package ar.com.avaco.nitrophyl.repository.material;

import java.util.List;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPrueba;

@Repository("configuracionPruebaRepository")
public class ConfiguracionPruebaRepositoryImpl extends NJBaseRepository<Long, ConfiguracionPrueba>
		implements ConfiguracionPruebaRepositoryCustom {

	public ConfiguracionPruebaRepositoryImpl(EntityManager entityManager) {
		super(ConfiguracionPrueba.class, entityManager);
	}

	@Override
	public List<ConfiguracionPrueba> obtenerConfiguracionesMaximaVersionIndividual(Long idFormula) {

		String query = " select * from conf_prueba cp inner join ( select id_maquina, max(version) max_value "
				+ "   from conf_prueba where id_formula = :idFormula group by id_maquina "
				+ " )  t on t.id_maquina = cp.id_maquina and t.max_value = cp.version "
				+ " and cp.id_formula = :idFormula ";

		List<ConfiguracionPrueba> list = this.getCurrentSession().createNativeQuery(query, ConfiguracionPrueba.class)
				.setParameter("idFormula", idFormula).getResultList();

		return list;
	}
	
	@Override
	public void actualizarConfiguracionesVigentes(List<Long> ids, Long idFormula) {

		this.getCurrentSession().createNativeQuery("update conf_prueba set vigente = false where id_formula = :idFormula")
			.setParameter("idFormula", idFormula).executeUpdate();
		
		this.getCurrentSession().createNativeQuery("update conf_prueba set vigente = true where id_conf_prueba in (:ids)")
				.setParameterList("ids", ids).executeUpdate();

	}



}
