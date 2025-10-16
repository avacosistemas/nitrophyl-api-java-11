package ar.com.avaco.nitrophyl.repository.reporte;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.maquina.Maquina;
import ar.com.avaco.nitrophyl.domain.entities.reporte.ReporteLoteConfiguracionCliente;

@Repository("reporteLoteConfiguracionClienteRepository")
public class ReporteLoteConfiguracionClienteRepositoryImpl
		extends NJBaseRepository<Long, ReporteLoteConfiguracionCliente>
		implements ReporteLoteConfiguracionClienteRepositoryCustom {

	public ReporteLoteConfiguracionClienteRepositoryImpl(EntityManager entityManager) {
		super(ReporteLoteConfiguracionCliente.class, entityManager);
	}

	@Override
	public ReporteLoteConfiguracionCliente findByFormulaClienteMaquina(Formula formula, Cliente cliente,
			Maquina maquina) {
		Criteria criteria = getCurrentSession().createCriteria(ReporteLoteConfiguracionCliente.class);

		criteria.add(Restrictions.eq("formula", formula));

		if (cliente == null || cliente.getId() == 0) {
			criteria.add(Restrictions.isNull("cliente"));
		} else {
			criteria.add(Restrictions.eq("cliente.id", cliente.getId()));
		}

		if (maquina == null || maquina.getId() == 0) {
			criteria.add(Restrictions.isNull("maquina"));
		} else {
			criteria.add(Restrictions.eq("maquina.id", maquina.getId()));
		}

		return (ReporteLoteConfiguracionCliente) criteria.uniqueResult();
	}

	@Override
	public List<ReporteLoteConfiguracionCliente> findConfiguracionesByClienteIdFormulaId(Long idFormula,
			Long idCliente) {
		Criteria criteria = getCurrentSession().createCriteria(ReporteLoteConfiguracionCliente.class);
		criteria.add(Restrictions.eq("formula.id", idFormula));
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.eqOrIsNull("cliente.id", idCliente));
		disjunction.add(Restrictions.isNull("cliente"));
		criteria.add(disjunction);
		return criteria.list();
	}

}
