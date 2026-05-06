package ar.com.avaco.nitrophyl.ws.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class OrdenCompraDetalleDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private Long idCotizacion;

	private String fechaCotizacion;

	private Double valorCotizacion;

	private Long idPieza;

	private String pieza;

	private List<OrdenCompraDetallePedidoDTO> entregasSolicitadas = new ArrayList<OrdenCompraDetallePedidoDTO>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCotizacion() {
		return idCotizacion;
	}

	public void setIdCotizacion(Long idCotizacion) {
		this.idCotizacion = idCotizacion;
	}

	public String getFechaCotizacion() {
		return fechaCotizacion;
	}

	public void setFechaCotizacion(String fechaCotizacion) {
		this.fechaCotizacion = fechaCotizacion;
	}

	public Double getValorCotizacion() {
		return valorCotizacion;
	}

	public void setValorCotizacion(Double valorCotizacion) {
		this.valorCotizacion = valorCotizacion;
	}

	public Long getIdPieza() {
		return idPieza;
	}

	public void setIdPieza(Long idPieza) {
		this.idPieza = idPieza;
	}

	public String getPieza() {
		return pieza;
	}

	public void setPieza(String pieza) {
		this.pieza = pieza;
	}

	public List<OrdenCompraDetallePedidoDTO> getEntregasSolicitadas() {
		return entregasSolicitadas;
	}

	public void setEntregasSolicitadas(List<OrdenCompraDetallePedidoDTO> entregasSolicitadas) {
		this.entregasSolicitadas = entregasSolicitadas;
	}

}
