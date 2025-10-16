package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "TRATAMIENTO")
@SequenceGenerator(name = "TRATAMIENTO_SEQ", sequenceName = "TRATAMIENTO_SEQ", allocationSize = 1)
public class Tratamiento extends AuditableEntity<Long> {

	private static final long serialVersionUID = 4690913411486835414L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRATAMIENTO_SEQ")
	@Column(name = "ID_TRATAMIENTO", unique = true, nullable = false)
	private Long id;

	@Column(name = "NOMBRE", nullable = false, unique = true)
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
