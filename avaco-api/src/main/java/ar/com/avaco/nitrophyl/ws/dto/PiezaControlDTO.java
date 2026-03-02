/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoControl;
import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class PiezaControlDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private Long idPieza;

	private String control;

	private TipoControl tipo;

	private Boolean editable;

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public void setTipo(TipoControl tipo) {
		this.tipo = tipo;
	}

	public Long getIdPieza() {
		return idPieza;
	}

	public void setIdPieza(Long idPieza) {
		this.idPieza = idPieza;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public TipoControl getTipo() {
		return tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
