package ar.com.avaco.nitrophyl.domain.entities.formula;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.maquina.Maquina;

@Entity
@Table(name = "CONF_PRUEBA")
public class ConfiguracionPrueba extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = -2296428532611007942L;

	@Id
	@GeneratedValue(generator = "CONF_PRUEBA_SEQ")
	@GenericGenerator(name = "CONF_PRUEBA_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "CONF_PRUEBA_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_CONF_PRUEBA", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_MAQUINA")
	private Maquina maquina;

	@Column(name = "VERSION", nullable = false)
	private Long version;

	@Column(name = "FECHA", nullable = false)
	private Date fecha;

	@Column(name = "FECHA_HASTA", nullable = true)
	private Date fechaHasta;

	@Column(name = "VIGENTE", nullable = false)
	private Boolean vigente;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_FORMULA")
	private Formula formula;

	@Column(name = "OBSERVACIONES_REPORTE")
	private String observacionesReporte;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "configuracionPrueba")
	private Set<ConfiguracionPruebaParametro> parametros = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "configuracionPrueba")
	private Set<ConfiguracionPruebaCondicion> condiciones = new HashSet<>();

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

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
	}

	public Set<ConfiguracionPruebaParametro> getParametros() {
		return parametros;
	}

	public void setParametros(Set<ConfiguracionPruebaParametro> parametros) {
		this.parametros = parametros;
	}

	public Set<ConfiguracionPruebaCondicion> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(Set<ConfiguracionPruebaCondicion> condiciones) {
		this.condiciones = condiciones;
	}

	public String getObservacionesReporte() {
		return observacionesReporte;
	}

	public void setObservacionesReporte(String observacionesReporte) {
		this.observacionesReporte = observacionesReporte;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Boolean getVigente() {
		return vigente;
	}

	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getPosicion() {
		return this.maquina.getPosicion();
	}

}
