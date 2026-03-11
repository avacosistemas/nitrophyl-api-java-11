package ar.com.avaco.nitrophyl.ws.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class OrdenCompraDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private Long idCliente;

	private String cliente;

	private String comprobante;

	private String fecha;

	private ArchivoDTO archivo;

	private List<OrdenCompraDetalleDTO> detalle = new ArrayList<OrdenCompraDetalleDTO>();

	public void setArchivo(ArchivoDTO archivo) {
		this.archivo = archivo;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public List<OrdenCompraDetalleDTO> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<OrdenCompraDetalleDTO> detalle) {
		this.detalle = detalle;
	}

	public ArchivoDTO getArchivo() {
		return archivo;
	}

}
