package ar.com.avaco.nitrophyl.domain.entities.fabricacion;

import java.time.LocalDate;
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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import ar.com.avaco.arc.sec.domain.Usuario;
import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "ORDEN_FABRICACION", uniqueConstraints = {
		@UniqueConstraint(name = "UK_ORDEN_FABRICACION_NUMERO_ANIO", columnNames = { "numero", "anio" }) })
public class OrdenFabricacion extends AuditableEntity<Long> {

	private static final long serialVersionUID = 7569818075580326730L;

	@Id
	@GeneratedValue(generator = "ORDEN_FABRICACION_SEQ")
	@GenericGenerator(name = "ORDEN_FABRICACION_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "ORDEN_FABRICACION_SEQ"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_ORDEN_FABRICACION", unique = true, nullable = false)
	private Long id;

	@Column(name = "FECHA")
	private LocalDate fecha;

	// numero y anio combinados son unique.
	@Column(name = "NUMERO", nullable = false, updatable = false)
	private Long numero;

	@Column(name = "ANIO", nullable = false, updatable = false)
	private Integer anio;

	/**
	 * Cada orden de fabricacion esta asociado a un item de entrega de la OC.
	 */
	@ManyToOne
	@JoinColumn(name = "ID_OC_DET_PEDIDO", nullable = false)
	private OrdenCompraDetallePedido ordenCompraDetalle;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO")
	private EstadoOrdenFabricacion estado;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ordenFabricacion", orphanRemoval = true)
	private Set<OrdenFabricacionEntrega> entregas = new HashSet<OrdenFabricacionEntrega>();

	@ManyToOne
	@JoinColumn(name = "ID_SEC_FAB", nullable = true)
	private SectorFabrica sector;

	@ManyToOne
	@JoinColumn(name = "ID_MAQ_FAB", nullable = true)
	private MaquinaFabrica maquina;

	@ManyToOne
	@JoinColumn(name = "ID_OPERARIO")
	private Usuario operario;

	@Column(name = "POSICION")
	private Integer posicion;

	@Transient
	public String getNumeroOT() {
		return StringUtils.leftPad(this.getNumero().toString(), 3, "0") + "/"
				+ this.getAnio().toString().substring(2, 4);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public OrdenCompraDetallePedido getOrdenCompraDetalle() {
		return ordenCompraDetalle;
	}

	public void setOrdenCompraDetalle(OrdenCompraDetallePedido ordenCompraDetalle) {
		this.ordenCompraDetalle = ordenCompraDetalle;
	}

	public EstadoOrdenFabricacion getEstado() {
		return estado;
	}

	public void setEstado(EstadoOrdenFabricacion estado) {
		this.estado = estado;
	}

	public Set<OrdenFabricacionEntrega> getEntregas() {
		return entregas;
	}

	public void setEntregas(Set<OrdenFabricacionEntrega> entregas) {
		this.entregas = entregas;
	}

	public SectorFabrica getSector() {
		return sector;
	}

	public void setSector(SectorFabrica sector) {
		this.sector = sector;
	}

	public MaquinaFabrica getMaquina() {
		return maquina;
	}

	public void setMaquina(MaquinaFabrica maquina) {
		this.maquina = maquina;
	}

	public Usuario getOperario() {
		return operario;
	}

	public void setOperario(Usuario operario) {
		this.operario = operario;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

}
