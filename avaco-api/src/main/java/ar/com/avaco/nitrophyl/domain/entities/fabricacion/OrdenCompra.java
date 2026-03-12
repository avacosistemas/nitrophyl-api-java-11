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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;

@Entity
@Table(name = "ORDEN_COMPRA")
public class OrdenCompra extends AuditableEntity<Long> {

	private static final long serialVersionUID = -1168818575560524811L;

	@Id
	@GeneratedValue(generator = "ORDEN_COMPRA_SEQ")
	@GenericGenerator(name = "ORDEN_COMPRA_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "ORDEN_COMPRA_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_ORDEN_COMPRA", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE", nullable = false)
	private Cliente cliente;

	@Column(name = "ESTADO")
	@Enumerated(EnumType.STRING)
	private OrdenCompraEstado estado;

	@Column(name = "COMPROBANTE")
	private String comprobante;

	@Column(name = "FECHA")
	private LocalDate fecha;

	@OneToOne(mappedBy = "ordenDeCompra", cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
	private OrdenCompraArchivo archivo;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "ordenCompra", orphanRemoval = true)
	private Set<OrdenCompraDetalle> detalle = new HashSet<OrdenCompraDetalle>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public OrdenCompraEstado getEstado() {
		return estado;
	}

	public void setEstado(OrdenCompraEstado estado) {
		this.estado = estado;
	}

	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	public OrdenCompraArchivo getArchivo() {
		return archivo;
	}

	public void setArchivo(OrdenCompraArchivo archivo) {
		this.archivo = archivo;
		if (archivo != null) {
	        archivo.setOrdenDeCompra(this);
	    }
	}

	public Set<OrdenCompraDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(Set<OrdenCompraDetalle> detalle) {
		this.detalle = detalle;
	}

}
