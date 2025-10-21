package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "INSUMO_TRATADO")
public class InsumoTratado extends AuditableEntity<Long> {

	private static final long serialVersionUID = 797497464400538571L;

	@Id
	@GeneratedValue(generator = "INSUMO_TRATADO_SEQ")
	@GenericGenerator(name = "INSUMO_TRATADO_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "INSUMO_TRATADO_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_INSUMO_TRATADO", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_PIEZA")
	private Pieza pieza;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "INSUMO_TRATADO_TRATAMIENTO", joinColumns = @JoinColumn(name = "ID_INSUMO_TRATADO", referencedColumnName = "ID_INSUMO_TRATADO"), inverseJoinColumns = @JoinColumn(name = "ID_TRATAMIENTO", referencedColumnName = "ID_TRATAMIENTO"))
	@Fetch(FetchMode.SELECT)
	private Set<Tratamiento> tratamientos = new HashSet<>();

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "ID_INSUMO")
	private Insumo insumo;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "INSUMO_TRATADO_ADHESIVO", joinColumns = @JoinColumn(name = "ID_INSUMO_TRATADO", referencedColumnName = "ID_INSUMO_TRATADO"), inverseJoinColumns = @JoinColumn(name = "ID_ADHESIVO", referencedColumnName = "ID_ADHESIVO"))
	@Fetch(FetchMode.SELECT)
	private Set<Adhesivo> adhesivos = new HashSet<>();

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	/**
	 * La cantidad de insumos.
	 */
	@Column(name = "UNIDADES")
	private Integer unidades;

	/**
	 * La unidad en que se miden los insumos tratados. DIAMETRO, SUPERFICIE O
	 * LONGITUD
	 */
	@Column(name = "UNIDAD_MEDIDA_INSUMIDA")
	@Enumerated(EnumType.STRING)
	private UnidadMedidaInsumida unidadMedida;

	/**
	 * MM, CM o M
	 */
	@Column(name = "UNIDAD_MEDIDA_LONGITUD")
	@Enumerated(EnumType.STRING)
	private UnidadMedidaLongitud unidadMedidaLongitud;

	/**
	 * Puede indicar superficie, metros lineales o diametro.
	 */
	@Column(name = "MEDIDA1")
	private Double medida1;

	/**
	 * Depende de medida 1 para calcular superficie o algun otro tipo de medida que
	 * requiera 2 valores. Tendra valor dependiendo de la unidad de medida. Se
	 * empleara solo para superfiecie
	 */
	@Column(name = "MEDIDA2")
	private Double medida2;

	public Integer getUnidades() {
		return unidades;
	}

	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

	public Double getMedida1() {
		return medida1;
	}

	public void setMedida1(Double medida1) {
		this.medida1 = medida1;
	}

	public Double getMedida2() {
		return medida2;
	}

	public void setMedida2(Double medida2) {
		this.medida2 = medida2;
	}

	public UnidadMedidaInsumida getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedidaInsumida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public UnidadMedidaLongitud getUnidadMedidaLongitud() {
		return unidadMedidaLongitud;
	}

	public void setUnidadMedidaLongitud(UnidadMedidaLongitud unidadMedidaLongitud) {
		this.unidadMedidaLongitud = unidadMedidaLongitud;
	}

	public InsumoTratado clonar(String username, Date fechaHora, Pieza pieza) {
		InsumoTratado clonada = new InsumoTratado();
		clonada.resetearCreacion(username, fechaHora);
		clonada.getAdhesivos().addAll(this.adhesivos);
		clonada.setInsumo(this.insumo);
		clonada.setUnidades(this.unidades);
		clonada.setUnidadMedida(this.unidadMedida);
		clonada.setUnidadMedidaLongitud(this.unidadMedidaLongitud);
		clonada.setMedida1(this.medida1);
		clonada.setMedida2(this.medida2);
		clonada.setObservaciones(this.observaciones);
		clonada.setPieza(pieza);
		clonada.getTratamientos().addAll(this.tratamientos);
		return clonada;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	public Set<Tratamiento> getTratamientos() {
		return tratamientos;
	}

	public void setTratamientos(Set<Tratamiento> tratamientos) {
		this.tratamientos = tratamientos;
	}

	public Set<Adhesivo> getAdhesivos() {
		return adhesivos;
	}

	public void setAdhesivos(Set<Adhesivo> adhesivos) {
		this.adhesivos = adhesivos;
	}

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

}
