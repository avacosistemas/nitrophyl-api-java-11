package ar.com.avaco.nitrophyl.ws.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class ProcesoDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private PrecalentamientoDTO precalentamiento;

	private List<PrensaDTO> prensas = new ArrayList<>();

	private String desmoldante;

	private String postCura;

	private String hojaProceso;
	
	public String getHojaProceso() {
		return hojaProceso;
	}

	public void setHojaProceso(String hojaProceso) {
		this.hojaProceso = hojaProceso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PrecalentamientoDTO getPrecalentamiento() {
		return precalentamiento;
	}

	public void setPrecalentamiento(PrecalentamientoDTO precalentamiento) {
		this.precalentamiento = precalentamiento;
	}

	public List<PrensaDTO> getPrensas() {
		return prensas;
	}

	public void setPrensas(List<PrensaDTO> prensas) {
		this.prensas = prensas;
	}

	public String getDesmoldante() {
		return desmoldante;
	}

	public void setDesmoldante(String desmoldante) {
		this.desmoldante = desmoldante;
	}

	public String getPostCura() {
		return postCura;
	}

	public void setPostCura(String postCura) {
		this.postCura = postCura;
	}

}
