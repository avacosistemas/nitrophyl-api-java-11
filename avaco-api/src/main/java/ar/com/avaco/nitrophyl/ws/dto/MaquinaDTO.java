package ar.com.avaco.nitrophyl.ws.dto;

import java.util.Date;

import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class MaquinaDTO extends DTOAuditableEntity<Long> {

	private Long id;
	private String nombre;
	private String estado;
	private String observacionesReporte;
	private int posicion;
	private boolean versionable;
	private String norma;
	private Date fechaUltimaCalibracion;
	private Integer perioricidadCalibracion;

	public Date getFechaUltimaCalibracion() {
		return fechaUltimaCalibracion;
	}

	public void setFechaUltimaCalibracion(Date fechaUltimaCalibracion) {
		this.fechaUltimaCalibracion = fechaUltimaCalibracion;
	}

	public Integer getPerioricidadCalibracion() {
		return perioricidadCalibracion;
	}

	public void setPerioricidadCalibracion(Integer perioricidadCalibracion) {
		this.perioricidadCalibracion = perioricidadCalibracion;
	}

	public boolean isVersionable() {
		return versionable;
	}

	public void setVersionable(boolean versionable) {
		this.versionable = versionable;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getObservacionesReporte() {
		return observacionesReporte;
	}

	public void setObservacionesReporte(String observacionesReporte) {
		this.observacionesReporte = observacionesReporte;
	}

	public String getNorma() {
		return norma;
	}

	public void setNorma(String norma) {
		this.norma = norma;
	}

}
