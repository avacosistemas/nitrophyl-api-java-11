package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;

@Embeddable
public class PiezaFormula {

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "ID_FORMULA")
	private Formula formula;

	@Column(name = "PESO_CRUDO")
	private Double pesoCrudo;

	@Column(name = "OBS_PESO_CRUD")
	private String observacionesPesoCrudo;

	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
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

}
