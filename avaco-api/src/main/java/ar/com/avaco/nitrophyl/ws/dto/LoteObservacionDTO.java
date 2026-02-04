package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class LoteObservacionDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private String observaciones;

	private Boolean mostrarReporte;

	private Long idLote;

	public Long getIdLote() {
		return idLote;
	}

	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Boolean getMostrarReporte() {
		return mostrarReporte;
	}

	public void setMostrarReporte(Boolean mostrarReporte) {
		this.mostrarReporte = mostrarReporte;
	}

}
