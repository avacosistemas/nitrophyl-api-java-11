package ar.com.avaco.nitrophyl.domain.entities.formula;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "FORMULA")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "FORMULA_SEQ", sequenceName = "FORMULA_SEQ", allocationSize = 1)
public class Formula extends ar.com.avaco.arc.core.domain.Entity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2296428532611007942L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORMULA_SEQ")
	@Column(name = "ID_FORMULA", unique = true, nullable = false)
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "NORMA")
	private String norma;

	@Column(name = "VERSION")
	private Integer version;

	@Column(name = "FECHA")
	private Date fecha;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "ID_MATERIAL")
	@Fetch(FetchMode.JOIN)
	private Material material;

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	/**
	 * Ultima revision vigente de parametros
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "ID_REV_PARAM")
	@Fetch(FetchMode.JOIN)
	private RevisionParametros revision;

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

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNorma() {
		return norma;
	}

	public void setNorma(String norma) {
		this.norma = norma;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public RevisionParametros getRevision() {
		return revision;
	}

	public void setRevision(RevisionParametros revision) {
		this.revision = revision;
	}

}
