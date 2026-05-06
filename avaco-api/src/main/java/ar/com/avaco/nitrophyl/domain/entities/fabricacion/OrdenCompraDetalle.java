package ar.com.avaco.nitrophyl.domain.entities.fabricacion;

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

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.cliente.Cotizacion;

@Entity
@Table(name = "ORDEN_COMPRA_DETALLE")
public class OrdenCompraDetalle extends AuditableEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8702524794147610427L;

	@Id
	@GeneratedValue(generator = "ORDEN_COMPRA_DETALLE_SEQ")
	@GenericGenerator(name = "ORDEN_COMPRA_DETALLE_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "ORDEN_COMPRA_DETALLE_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_ORDEN_COMPRA_DETALLE", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_ORDEN_COMPRA", nullable = false)
	private OrdenCompra ordenCompra;

	@ManyToOne
	@JoinColumn(name = "ID_PIEZA", nullable = false)
	private Pieza pieza;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_COTIZACION", nullable = false)
	private Cotizacion cotizacion;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "ordenCompraDetalle", orphanRemoval = true)
	private Set<OrdenCompraDetallePedido> entregasSolicitadas = new HashSet<OrdenCompraDetallePedido>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	public Cotizacion getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	public Set<OrdenCompraDetallePedido> getEntregasSolicitadas() {
		return entregasSolicitadas;
	}

	public void setEntregasSolicitadas(Set<OrdenCompraDetallePedido> entregasSolicitadas) {
		this.entregasSolicitadas = entregasSolicitadas;
	}

}
