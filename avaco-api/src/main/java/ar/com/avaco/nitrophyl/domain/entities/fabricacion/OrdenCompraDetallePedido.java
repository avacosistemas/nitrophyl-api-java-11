package ar.com.avaco.nitrophyl.domain.entities.fabricacion;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "ORDEN_COMPRA_DETALLE_PEDIDO")
public class OrdenCompraDetallePedido extends AuditableEntity<Long> {

	private static final long serialVersionUID = 8702524794147610427L;

	@Id
	@GeneratedValue(generator = "ORDEN_COMPRA_DETALLE_PEDIDO_SEQ")
	@GenericGenerator(name = "ORDEN_COMPRA_DETALLE_PEDIDO_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "ORDEN_COMPRA_DETALLE_PEDIDO_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_ORDEN_COMPRA_DETALLE_PEDIDO", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_ORDEN_COMPRA_DETALLE", nullable = false)
	private OrdenCompraDetalle ordenCompraDetalle;

	@Column(name = "CANTIDAD", nullable = false)
	private Integer cantidad;

	@Column(name = "FECHA_ENTREGA_SOLICITADA", nullable = false)
	private LocalDate fechaEntregaSolicitada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrdenCompraDetalle getOrdenCompraDetalle() {
		return ordenCompraDetalle;
	}

	public void setOrdenCompraDetalle(OrdenCompraDetalle ordenCompraDetalle) {
		this.ordenCompraDetalle = ordenCompraDetalle;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDate getFechaEntregaSolicitada() {
		return fechaEntregaSolicitada;
	}

	public void setFechaEntregaSolicitada(LocalDate fechaEntregaSolicitada) {
		this.fechaEntregaSolicitada = fechaEntregaSolicitada;
	}

}
