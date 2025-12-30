package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.nitrophyl.domain.entities.molde.PlanoClasificacion;
import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class PiezaPlanoDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private Long idPieza;

	private byte[] archivo;

	private String codigo;

	private String revision;

	private PlanoClasificacion clasificacion;

	private String observaciones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdPieza() {
		return idPieza;
	}

	public void setIdPieza(Long idPieza) {
		this.idPieza = idPieza;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public PlanoClasificacion getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(PlanoClasificacion clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
