package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "ADHESIVO")
public class Adhesivo extends AuditableEntity<Long> {

	private static final long serialVersionUID = -7515569203985933376L;

	@Id
	@GeneratedValue(generator = "ADHESIVO_SEQ")
	@GenericGenerator(
	        name = "ADHESIVO_SEQ",
	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	        parameters = {
	            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "ADHESIVO_SEQ"),
	            @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
	            @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
	        }
	    )
	@Column(name = "ID_ADHESIVO", unique = true, nullable = false)
	private Long id;

	@Column(name = "NOMBRE", unique = true, nullable = false)
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

}
