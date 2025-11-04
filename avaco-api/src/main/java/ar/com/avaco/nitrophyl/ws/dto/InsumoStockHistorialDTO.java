package ar.com.avaco.nitrophyl.ws.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoMovimientoStock;
import ar.com.avaco.nitrophyl.domain.entities.pieza.UnidadMedida;
import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class InsumoStockHistorialDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private Long idInsumo;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date fecha;

	private Double cantidad;

	private TipoMovimientoStock tipo;

	private String observaciones;

	private UnidadMedida unidadMedida;

	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdInsumo() {
		return idInsumo;
	}

	public void setIdInsumo(Long idInsumo) {
		this.idInsumo = idInsumo;
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
