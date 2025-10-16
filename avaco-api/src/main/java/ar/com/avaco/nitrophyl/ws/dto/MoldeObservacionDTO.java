/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeObservacion;
import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class MoldeObservacionDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private Long idMolde;

	private String observacion;

	public MoldeObservacionDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public MoldeObservacionDTO(MoldeObservacion obs) {
		super(obs);
		this.idMolde = obs.getIdMolde();
		this.observacion = obs.getObservacion();
		this.id = obs.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMolde() {
		return idMolde;
	}

	public void setIdMolde(Long idMolde) {
		this.idMolde = idMolde;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
