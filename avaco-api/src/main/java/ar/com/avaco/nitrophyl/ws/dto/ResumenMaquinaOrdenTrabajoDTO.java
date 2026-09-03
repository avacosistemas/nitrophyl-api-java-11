package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.nitrophyl.domain.entities.fabricacion.TipoMaquinaFabrica;

public class ResumenMaquinaOrdenTrabajoDTO {

	private Long idMaquina;
	private Long idSector;
	private String maquina;
	private String sector;
	private String tipoMaquina;
	private Integer cantidad;

	public ResumenMaquinaOrdenTrabajoDTO() {
	}

	public ResumenMaquinaOrdenTrabajoDTO(Long idMaquina, Long idSector, String maquina, String sector,
			TipoMaquinaFabrica tipoMaquina, Long cantidad) {

		this.idMaquina = idMaquina;
		this.idSector = idSector;
		this.maquina = maquina;
		this.sector = sector;
		this.tipoMaquina = tipoMaquina != null ? tipoMaquina.name() : null;
		this.cantidad = cantidad != null ? cantidad.intValue() : 0;
	}

	public Long getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(Long idMaquina) {
		this.idMaquina = idMaquina;
	}

	public Long getIdSector() {
		return idSector;
	}

	public void setIdSector(Long idSector) {
		this.idSector = idSector;
	}

	public String getMaquina() {
		return maquina;
	}

	public void setMaquina(String maquina) {
		this.maquina = maquina;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getTipoMaquina() {
		return tipoMaquina;
	}

	public void setTipoMaquina(String tipoMaquina) {
		this.tipoMaquina = tipoMaquina;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
