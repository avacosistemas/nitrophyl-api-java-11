package ar.com.avaco.ws.rest.dto;

import java.io.Serializable;
import java.util.Date;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

public abstract class DTOAuditableEntity<ID extends Serializable> extends DTOEntity<ID> {

	private String usuarioCreacion;

	private Date fechaCreacion;

	private String usuarioActualizacion;

	private Date fechaActualizacion;

	public DTOAuditableEntity() {
		// TODO Auto-generated constructor stub
	}

	public DTOAuditableEntity(AuditableEntity<ID> entity) {
		this.usuarioCreacion = entity.getUsuarioCreacion();
		this.fechaCreacion = entity.getFechaCreacion();
		this.usuarioActualizacion = entity.getUsuarioActualizacion();
		this.fechaActualizacion = entity.getFechaActualizacion();
	}
	
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioActualizacion() {
		return usuarioActualizacion;
	}

	public void setUsuarioActualizacion(String usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

}
