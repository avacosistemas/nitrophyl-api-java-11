package ar.com.avaco.nitrophyl.domain.entities.fabricacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "SECTOR_FABRICA")
public class SectorFabrica extends AuditableEntity<Long> {

	private static final long serialVersionUID = -3889046490162070940L;

	@Id
	@GeneratedValue(generator = "SECTOR_FABRICA_SEQ")
	@GenericGenerator(name = "SECTOR_FABRICA_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SECTOR_FABRICA_SEQ"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_SECCION_FABRICA", unique = true, nullable = false)
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

	public static SectorFabrica ofId(Long idUsuario) {
		SectorFabrica sf = new SectorFabrica();
		sf.setId(idUsuario);
		return sf;
	}

}
