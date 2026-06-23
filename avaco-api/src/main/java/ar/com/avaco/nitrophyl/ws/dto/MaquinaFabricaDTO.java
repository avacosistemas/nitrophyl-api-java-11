package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.nitrophyl.domain.entities.fabricacion.TipoMaquinaFabrica;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class MaquinaFabricaDTO extends DTOEntity<Long> {

	private Long id;

	private String nombre;

	private TipoMaquinaFabrica tipo;

	private Long idSector;

	private String sector;

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

	public TipoMaquinaFabrica getTipo() {
		return tipo;
	}

	public void setTipo(TipoMaquinaFabrica tipo) {
		this.tipo = tipo;
	}

	public Long getIdSector() {
		return idSector;
	}

	public void setIdSector(Long idSector) {
		this.idSector = idSector;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

}
