package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class InsumoTratadoObservacionControlDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private String observacion;

	private Boolean controlar;

	public InsumoTratadoObservacionControlDTO() {
	}

	public InsumoTratadoObservacionControlDTO(Long id, String observacion, Boolean controlar) {
		super();
		this.id = id;
		this.observacion = observacion;
		this.controlar = controlar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Boolean getControlar() {
		return controlar;
	}

	public void setControlar(Boolean controlar) {
		this.controlar = controlar;
	}

}
