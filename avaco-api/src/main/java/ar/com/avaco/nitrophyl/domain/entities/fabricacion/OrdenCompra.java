package ar.com.avaco.nitrophyl.domain.entities.fabricacion;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	// ASIGNADA PARCIAL
	// ASIGNADA TOTAL
	// Si esta pendiente o asignada parcial debería poder generse una orden de
	// fabricacion
	@Column(name = "ESTADO")
	private String estado;

	@Column(name = "NRO_COMPROBANTE")
	private String nroComprobante;

	// codigo del cliente + secuencial + anio sugerir
	@Column(name = "NRO_INTERNO")
	private String nroInterno;

	@Column(name = "FECHA")
	private Date fecha;

	@OneToOne(mappedBy = "ordenDeCompra", cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
	private OrdenCompraArchivo archivo;

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

	public String getNroComprobante() {
		return nroComprobante;
	}

	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	public String getNroInterno() {
		return nroInterno;
	}

	public void setNroInterno(String nroInterno) {
		this.nroInterno = nroInterno;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
