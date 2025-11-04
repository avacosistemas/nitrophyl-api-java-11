package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "INSUMO_STOCK_HISTORIAL")
public class InsumoStockHistorial extends AuditableEntity<Long> {

	private static final long serialVersionUID = -8973045251658414743L;

	@Id
	@GeneratedValue(generator = "INSUMO_STOCK_HISTORIAL_SEQ")
	@GenericGenerator(name = "INSUMO_STOCK_HISTORIAL_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "INSUMO_STOCK_HISTORIAL_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_INSUMO_STOCK_HISTORIAL", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_INSUMO", insertable = true, updatable = false)
	private Insumo insumo;

	@Column(name = "FECHA")
	private Date fecha;

	@Column(name = "CANTIDAD")
	private Double cantidad;

	@Column(name = "TIPO_MOVIMIENTO")
	@Enumerated(EnumType.STRING)
	private TipoMovimientoStock tipo;

	/**
	 * Campo en el cual se describira como se consumieron o ingresaron los items.
	 */
	@Column(name = "OBSERVACIONES")
	private String observaciones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public TipoMovimientoStock getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimientoStock tipo) {
		this.tipo = tipo;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
