package ar.com.avaco.nitrophyl.domain.entities.formula;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "FORMULA_REV_PARAM")
@SequenceGenerator(name = "FORMULA_REV_PARAM_SEQ", sequenceName = "FORMULA_REV_PARAM_SEQ", allocationSize = 1)
public class RevisionParametros extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORMULA_REV_PARAM_SEQ")
	@Column(name = "ID_REV_PARAM")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_FORMULA")
	private Formula formula;

	@Column(name = "REVISION", nullable = false)
	private Long revision;

	@Column(name = "FECHA")
	private Date fecha;

	// se debe pisar cuando se cree una nueva revision. La actual es la que no tiene
	// fechaHasta
	@Column(name = "FECHA_HASTA")
	private Date fechaHasta;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "FORMULA_REV_PARAM_CONF_PRUEBA", joinColumns = @JoinColumn(name = "ID_FORMULA_REV_PARAM", referencedColumnName = "ID_REV_PARAM"), inverseJoinColumns = @JoinColumn(name = "ID_CONF_PRUEBA", referencedColumnName = "ID_CONF_PRUEBA"))
	@Fetch(FetchMode.SELECT)
	private Set<ConfiguracionPrueba> configuraciones = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Set<ConfiguracionPrueba> getConfiguraciones() {
		return configuraciones;
	}

	public void setConfiguraciones(Set<ConfiguracionPrueba> configuraciones) {
		this.configuraciones = configuraciones;
	}

	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
	}

	public Long getRevision() {
		return revision;
	}

	public void setRevision(Long revision) {
		this.revision = revision;
	}

}
