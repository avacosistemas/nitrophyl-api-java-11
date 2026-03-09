package ar.com.avaco.arc.core.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import org.hibernate.Hibernate;

/**
 * Default abstract class that represents a unique and independent domain
 * object.<br>
 * Provides a comparison capability using the unique identifier of the object.
 * 
 */
@MappedSuperclass
public abstract class Entity<ID extends Serializable> implements Serializable {

	private static final long serialVersionUID = 7239163146107336896L;

	public abstract void setId(ID id);

	public abstract ID getId();

	@Override
	public String toString() {
	    return getClass().getSimpleName() +
	           "[id=" + (getId() != null ? getId().toString() : "null") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null) return false;
	    if (Hibernate.getClass(this) != Hibernate.getClass(o)) return false;

	    Entity<?> other = (Entity<?>) o;
	    return getId() != null && getId().equals(other.getId());
	}

}