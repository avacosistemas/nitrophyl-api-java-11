package ar.com.avaco.nitrophyl.ws.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class ReporteLoteConfiguracionClienteDTO extends DTOEntity<Long> {

	private Long id;

	private Long idCliente;

	private String cliente;

	private Long idFormula;

	private String formula;

	private Long idMaquina;

	private String maquina;

	private Boolean mostrarParametros;

	private Boolean mostrarResultados;

	private Boolean mostrarCondiciones;

	private Boolean mostrarObservacionesParametro;

	private Boolean enviarGrafico;

	private List<Long> idsPruebas = new ArrayList<Long>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Long getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(Long idFormula) {
		this.idFormula = idFormula;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public Long getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(Long idMaquina) {
		this.idMaquina = idMaquina;
	}

	public String getMaquina() {
		return maquina;
	}

	public void setMaquina(String maquina) {
		this.maquina = maquina;
	}

	public Boolean getMostrarParametros() {
		return mostrarParametros;
	}

	public void setMostrarParametros(Boolean mostrarParametros) {
		this.mostrarParametros = mostrarParametros;
	}

	public Boolean getMostrarResultados() {
		return mostrarResultados;
	}

	public void setMostrarResultados(Boolean mostrarResultados) {
		this.mostrarResultados = mostrarResultados;
	}

	public Boolean getMostrarCondiciones() {
		return mostrarCondiciones;
	}

	public void setMostrarCondiciones(Boolean mostrarCondiciones) {
		this.mostrarCondiciones = mostrarCondiciones;
	}

	public Boolean getMostrarObservacionesParametro() {
		return mostrarObservacionesParametro;
	}

	public void setMostrarObservacionesParametro(Boolean mostrarObservacionesParametro) {
		this.mostrarObservacionesParametro = mostrarObservacionesParametro;
	}

	public Boolean getEnviarGrafico() {
		return enviarGrafico;
	}

	public void setEnviarGrafico(Boolean enviarGrafico) {
		this.enviarGrafico = enviarGrafico;
	}

	public List<Long> getIdsPruebas() {
		return idsPruebas;
	}

	public void setIdsPruebas(List<Long> idsPruebas) {
		this.idsPruebas = idsPruebas;
	}

}
