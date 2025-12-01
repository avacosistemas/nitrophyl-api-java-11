package ar.com.avaco.nitrophyl.domain.entities.formula;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.pieza.UnidadDureza;

@Entity
@Table(name = "FORMULA")
@Inheritance(strategy = InheritanceType.JOINED)
public class Formula extends ar.com.avaco.arc.core.domain.Entity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2296428532611007942L;

	@Id
	@GeneratedValue(generator = "FORMULA_SEQ")
	@GenericGenerator(name = "FORMULA_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "FORMULA_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
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

	@Column(name = "DUREZA_MIN")
	private Double durezaMinima;

	@Column(name = "DUREZA_MAX")
	private Double durezaMaxima;

	@Column(name = "DUREZA_UNIDAD")
	@Enumerated(EnumType.STRING)
	private UnidadDureza unidadDureza;

	public Double getDurezaMinima() {
		return durezaMinima;
	}

	public void setDurezaMinima(Double durezaMinima) {
		this.durezaMinima = durezaMinima;
	}

	public Double getDurezaMaxima() {
		return durezaMaxima;
	}

	public void setDurezaMaxima(Double durezaMaxima) {
		this.durezaMaxima = durezaMaxima;
	}

	public UnidadDureza getUnidadDureza() {
		return unidadDureza;
	}

	public void setUnidadDureza(UnidadDureza unidadDureza) {
		this.unidadDureza = unidadDureza;
	}

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
