package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class ClienteDTO extends DTOEntity<Long> {

	private Long id;
	private String razonSocial;
	private String nombre;
	private String domicilio;
	private String codigoPostal;
	private String localidad;
	private String provincia;
	private String email;
	private String webSite;
	private String cuit;
	private String observacionesCobranzas;
	private String observacionesEntrega;
	private String observacionesFacturacion;
	private String telefono;
	private Boolean activo;
	private String empresa;

	public ClienteDTO() {
	}

	public String getLabelCombo() {
		return this.nombre + " (" + this.razonSocial + ")";
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getObservacionesFacturacion() {
		return observacionesFacturacion;
	}

	public void setObservacionesFacturacion(String observacionesFacturacion) {
		this.observacionesFacturacion = observacionesFacturacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getObservacionesCobranzas() {
		return observacionesCobranzas;
	}

	public void setObservacionesCobranzas(String observacionesCobranzas) {
		this.observacionesCobranzas = observacionesCobranzas;
	}

	public String getObservacionesEntrega() {
		return observacionesEntrega;
	}

	public void setObservacionesEntrega(String observacionesEntrega) {
		this.observacionesEntrega = observacionesEntrega;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

}
