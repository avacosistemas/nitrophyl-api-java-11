package ar.com.avaco.nitrophyl.ws.dto;

import java.util.Date;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class LoteGraficoSinArchivoDTO extends DTOEntity<Long> {

	private Long id;
	private String maquina;
	private Date fecha;

	public LoteGraficoSinArchivoDTO(Integer id, Date fecha, String maquina) {
		super();
		this.id = new Long(id);
		this.maquina = maquina;
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMaquina() {
		return maquina;
	}

	public void setMaquina(String maquina) {
		this.maquina = maquina;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
