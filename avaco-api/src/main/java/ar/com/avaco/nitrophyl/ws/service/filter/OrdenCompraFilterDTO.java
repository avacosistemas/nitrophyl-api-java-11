package ar.com.avaco.nitrophyl.ws.service.filter;

import ar.com.avaco.nitrophyl.ws.dto.SortPageDTO;

public class OrdenCompraFilterDTO extends SortPageDTO {

	private Long idCliente;

	private String fechaDesde;

	private String fechaHasta;

	private String comprobante;

	private String estado;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

}
