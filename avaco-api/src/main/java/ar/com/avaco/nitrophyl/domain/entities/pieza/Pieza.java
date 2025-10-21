package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.ws.dto.PiezaGrillaDTO;

@SqlResultSetMapping(name = "PiezaGrillaDTOMapper", classes = {
		@ConstructorResult(targetClass = PiezaGrillaDTO.class, columns = {
				@ColumnResult(name = "rows", type = Integer.class),
				@ColumnResult(name = "denominacion", type = String.class),
				@ColumnResult(name = "idPieza", type = Integer.class),
				@ColumnResult(name = "codigo", type = String.class),
				@ColumnResult(name = "vigente", type = Boolean.class),
				@ColumnResult(name = "revision", type = Integer.class),
				@ColumnResult(name = "fechaRevision", type = Date.class),
				@ColumnResult(name = "tipo", type = String.class),
				@ColumnResult(name = "material", type = String.class),
				@ColumnResult(name = "puedeGenerarRevision", type = Boolean.class),
				@ColumnResult(name = "formula", type = String.class),
				@ColumnResult(name = "puedeMarcarVigente", type = Boolean.class) }) })

@Entity
@Table(name = "PIEZA")
public class Pieza extends AuditableEntity<Long> {

	private static final long serialVersionUID = 2865115894963877402L;

	@Id
	@GeneratedValue(generator = "PIEZA_SEQ")
	@GenericGenerator(name = "PIEZA_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "PIEZA_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_PIEZA", unique = true, nullable = false)
	private Long id;

	/**
	 * Nombre de la pieza.
	 */
	@Column(name = "DENOMINACION", nullable = false)
	private String denominacion;

	/**
	 * Tipo de pieza.
	 */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "ID_TIPO")
	private PiezaTipo tipo;

	/**
	 * Listado de dimensiones de la pieza.
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pieza")
	@Fetch(FetchMode.SELECT)
	private Set<PiezaDimension> dimensiones = new HashSet<>();

	/**
	 * Codigo univoco de pieza.
	 */
	@Column(name = "CODIGO", nullable = false)
	private String codigo;

	/**
	 * Detalles de la formula.
	 */
	@Embedded
	private PiezaFormula detalleFormula;

	/**
	 * Planos asociados a la pieza
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pieza")
	@Fetch(FetchMode.SELECT)
	private Set<PiezaPlano> planos = new HashSet<>();

	/**
	 * Insumos de la pieza con su tratamiento y pegamento.
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pieza")
	@Fetch(FetchMode.SELECT)
	private Set<InsumoTratado> insumos = new HashSet<>();

	/**
	 * Listado de moldes asociados de la pieza.
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pieza")
	private Set<PiezaMolde> moldes = new HashSet<>();

	/**
	 * Listado de espesores asociados de la pieza.
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pieza", orphanRemoval = true)
	private Set<PiezaEspesor> espesores = new HashSet<>();

	/**
	 * Proceso de creacion de la pieza.
	 */
	@OneToOne(mappedBy = "pieza", cascade = CascadeType.ALL, orphanRemoval = true)
	private Proceso proceso;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pieza")
	private Set<PiezaCliente> clientes = new HashSet<>();

	/**
	 * Revision.
	 */
	@Column(name = "REVISION", nullable = false, unique = true)
	private Long revision;

	/**
	 * Fecha de la revision.
	 */
	@Column(name = "FECHA_REVISION", nullable = false, unique = true)
	private Date fechaRevision;

	/**
	 * Determina si la revision de la pieza es la vigente.
	 */
	@Column(name = "VIGENTE", nullable = false)
	private Boolean vigente;

	/**
	 * Fecha en que se creo la pieza.
	 */
	@Column(name = "FECHA_CREACION_PROCESO")
	private Date fechaCreacionPiezaProceso;

	/**
	 * Observaciones de la revision.
	 */
	@Column(name = "OBSERVACIONES_REVISION")
	private String observacionesRevision;

	public static Pieza ofId(Long id) {
		Pieza pieza = new Pieza();
		pieza.setId(id);
		return pieza;
	}

	public Pieza clonar(String username, Date fechaHora) {
		Pieza clonada = new Pieza();
		clonada.resetearCreacion(username, fechaHora);

		this.clientes.forEach(cliente -> clonada.getClientes().add(cliente.clonar(username, fechaHora, clonada)));

		clonada.setCodigo(codigo);
		clonada.setDenominacion(this.denominacion);
		clonada.setTipo(tipo);
		clonada.setDetalleFormula(detalleFormula);

		clonada.setFechaCreacionPiezaProceso(this.fechaCreacionPiezaProceso);
		clonada.setFechaRevision(fechaHora);

		this.espesores.forEach(espesor -> clonada.getEspesores().add(espesor.clonar(username, fechaHora, clonada)));
		this.dimensiones
				.forEach(dimension -> clonada.getDimensiones().add(dimension.clonar(username, fechaHora, clonada)));
		this.insumos.forEach(insumo -> clonada.getInsumos().add(insumo.clonar(username, fechaHora, clonada)));
		this.moldes.forEach(molde -> clonada.getMoldes().add(molde.clonar(username, fechaHora, clonada)));
		this.planos.forEach(plano -> clonada.getPlanos().add(plano.clonar(username, fechaHora, clonada)));

		clonada.setProceso(proceso.clonar(username, fechaHora, clonada));

		clonada.setRevision(revision + 1);

		clonada.setVigente(false);

		return clonada;
	}

	public Set<PiezaEspesor> getEspesores() {
		return espesores;
	}

	public void setEspesores(Set<PiezaEspesor> espesores) {
		this.espesores = espesores;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public PiezaTipo getTipo() {
		return tipo;
	}

	public void setTipo(PiezaTipo tipo) {
		this.tipo = tipo;
	}

	public String getObservacionesRevision() {
		return observacionesRevision;
	}

	public void setObservacionesRevision(String observacionesRevision) {
		this.observacionesRevision = observacionesRevision;
	}

	public Set<PiezaDimension> getDimensiones() {
		return dimensiones;
	}

	public Set<PiezaPlano> getPlanos() {
		return planos;
	}

	public void setPlanos(Set<PiezaPlano> planos) {
		this.planos = planos;
	}

	public Boolean getVigente() {
		return vigente;
	}

	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}

	public void setDimensiones(Set<PiezaDimension> dimensiones) {
		this.dimensiones = dimensiones;
	}

	public Set<InsumoTratado> getInsumos() {
		return insumos;
	}

	public void setInsumos(Set<InsumoTratado> insumos) {
		this.insumos = insumos;
	}

	public Set<PiezaMolde> getMoldes() {
		return moldes;
	}

	public void setMoldes(Set<PiezaMolde> moldes) {
		this.moldes = moldes;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Long getRevision() {
		return revision;
	}

	public void setRevision(Long revision) {
		this.revision = revision;
	}

	public Date getFechaRevision() {
		return fechaRevision;
	}

	public void setFechaRevision(Date fechaRevision) {
		this.fechaRevision = fechaRevision;
	}

	public Date getFechaCreacionPiezaProceso() {
		return fechaCreacionPiezaProceso;
	}

	public void setFechaCreacionPiezaProceso(Date fechaCreacionPiezaProceso) {
		this.fechaCreacionPiezaProceso = fechaCreacionPiezaProceso;
	}

	public PiezaFormula getDetalleFormula() {
		return detalleFormula;
	}

	public void setDetalleFormula(PiezaFormula detalleFormula) {
		this.detalleFormula = detalleFormula;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Set<PiezaCliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<PiezaCliente> clientes) {
		this.clientes = clientes;
	}

}
