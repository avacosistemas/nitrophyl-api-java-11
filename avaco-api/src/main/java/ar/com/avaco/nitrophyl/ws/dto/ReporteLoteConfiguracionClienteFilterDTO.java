package ar.com.avaco.nitrophyl.ws.dto;

public class ReporteLoteConfiguracionClienteFilterDTO extends SortPageDTO {

	private Long idCliente;

	private Long idFormula;

	private Long idMaquina;

	private Boolean mostrarParametros;

	private Boolean mostrarResultados;

	private Boolean mostrarCondiciones;

	private Boolean mostrarObservacionesParametro;

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(Long idFormula) {
		this.idFormula = idFormula;
	}

	public Long getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(Long idMaquina) {
		this.idMaquina = idMaquina;
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

}
