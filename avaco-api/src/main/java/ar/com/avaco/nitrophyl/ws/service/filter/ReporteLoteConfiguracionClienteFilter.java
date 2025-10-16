package ar.com.avaco.nitrophyl.ws.service.filter;

import java.util.ArrayList;
import java.util.List;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.ws.dto.ReporteLoteConfiguracionClienteFilterDTO;

public class ReporteLoteConfiguracionClienteFilter extends AbstractFilter {

	private Long idCliente;

	private Long idFormula;

	private Long idMaquina;

	private Boolean mostrarParametros;

	private Boolean mostrarResultados;

	private Boolean mostrarCondiciones;

	private Boolean mostrarObservacionesParametro;

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> list = new ArrayList<FilterData>();

		if (idCliente != null) {
			list.add(new FilterData("cliente.id", idCliente, FilterDataType.EQUALS));
		}

		if (idFormula != null) {
			list.add(new FilterData("formula.id", idFormula, FilterDataType.EQUALS));
		}

		if (idMaquina != null) {
			list.add(new FilterData("maquina.id", idMaquina, FilterDataType.EQUALS));
		}

		if (mostrarParametros != null) {
			list.add(new FilterData("mostrarParametros", mostrarParametros, FilterDataType.EQUALS));
		}

		if (mostrarResultados != null) {
			list.add(new FilterData("mostrarResultados", mostrarResultados, FilterDataType.EQUALS));
		}

		if (mostrarCondiciones != null) {
			list.add(new FilterData("mostrarCondiciones", mostrarCondiciones, FilterDataType.EQUALS));
		}

		if (mostrarObservacionesParametro != null) {
			list.add(new FilterData("mostrarObservacionesParametro", mostrarObservacionesParametro,
					FilterDataType.EQUALS));
		}

		return list;
	}

	public ReporteLoteConfiguracionClienteFilter() {
	}

	public ReporteLoteConfiguracionClienteFilter(ReporteLoteConfiguracionClienteFilterDTO dto) {
		super(dto.getRows(), dto.getFirst(), dto.getAsc(), dto.getIdx());
		this.idCliente = dto.getIdCliente();
		this.idFormula = dto.getIdFormula();
		this.idMaquina = dto.getIdMaquina();
		this.mostrarObservacionesParametro = dto.getMostrarObservacionesParametro();
		this.mostrarCondiciones = dto.getMostrarCondiciones();
		this.mostrarParametros = dto.getMostrarParametros();
		this.mostrarResultados = dto.getMostrarResultados();
	}

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
