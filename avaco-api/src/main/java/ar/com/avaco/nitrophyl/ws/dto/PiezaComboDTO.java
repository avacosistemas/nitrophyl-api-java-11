/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

public class PiezaComboDTO {

	private Long id;

	private String denominacion;

	public PiezaComboDTO(Long id, String denominacion) {
		super();
		this.id = id;
		this.denominacion = denominacion;
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

}
