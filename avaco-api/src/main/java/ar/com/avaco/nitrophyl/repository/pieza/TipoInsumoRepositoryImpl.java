package ar.com.avaco.nitrophyl.repository.pieza;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoInsumo;

@Repository("tipoInsumoRepository")
public class TipoInsumoRepositoryImpl extends NJBaseRepository<Long, TipoInsumo> implements TipoInsumoRepositoryCustom {

	public TipoInsumoRepositoryImpl(EntityManager entityManager) {
		super(TipoInsumo.class, entityManager);
	}

	@Override
	public List<TipoInsumo> listHijos() {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		Subquery<Long> subquery;
		CriteriaQuery<TipoInsumo> query = cb.createQuery(TipoInsumo.class);
		Root<TipoInsumo> root = query.from(TipoInsumo.class);
		subquery = query.subquery(Long.class);
		Root<TipoInsumo> subRoot = subquery.from(TipoInsumo.class);
		subquery.select(subRoot.get("padre").get("id")).where(cb.isNotNull(subRoot.get("padre"))).distinct(true);
		query.select(root).where(cb.not(root.get("id").in(subquery)));
		return this.entityManager.createQuery(query).getResultList();
	}

}
