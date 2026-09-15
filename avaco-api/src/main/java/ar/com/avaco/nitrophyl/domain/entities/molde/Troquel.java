package ar.com.avaco.nitrophyl.domain.entities.molde;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.arc.core.domain.Entity;

@javax.persistence.Entity
@Table(name = "TROQUEL")
public class Troquel extends Entity<Long> {

	private static final long serialVersionUID = 8480886302352309706L;

	@Id
	@GeneratedValue(generator = "TROQUEL_SEQ")
	@GenericGenerator(name = "TROQUEL_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "TROQUEL_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_TROQUEL", unique = true, nullable = false)
	private Long id;

	@Column(name = "NOMBRE", nullable = false)
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

	public static Troquel ofId(Long idTroquel) {
		Troquel troquel = new Troquel();
		troquel.setId(idTroquel);
		return troquel;
	}

}
