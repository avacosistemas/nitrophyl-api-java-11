package ar.com.avaco.nitrophyl.ws.dto;

import java.util.List;

import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class EsquemaDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private Long idProceso;

	private String titulo;

	private List<EsquemaPasoDTO> pasos;

	private byte[] imagen;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<EsquemaPasoDTO> getPasos() {
		return pasos;
	}

	public void setPasos(List<EsquemaPasoDTO> pasos) {
		this.pasos = pasos;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
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
