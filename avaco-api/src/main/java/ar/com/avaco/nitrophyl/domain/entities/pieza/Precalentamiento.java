package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Precalentamiento {

	@Column(name = "PRECALENTAMIENTO")
	private Integer valor;

	@Column(name = "UNIDAD_PRECALENTAMIENTO")
	private String unidad;

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

}
