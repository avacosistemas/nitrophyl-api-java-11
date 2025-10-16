package ar.com.avaco.nitrophyl.ws.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class EnsayoDTO extends DTOEntity<Long> {

	private Long id;

	private Long idLote;

	private Long idConfiguracionPrueba;

	private String fecha;

	private String observaciones;

	private String maquina;

	private String estado;

	private List<EnsayoResultadoDTO> resultados = new ArrayList<EnsayoResultadoDTO>();

	public Long getIdConfiguracionPrueba() {
		return idConfiguracionPrueba;
	}

	public void setIdConfiguracionPrueba(Long idConfiguracionPrueba) {
		this.idConfiguracionPrueba = idConfiguracionPrueba;
	}

	public Long getIdLote() {
		return idLote;
	}

	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getMaquina() {
		return maquina;
	}

	public void setMaquina(String maquina) {
		this.maquina = maquina;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<EnsayoResultadoDTO> getResultados() {
		return resultados;
	}

	public void setResultados(List<EnsayoResultadoDTO> resultados) {
		this.resultados = resultados;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
