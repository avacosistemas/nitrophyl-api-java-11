/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.nitrophyl.domain.entities.molde.EstadoBoca;
import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class MoldeBocaDTO extends DTOAuditableEntity<Long> {

	private Long id;
	private Integer nroBoca;
	private EstadoBoca estado;
	private String descripcion;
	private String observacionesEstado;
	private Long idMolde;

	public Long getIdMolde() {
		return idMolde;
	}

	public void setIdMolde(Long idMolde) {
		this.idMolde = idMolde;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservacionesEstado() {
		return observacionesEstado;
	}

	public void setObservacionesEstado(String observacionesEstado) {
		this.observacionesEstado = observacionesEstado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getNroBoca() {
		return nroBoca;
	}

	public void setNroBoca(Integer nroBoca) {
		this.nroBoca = nroBoca;
	}

	public EstadoBoca getEstado() {
		return estado;
	}

	public void setEstado(EstadoBoca estado) {
		this.estado = estado;
	}

}
