package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoStock;
import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class TipoInsumoDTO extends DTOAuditableEntity<Long> {

	private Long id;
	private String nombre;
	private TipoInsumoDTO padre;
	private Long idPadre;
	private TipoStock tipoStock;

	public TipoStock getTipoStock() {
		return tipoStock;
	}

	public void setTipoStock(TipoStock tipoStock) {
		this.tipoStock = tipoStock;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoInsumoDTO getPadre() {
		return padre;
	}

	public void setPadre(TipoInsumoDTO padre) {
		this.padre = padre;
	}

	public Long getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(Long idPadre) {
		this.idPadre = idPadre;
	}

}
