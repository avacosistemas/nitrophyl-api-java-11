/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

public class PiezaComboDTO {

	private Long id;

	private String denominacion;

	private String formula;

	private String codigo;

	public PiezaComboDTO(Long id, String denominacion, String formula, String codigo) {
		super();
		this.id = id;
		this.denominacion = denominacion;
		this.codigo = codigo;
		this.formula = formula;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
