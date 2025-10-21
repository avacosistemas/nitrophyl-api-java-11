package ar.com.avaco.nitrophyl.domain.entities.fabrica;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "PRENSA")
public class Prensa extends AuditableEntity<Long> {

	private static final long serialVersionUID = 2603285910247363629L;

	@Id
	@GeneratedValue(generator = "PRENSA_SEQ")
	@GenericGenerator(name = "PRENSA_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "PRENSA_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_PRENSA", unique = true, nullable = false)
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static Prensa ofId(Long id) {
		Prensa prensa = new Prensa();
		prensa.setId(id);
		return prensa;
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    Prensa prensa = (Prensa) o;
	    return Objects.equals(id, prensa.id);
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(id);
	}
	
}
