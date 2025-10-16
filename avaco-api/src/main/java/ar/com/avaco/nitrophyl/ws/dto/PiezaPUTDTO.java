/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

import java.util.HashSet;
import java.util.Set;

import ar.com.avaco.nitrophyl.domain.entities.pieza.UnidadDureza;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class PiezaPUTDTO extends DTOEntity<Long> {

	private Long id;

	private Double durezaMinima;

	private Double durezaMaxima;

	private UnidadDureza unidadDureza;

	private Double pesoCrudo;

	private String observacionesPesoCrudo;

	private String observacionesRevision;

	private Set<EspesorDTO> espesores = new HashSet<>();

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

	public Double getDurezaMinima() {
		return durezaMinima;
	}

	public void setDurezaMinima(Double durezaMinima) {
		this.durezaMinima = durezaMinima;
	}

	public Double getDurezaMaxima() {
		return durezaMaxima;
	}

	public void setDurezaMaxima(Double durezaMaxima) {
		this.durezaMaxima = durezaMaxima;
	}

	public UnidadDureza getUnidadDureza() {
		return unidadDureza;
	}

	public void setUnidadDureza(UnidadDureza unidadDureza) {
		this.unidadDureza = unidadDureza;
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
