package ar.com.avaco.nitrophyl.ws.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class ConfiguracionPruebaDTO extends DTOEntity<Long> {

	private Long id;

	private Long idMaquina;

	private Long idFormula;

	private String maquina;

	private List<ConfiguracionPruebaCondicionDTO> condiciones = new ArrayList<ConfiguracionPruebaCondicionDTO>();

	private List<ConfiguracionPruebaParametroDTO> parametros = new ArrayList<ConfiguracionPruebaParametroDTO>();

	private String observacionesReporte;

	private Long version;

	private String fecha;

	private String fechaHasta;

	private Boolean vigente;

	private Boolean isPuedeIncorporarRevisionVigente;

	public Boolean getIsPuedeIncorporarRevisionVigente() {
		return isPuedeIncorporarRevisionVigente;
	}

	public void setIsPuedeIncorporarRevisionVigente(Boolean isPuedeIncorporarRevisionVigente) {
		this.isPuedeIncorporarRevisionVigente = isPuedeIncorporarRevisionVigente;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(Long idMaquina) {
		this.idMaquina = idMaquina;
	}

	public Long getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(Long idFormula) {
		this.idFormula = idFormula;
	}

	public List<ConfiguracionPruebaCondicionDTO> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<ConfiguracionPruebaCondicionDTO> condiciones) {
		this.condiciones = condiciones;
	}

	public List<ConfiguracionPruebaParametroDTO> getParametros() {
		return parametros;
	}

	public void setParametros(List<ConfiguracionPruebaParametroDTO> parametros) {
		this.parametros = parametros;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getMaquina() {
		return maquina;
	}

	public void setMaquina(String maquina) {
		this.maquina = maquina;
	}

	public String getObservacionesReporte() {
		return observacionesReporte;
	}

	public void setObservacionesReporte(String observacionesReporte) {
		this.observacionesReporte = observacionesReporte;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Boolean getVigente() {
		return vigente;
	}

	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}

}
