package ar.com.avaco.nitrophyl.ws.dto;

import java.util.Date;

import ar.com.avaco.nitrophyl.domain.entities.pieza.Insumo;
import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoMovimientoStock;
import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class InsumoStockHistorialDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private Insumo insumo;

	private Date fecha;

	private Double cantidad;

	private TipoMovimientoStock tipo;

	/**
	 * Campo en el cual se describira como se consumieron o ingresaron los items.
	 */
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
