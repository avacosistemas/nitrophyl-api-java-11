package ar.com.avaco.nitrophyl.ws.dto;

import java.util.Date;

import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class PiezaClienteDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private Long idCliente;

	private Long idPieza;

	private String nombreCliente;

	private String nombrePiezaPersonalizado;

	private Double cotizacion;

	private Date fechaCotizacion;

	private String observacionesCotizacion;

	private String denominacion;

	private String codigo;

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Double getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Double cotizacion) {
		this.cotizacion = cotizacion;
	}

	public Date getFechaCotizacion() {
		return fechaCotizacion;
	}

	public void setFechaCotizacion(Date fechaCotizacion) {
		this.fechaCotizacion = fechaCotizacion;
	}

	public String getObservacionesCotizacion() {
		return observacionesCotizacion;
	}

	public void setObservacionesCotizacion(String observacionesCotizacion) {
		this.observacionesCotizacion = observacionesCotizacion;
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

	public Long getIdPieza() {
		return idPieza;
	}

	public void setIdPieza(Long idPieza) {
		this.idPieza = idPieza;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNombrePiezaPersonalizado() {
		return nombrePiezaPersonalizado;
	}

	public void setNombrePiezaPersonalizado(String nombrePiezaPersonalizado) {
		this.nombrePiezaPersonalizado = nombrePiezaPersonalizado;
	}

}
