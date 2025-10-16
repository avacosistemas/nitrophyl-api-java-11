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
@Table(name = "PIEZA_TIPO")
@SequenceGenerator(name = "PIEZA_TIPO_SEQ", sequenceName = "PIEZA_TIPO_SEQ", allocationSize = 1)
public class PiezaTipo extends AuditableEntity<Long> {

	private static final long serialVersionUID = -4375607783210704758L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PIEZA_TIPO_SEQ")
	@Column(name = "ID_PIEZA_TIPO", unique = true, nullable = false)
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
