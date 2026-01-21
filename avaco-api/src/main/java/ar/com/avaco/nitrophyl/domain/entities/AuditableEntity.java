package ar.com.avaco.nitrophyl.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import ar.com.avaco.arc.core.domain.Entity;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity<T extends Serializable> extends Entity<T> {

	private static final long serialVersionUID = -8258576478153946986L;

	@CreatedBy
	@Column(name = "USUARIO_CREACION", nullable = false, updatable = false)
	private String usuarioCreacion;

	@CreatedDate
	@Column(name = "FECHA_CREACION", nullable = false, updatable = false)
	private Date fechaCreacion;

	@LastModifiedBy
	@Column(name = "USUARIO_ACTUALIZACION", nullable = false)
	private String usuarioActualizacion;

	@LastModifiedDate
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
