package ar.com.avaco.nitrophyl.ws.dto;

import java.util.ArrayList;
import java.util.List;

public class MoldeoPUTDTO {

	List<Long> prensas = new ArrayList<>();

	private Integer precalentamientoValor;

	private String precalentamientoUnidad;

	private Long vulcanizacionTiempo;

	private Double vulcanizacionTemperaturaMin;

	private Double vulcanizacionTemperaturaMax;

	private List<BombeoDTO> bombeos = new ArrayList<BombeoDTO>();

	public List<Long> getPrensas() {
		return prensas;
	}

	public void setPrensas(List<Long> prensas) {
		this.prensas = prensas;
	}

	public Integer getPrecalentamientoValor() {
		return precalentamientoValor;
	}

	public void setPrecalentamientoValor(Integer precalentamientoValor) {
		this.precalentamientoValor = precalentamientoValor;
	}

	public String getPrecalentamientoUnidad() {
		return precalentamientoUnidad;
	}

	public void setPrecalentamientoUnidad(String precalentamientoUnidad) {
		this.precalentamientoUnidad = precalentamientoUnidad;
	}

	public Long getVulcanizacionTiempo() {
		return vulcanizacionTiempo;
	}

	public void setVulcanizacionTiempo(Long vulcanizacionTiempo) {
		this.vulcanizacionTiempo = vulcanizacionTiempo;
	}

	public Double getVulcanizacionTemperaturaMin() {
		return vulcanizacionTemperaturaMin;
	}

	public void setVulcanizacionTemperaturaMin(Double vulcanizacionTemperaturaMin) {
		this.vulcanizacionTemperaturaMin = vulcanizacionTemperaturaMin;
	}

	public Double getVulcanizacionTemperaturaMax() {
		return vulcanizacionTemperaturaMax;
	}

	public void setVulcanizacionTemperaturaMax(Double vulcanizacionTemperaturaMax) {
		this.vulcanizacionTemperaturaMax = vulcanizacionTemperaturaMax;
	}

	public List<BombeoDTO> getBombeos() {
		return bombeos;
	}

	public void setBombeos(List<BombeoDTO> bombeos) {
		this.bombeos = bombeos;
	}

}
