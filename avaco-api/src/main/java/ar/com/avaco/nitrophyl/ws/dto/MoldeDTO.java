/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.avaco.nitrophyl.domain.entities.molde.TipoMolde;
import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class MoldeDTO extends DTOAuditableEntity<Long> {

	private Long id;
	private String codigo;
	private String estado;
	private String nombre;
	private String ubicacion;
	private boolean propio;
	private String observaciones;
	private String clienteDuenio;
	private Long idClienteDuenio;
	private String observacionesEstado;
	private List<PiezaTipoDTO> piezaTipos = new ArrayList<PiezaTipoDTO>();
	private Integer cantidadBocas;

	private TipoMolde tipoMolde;
	private Double alto;
	private Double ancho;
	private Double profundidad;
	private Double diametro;

	private String troquel;
	private Long idTroquel;

	public String getTroquel() {
		return troquel;
	}

	public void setTroquel(String troquel) {
		this.troquel = troquel;
	}

	public Long getIdTroquel() {
		return idTroquel;
	}

	public void setIdTroquel(Long idTroquel) {
		this.idTroquel = idTroquel;
	}

	public TipoMolde getTipoMolde() {
		return tipoMolde;
	}

	public void setTipoMolde(TipoMolde tipoMolde) {
		this.tipoMolde = tipoMolde;
	}

	public Double getAlto() {
		return alto;
	}

	public void setAlto(Double alto) {
		this.alto = alto;
	}

	public Double getAncho() {
		return ancho;
	}

	public void setAncho(Double ancho) {
		this.ancho = ancho;
	}

	public Double getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(Double profundidad) {
		this.profundidad = profundidad;
	}

	public Double getDiametro() {
		return diametro;
	}

	public void setDiametro(Double diametro) {
		this.diametro = diametro;
	}

	public Integer getCantidadBocas() {
		return cantidadBocas;
	}

	public void setCantidadBocas(Integer cantidadBocas) {
		this.cantidadBocas = cantidadBocas;
	}

	public String getObservacionesEstado() {
		return observacionesEstado;
	}

	public void setObservacionesEstado(String observacionesEstado) {
		this.observacionesEstado = observacionesEstado;
	}

	public List<PiezaTipoDTO> getPiezaTipos() {
		return piezaTipos;
	}

	public void setPiezaTipos(List<PiezaTipoDTO> piezaTipos) {
		this.piezaTipos = piezaTipos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public boolean isPropio() {
		return propio;
	}

	public void setPropio(boolean propio) {
		this.propio = propio;
	}

	public String getClienteDuenio() {
		return clienteDuenio;
	}

	public void setClienteDuenio(String clienteDuenio) {
		this.clienteDuenio = clienteDuenio;
	}

	public Long getIdClienteDuenio() {
		return idClienteDuenio;
	}

	public void setIdClienteDuenio(Long idClienteDuenio) {
		this.idClienteDuenio = idClienteDuenio;
	}

}
