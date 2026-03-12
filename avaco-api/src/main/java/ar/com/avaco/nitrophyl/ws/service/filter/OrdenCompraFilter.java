package ar.com.avaco.nitrophyl.ws.service.filter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompraEstado;
import ar.com.avaco.utils.DateUtils;

public class OrdenCompraFilter extends AbstractFilter {

	private Long idCliente;

	private String fechaDesde;

	private String fechaHasta;

	private String comprobante;

	private String estado;
	
	public OrdenCompraFilter() {
	}
	
	public OrdenCompraFilter(OrdenCompraFilterDTO filterDTO) {
		super(filterDTO);
		this.idCliente = filterDTO.getIdCliente();
		this.fechaDesde = filterDTO.getFechaDesde();
		this.fechaHasta = filterDTO.getFechaHasta();
		this.comprobante = filterDTO.getComprobante();
		this.estado = filterDTO.getEstado();
	}

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> filters = new ArrayList<FilterData>();

		if (idCliente != null)
			filters.add(new FilterData("cliente.id", idCliente, FilterDataType.EQUALS));
		
		filters.add(new FilterData("cliente.nombre", null, FilterDataType.IS_NOT_NULL));
		
		if (StringUtils.isNotBlank(fechaDesde)) {
			LocalDate date = LocalDate.parse(fechaDesde, DateTimeFormatter.ofPattern(DateUtils.dd_MM_yyyy));
			filters.add(new FilterData("fecha", date, FilterDataType.EQUALS_MORE_THAN));
		}

		if (StringUtils.isNotBlank(fechaHasta)) {
			LocalDate date = LocalDate.parse(fechaHasta, DateTimeFormatter.ofPattern(DateUtils.dd_MM_yyyy));
			filters.add(new FilterData("fecha", date, FilterDataType.EQUALS_LESS_THAN));
		}
		
		if (StringUtils.isNotBlank(comprobante)) {
			filters.add(new FilterData("comprobante", comprobante, FilterDataType.LIKE));
		}

		if (StringUtils.isNotBlank(estado)) {
			filters.add(new FilterData("estado", OrdenCompraEstado.valueOf(estado), FilterDataType.EQUALS));
		}
		
		return filters;
	}
	
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

}
