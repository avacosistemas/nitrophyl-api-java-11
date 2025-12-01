/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

import java.util.HashSet;
import java.util.Set;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class PiezaPUTDTO extends DTOEntity<Long> {

	private Long id;

	private Double pesoCrudo;

	private String observacionesPesoCrudo;

	private String observacionesRevision;

	private Set<EspesorDTO> espesores = new HashSet<>();

	private String hojaProceso;

	public String getHojaProceso() {
		return hojaProceso;
	}

	public void setHojaProceso(String hojaProceso) {
		this.hojaProceso = hojaProceso;
	}

	public Set<EspesorDTO> getEspesores() {
		return espesores;
	}

	public void setEspesores(Set<EspesorDTO> espesores) {
		this.espesores = espesores;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPesoCrudo() {
		return pesoCrudo;
	}

	public void setPesoCrudo(Double pesoCrudo) {
		this.pesoCrudo = pesoCrudo;
	}

	public String getObservacionesPesoCrudo() {
		return observacionesPesoCrudo;
	}

	public void setObservacionesPesoCrudo(String observacionesPesoCrudo) {
		this.observacionesPesoCrudo = observacionesPesoCrudo;
	}

	public String getObservacionesRevision() {
		return observacionesRevision;
	}

	public void setObservacionesRevision(String observacionesRevision) {
		this.observacionesRevision = observacionesRevision;
	}

}
