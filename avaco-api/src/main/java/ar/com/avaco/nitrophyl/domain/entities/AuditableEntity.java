package ar.com.avaco.nitrophyl.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import ar.com.avaco.arc.core.domain.Entity;

@MappedSuperclass
public abstract class AuditableEntity<T extends Serializable> extends Entity<T> {

	private static final long serialVersionUID = -8258576478153946986L;

	@Column(name = "USUARIO_CREACION", nullable = false, updatable = false)
	private String usuarioCreacion;

	@Column(name = "FECHA_CREACION", nullable = false, updatable = false)
	private Date fechaCreacion;

	@Column(name = "USUARIO_ACTUALIZACION", nullable = false)
	private String usuarioActualizacion;

	@Column(name = "FECHA_ACTUALIZACION", nullable = false)
	private Date fechaActualizacion;

	public void resetearCreacion(String username, Date fechaHora) {
		this.setId(null);
		this.fechaActualizacion = null;
		this.fechaCreacion = fechaHora;
		this.usuarioCreacion = username;
		this.usuarioActualizacion = null;
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
