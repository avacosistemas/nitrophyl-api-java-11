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
	private Integer alto;
	private Integer ancho;
	private Integer profundidad;
	private Integer diametro;

	public TipoMolde getTipoMolde() {
		return tipoMolde;
	}

	public void setTipoMolde(TipoMolde tipoMolde) {
		this.tipoMolde = tipoMolde;
	}

	public Integer getAlto() {
		return alto;
	}

	public void setAlto(Integer alto) {
		this.alto = alto;
	}

	public Integer getAncho() {
		return ancho;
	}

	public void setAncho(Integer ancho) {
		this.ancho = ancho;
	}

	public Integer getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(Integer profundidad) {
		this.profundidad = profundidad;
	}

	public Integer getDiametro() {
		return diametro;
	}

	public void setDiametro(Integer diametro) {
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
