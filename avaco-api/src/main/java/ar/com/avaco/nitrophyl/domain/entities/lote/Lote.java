package ar.com.avaco.nitrophyl.domain.entities.lote;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.formula.RevisionParametros;

@Entity
@Table(name = "LOTE")
public class Lote extends AuditableEntity<Long> {

	private static final long serialVersionUID = -4128392123321814398L;

	@Id
	@GeneratedValue(generator = "LOTE_SEQ")
	@GenericGenerator(name = "LOTE_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "LOTE_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_LOTE", unique = true, nullable = false)
	private Long id;

	@Column(name = "NRO_LOTE")
	private String nroLote;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "ID_FORMULA")
	private Formula formula;

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	@Column(name = "FECHA")
	private Date fecha;

	@Column(name = "OBS_ESTADO")
	private String observacionesEstado;

	@Column(name = "FECHA_ESTADO")
	private Date fechaEstado;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO")
	private EstadoLote estado;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lote", orphanRemoval = true)
	private Set<Ensayo> ensayos = new HashSet<Ensayo>();

	// FIXME CAMBIAR optional a false luego de setear todos los valores
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "ID_REVISION_PARAMETROS")
	private RevisionParametros revisionParametros;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
	}

	public Set<Ensayo> getEnsayos() {
		return ensayos;
	}

	public void setEnsayos(Set<Ensayo> ensayos) {
		this.ensayos = ensayos;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNroLote() {
		return nroLote;
	}

	public void setNroLote(String nroLote) {
		this.nroLote = nroLote;
	}

	public String getObservacionesEstado() {
		return observacionesEstado;
	}

	public void setObservacionesEstado(String observacionesEstado) {
		this.observacionesEstado = observacionesEstado;
	}

	public Date getFechaEstado() {
		return fechaEstado;
	}

	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	public EstadoLote getEstado() {
		return estado;
	}

	public void setEstado(EstadoLote estado) {
		this.estado = estado;
	}

	public RevisionParametros getRevisionParametros() {
		return revisionParametros;
	}

	public void setRevisionParametros(RevisionParametros revisionParametros) {
		this.revisionParametros = revisionParametros;
	}

}
