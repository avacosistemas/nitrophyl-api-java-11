package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class TerminacionDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private Long idProceso;

	private String refilado;

	private String identificacion;

	private String embalaje;

	private byte[] imagenTerminada;

	public String getRefilado() {
		return refilado;
	}

	public void setRefilado(String refilado) {
		this.refilado = refilado;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getEmbalaje() {
		return embalaje;
	}

	public void setEmbalaje(String embalaje) {
		this.embalaje = embalaje;
	}

	public byte[] getImagenTerminada() {
		return imagenTerminada;
	}

	public void setImagenTerminada(byte[] imagenTerminada) {
		this.imagenTerminada = imagenTerminada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(Long idProceso) {
		this.idProceso = idProceso;
	}

}
