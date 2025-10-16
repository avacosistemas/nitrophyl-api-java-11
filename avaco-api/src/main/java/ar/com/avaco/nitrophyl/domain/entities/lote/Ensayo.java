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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPrueba;

@Entity
@Table(name = "ENSAYO")
@SequenceGenerator(name = "ENSAYO_SEQ", sequenceName = "ENSAYO_SEQ", allocationSize = 1)
public class Ensayo extends AuditableEntity<Long> {

	private static final long serialVersionUID = 3379140593827986759L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENSAYO_SEQ")
	@Column(name = "ID_ENSAYO", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_LOTE")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Lote lote;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_CONF_PRUEBA", updatable = false)
	private ConfiguracionPrueba configuracionPrueba;

	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "observaciones")
	private String observaciones;

	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private EstadoEnsayo estado;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ensayo")
	private Set<EnsayoResultado> resultados = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Set<EnsayoResultado> getResultados() {
		return resultados;
	}

	public void setResultados(Set<EnsayoResultado> resultados) {
		this.resultados = resultados;
	}

	public EstadoEnsayo getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnsayo estado) {
		this.estado = estado;
	}

	public ConfiguracionPrueba getConfiguracionPrueba() {
		return configuracionPrueba;
	}

	public void setConfiguracionPrueba(ConfiguracionPrueba configuracionPrueba) {
		this.configuracionPrueba = configuracionPrueba;
	}

	public Integer getOrden() {
		return this.configuracionPrueba.getMaquina().getPosicion();
	}
	
}
