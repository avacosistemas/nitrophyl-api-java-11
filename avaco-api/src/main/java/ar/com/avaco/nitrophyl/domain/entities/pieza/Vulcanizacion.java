package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Vulcanizacion {

	@Column(name = "VULCANIZACION_TIEMPO")
	private Long tiempo;

	@Column(name = "VULCANIZACION_TEMP_MIN")
	private Double temperaturaMin;

	@Column(name = "VULCANIZACION_TEMP_MAX")
	private Double temperaturaMax;

	public Long getTiempo() {
		return tiempo;
	}

	public void setTiempo(Long tiempo) {
		this.tiempo = tiempo;
	}

	public Double getTemperaturaMin() {
		return temperaturaMin;
	}

	public void setTemperaturaMin(Double temperaturaMin) {
		this.temperaturaMin = temperaturaMin;
	}

	public Double getTemperaturaMax() {
		return temperaturaMax;
	}

	public void setTemperaturaMax(Double temperaturaMax) {
		this.temperaturaMax = temperaturaMax;
	}

}
