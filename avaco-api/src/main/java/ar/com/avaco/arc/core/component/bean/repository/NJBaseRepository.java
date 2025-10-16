package ar.com.avaco.arc.core.component.bean.repository;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.NullPrecedence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.CriteriaImpl.Subcriteria;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.StringUtils;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;

public class NJBaseRepository<ID extends Serializable, E extends ar.com.avaco.arc.core.domain.Entity<ID>>
        extends SimpleJpaRepository<E, ID> implements NJRepository<ID, E> {

    @Autowired
    private SessionFactory sessionFactory;

    private final Class<E> javaType;
    protected final EntityManager entityManager;

    public NJBaseRepository(Class<E> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.javaType = domainClass;
        this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> listFilter(AbstractFilter abstractFilter) {
        Criteria criteria = getCurrentSession().createCriteria(getHandledClass());
        applyFilters(criteria, abstractFilter);
        applyPagination(criteria, abstractFilter);
        applyOrdering(criteria, abstractFilter);
        if (Boolean.TRUE.equals(abstractFilter.getDistinctRootEntity())) {
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        }
        return criteria.list();
    }

    protected Class<E> getHandledClass() {
        return javaType;
    }

    @Override
    public int listCount(AbstractFilter abstractFilter) {
        Criteria criteria = getCurrentSession().createCriteria(getHandledClass());
        applyFilters(criteria, abstractFilter);
        Long uniqueResult = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        return uniqueResult.intValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> listPattern(String field, String pattern) {
        Criteria criteria = getCurrentSession().createCriteria(getHandledClass());
        criteria.add(Restrictions.like(field, pattern.trim(), MatchMode.ANYWHERE).ignoreCase());
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> listEqField(String field, Object value) {
        Criteria criteria = getCurrentSession().createCriteria(getHandledClass());
        if (value == null) {
            criteria.add(Restrictions.isNull(field));
        } else {
            criteria.add(Restrictions.eq(field, value));
        }
        return criteria.list();
    }

    protected void applyPagination(Criteria criteria, AbstractFilter abstractFilter) {
        if (abstractFilter.getFirst() != null && abstractFilter.getRows() != null) {
            criteria.setFirstResult(abstractFilter.getFirst());
            criteria.setMaxResults(abstractFilter.getRows());
        }
    }

    protected void applyOrdering(Criteria criteria, AbstractFilter abstractFilter) {
        if (abstractFilter.getAsc() != null && !StringUtils.isEmpty(abstractFilter.getSidx())) {
            criteria = containsAlias(criteria, abstractFilter.getIdx());
            String sortField = abstractFilter.getIdx();
            if (!isPropertyEmbeddable(sortField)) {
                sortField = getAliasedProperty(sortField);
            }
            if (abstractFilter.isAsc()) {
                criteria.addOrder(Order.asc(sortField).nulls(NullPrecedence.NONE));
            } else {
                criteria.addOrder(Order.desc(sortField).nulls(NullPrecedence.NONE));
            }
        }
    }

    private String getAliasedProperty(String property) {
        String[] prop = property.split("\\.");
        StringBuilder field = new StringBuilder();
        for (int i = 0; i < prop.length; i++) {
            if (i != 0 && i == prop.length - 1) {
                field.append(".");
            }
            field.append(prop[i]);
        }
        return field.toString();
    }

    protected void applyFilters(Criteria criteria, AbstractFilter abstractFilter) {
        if (abstractFilter == null) return;

        for (List<FilterData> fdl : abstractFilter.getOrFilterDatas()) {
            Disjunction or = Restrictions.disjunction();
            for (FilterData ofd : fdl) {
                criteria = containsAlias(criteria, ofd.getProperty());
                or.add(createCriterion(ofd));
            }
            criteria.add(or);
        }

        for (FilterData data : abstractFilter.getFilterDatas()) {
            criteria = containsAlias(criteria, data.getProperty());
            Criterion criterion = createCriterion(data);
            criteria.add(criterion);
        }
    }

    private Criterion createCriterion(FilterData data) {
        String property = data.getProperty();
        if (!isPropertyEmbeddable(property)) {
            property = getAliasedProperty(property);
        }

        switch (data.getFilterDataType()) {
            case LESS_THAN: return Restrictions.lt(property, data.getObject());
            case MORE_THAN: return Restrictions.gt(property, data.getObject());
            case EQUALS: return Restrictions.eq(property, data.getObject());
            case LIKE: return Restrictions.ilike(property, data.getObject().toString(), MatchMode.ANYWHERE);
            case EQUALS_LESS_THAN: return Restrictions.le(property, data.getObject());
            case EQUALS_MORE_THAN: return Restrictions.ge(property, data.getObject());
            case NOT_EQUALS: return Restrictions.not(Restrictions.eq(property, data.getObject()));
            case IS_NULL: return Restrictions.isNull(property);
            case IS_NOT_NULL: return Restrictions.isNotNull(property);
            case IN: return Restrictions.in(property, (Object[]) data.getObject());
            case NOT_IN: return Restrictions.not(Restrictions.in(property, (Object[]) data.getObject()));
            default: return null;
        }
    }

    private boolean isPropertyEmbeddable(String property) {
        String theProperty = property.contains(".") ? property.substring(0, property.indexOf(".")) : property;
        Class<E> clazz = getHandledClass();

        while (clazz != null) {
            try {
                Annotation[] annotations = clazz.getDeclaredField(theProperty).getType().getAnnotations();
                for (Annotation ann : annotations) {
                    if (ann.annotationType().equals(Embeddable.class)) {
                        return true;
                    }
                }
                return false;
            } catch (NoSuchFieldException e) {
                clazz = (Class<E>) clazz.getSuperclass();
                if (clazz.equals(Object.class)) {
                    throw new RuntimeException("Property " + property + " not present in " + getHandledClass().getName());
                }
            }
        }
        return false;
    }

    private Criteria containsAlias(Criteria criteria, String property) {
        if (!isPropertyEmbeddable(property) && property.contains(".")) {
            String[] prop = property.split("\\.");
            CriteriaImpl ci = (CriteriaImpl) criteria;

            for (int i = 0; i < prop.length - 1; i++) {
                StringBuilder associationPath = new StringBuilder();
                StringBuilder alias = new StringBuilder();

                for (int j = 0; j <= i; j++) {
                    associationPath.append(prop[j]);
                    if (j != i) associationPath.append(".");
                    alias.append(prop[j]);
                }

                Iterator<Subcriteria> it = ci.iterateSubcriteria();
                boolean found = false;
                while (it.hasNext() && !found) {
                    Subcriteria next = it.next();
                    found = next.getAlias().equals(alias.toString());
                }

                if (!found) {
                    criteria.createAlias(associationPath.toString(), alias.toString(), JoinType.LEFT_OUTER_JOIN);
                }
            }
        }
        return criteria;
    }

    public Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
