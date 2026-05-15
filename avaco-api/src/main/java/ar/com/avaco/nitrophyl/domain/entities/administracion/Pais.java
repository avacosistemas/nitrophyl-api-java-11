package ar.com.avaco.nitrophyl.domain.entities.administracion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "PAIS")
public class Pais extends AuditableEntity<Long> {

	private static final long serialVersionUID = 3868279756784146751L;

	@Id
	@GeneratedValue(generator = "PAIS_SEQ")
	@GenericGenerator(name = "PAIS_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "PAIS_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_PAIS", unique = true, nullable = false)
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static Pais ofId(Long idPais) {
		Pais et = new Pais();
		et.setId(idPais);
		return et;
	}

}
