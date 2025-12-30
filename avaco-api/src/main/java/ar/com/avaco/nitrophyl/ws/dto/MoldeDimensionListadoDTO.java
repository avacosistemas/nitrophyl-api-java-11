/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.nitrophyl.domain.entities.molde.TipoDimension;

public class MoldeDimensionListadoDTO {

	private TipoDimension tipoDimension;
	private Integer valor;

	public TipoDimension getTipoDimension() {
		return tipoDimension;
	}

	public void setTipoDimension(TipoDimension tipoDimension) {
		this.tipoDimension = tipoDimension;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

}
