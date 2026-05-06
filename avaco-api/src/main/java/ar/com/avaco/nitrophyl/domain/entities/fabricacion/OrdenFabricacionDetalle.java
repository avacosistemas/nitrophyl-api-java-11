package ar.com.avaco.nitrophyl.domain.entities.fabricacion;

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
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;

@Entity
@Table(name = "ORDEN_FABRICACION_DETALLE")
public class OrdenFabricacionDetalle extends AuditableEntity<Long> {

	private static final long serialVersionUID = -3488533801030554441L;

	@Id
	@GeneratedValue(generator = "ORDEN_FAB_DET_SEQ")
	@GenericGenerator(name = "ORDEN_FAB_DET_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "ORDEN_FAB_DET_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_ORDEN_FAB_DET", unique = true, nullable = false)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_ORDEN_FABRICACION")
	private OrdenFabricacion ordenFabricacion;

	@Column(name = "CANTIDAD")
	private Integer cantidad;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_PIEZA")
	private Pieza pieza;

	@Column(name = "FECHA_ENTREGA_ESTIMADA")
	private Date fechaEntregaEstimada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	public Date getFechaEntregaEstimada() {
		return fechaEntregaEstimada;
	}

	public void setFechaEntregaEstimada(Date fechaEntregaEstimada) {
		this.fechaEntregaEstimada = fechaEntregaEstimada;
	}

	public OrdenFabricacion getOrdenFabricacion() {
		return ordenFabricacion;
	}

	public void setOrdenFabricacion(OrdenFabricacion ordenFabricacion) {
		this.ordenFabricacion = ordenFabricacion;
	}

}
