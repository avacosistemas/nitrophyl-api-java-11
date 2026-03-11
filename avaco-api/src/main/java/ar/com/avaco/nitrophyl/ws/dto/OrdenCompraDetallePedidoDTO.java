package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class OrdenCompraDetallePedidoDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private Integer cantidad;

	private String fechaEntregaSolicitada;

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getFechaEntregaSolicitada() {
		return fechaEntregaSolicitada;
	}

	public void setFechaEntregaSolicitada(String fechaEntregaSolicitada) {
		this.fechaEntregaSolicitada = fechaEntregaSolicitada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
