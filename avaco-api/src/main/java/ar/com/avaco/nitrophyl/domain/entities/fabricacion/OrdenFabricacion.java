package ar.com.avaco.nitrophyl.domain.entities.fabricacion;

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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;

@Entity
@Table(name = "ORDEN_FABRICACION", uniqueConstraints = {
		@UniqueConstraint(name = "UK_ORDEN_FABRICACION_NUMERO_ANIO", columnNames = { "numero", "anio" }) })
public class OrdenFabricacion extends AuditableEntity<Long> {

	private static final long serialVersionUID = 7569818075580326730L;

	@Id
	@GeneratedValue(generator = "ORDEN_FABRICACION_SEQ")
	@GenericGenerator(name = "ORDEN_FABRICACION_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "ORDEN_FABRICACION_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_ORDEN_FABRICACION", unique = true, nullable = false)
	private Long id;

	@Column(name = "FECHA")
	private Date fecha;

	// numero y anio combinados son unique.
	@Column(name = "NUMERO", nullable = false, updatable = false)
	private Long numero;

	@Column(name = "ANIO", nullable = false, updatable = false)
	private Long anio;

	// Solo permitir seleccionar las que esten en estado pendiente o asignacion
	// parcial
	// Debe filtrarse solo las del cliente seleccionado. Puede ser null si la orden
	// es interna
	@ManyToOne
	@JoinColumn(name = "ID_ORDEN_COMPRA", nullable = false)
	private OrdenCompra ordenCompra;

	// Si el cliente es null, entonces es interna propia de nitro
	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE", nullable = false)
	private Cliente cliente;

	// Revisar los demás estados. llegar solo hasta pendiente asignacion
	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO")
	private EstadoOrdenFabricacion estado;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ordenFabricacion", orphanRemoval = true)
	private Set<OrdenFabricacionDetalle> detalle = new HashSet<OrdenFabricacionDetalle>();

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

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Long getAnio() {
		return anio;
	}

	public void setAnio(Long anio) {
		this.anio = anio;
	}

	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public EstadoOrdenFabricacion getEstado() {
		return estado;
	}

	public void setEstado(EstadoOrdenFabricacion estado) {
		this.estado = estado;
	}

	public Set<OrdenFabricacionDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(Set<OrdenFabricacionDetalle> detalle) {
		this.detalle = detalle;
	}

}
