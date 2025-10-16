package ar.com.avaco.nitrophyl.ws.service.filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.domain.entities.lote.EstadoLote;
import ar.com.avaco.nitrophyl.ws.dto.LoteFilterDTO;
import ar.com.avaco.utils.DateUtils;

public class LoteFilter extends AbstractFilter {

	private String nroLote;

	private Long idFormula;

	private Date fechaDesde;

	private Date fechaHasta;

	private boolean excluirPendientes = false;

	private String estado;

	private List<String> estados = new ArrayList<>();

	public LoteFilter() {
	}

	public LoteFilter(LoteFilterDTO filter) {
		super(filter.getRows(), filter.getFirst(), filter.getAsc(), filter.getIdx());
		this.nroLote = filter.getNroLote();
		this.idFormula = filter.getIdFormula();
		if (StringUtils.isNotEmpty(filter.getFechaDesde())) {
			this.fechaDesde = DateUtils.toDate(filter.getFechaDesde(), "dd/MM/yyyy");
		}
		if (StringUtils.isNotEmpty(filter.getFechaHasta())) {
			this.fechaHasta = DateUtils.toDate(filter.getFechaHasta(), "dd/MM/yyyy");
		}
		this.excluirPendientes = filter.isExcluirPendientes();
		this.estado = filter.getEstado();
		this.estados = filter.getEstados();
	}

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> list = new ArrayList<FilterData>();
		if (StringUtils.isNotBlank(this.nroLote)) {
			list.add(new FilterData("nroLote", this.nroLote, FilterDataType.LIKE));
		}
		if (idFormula != null && idFormula > 0) {
			list.add(new FilterData("formula.id", idFormula, FilterDataType.EQUALS));
		}
		if (fechaDesde != null) {
			list.add(new FilterData("fecha", fechaDesde, FilterDataType.EQUALS_MORE_THAN));

		}
		if (fechaHasta != null) {
			list.add(new FilterData("fecha", fechaHasta, FilterDataType.EQUALS_LESS_THAN));
		}

		if (StringUtils.isNotBlank(estado)) {
			list.add(new FilterData("estado", EstadoLote.valueOf(estado), FilterDataType.EQUALS));
		}

		if (excluirPendientes) {
			list.add(new FilterData("fechaEstado", null, FilterDataType.IS_NOT_NULL));
		}

		return list;
	}
	
	@Override
	public List<List<FilterData>> getOrFilterDatas() {
		List<List<FilterData>> orList = new ArrayList<>();

		if (!estados.isEmpty()) {
			List<FilterData> list = new ArrayList<FilterData>();
			estados.forEach(x->{
				list.add(new FilterData("estado", EstadoLote.valueOf(x), FilterDataType.EQUALS));	
			});
			orList.add(list);
		}
		return orList;
	}

	public String getNroLote() {
		return nroLote;
	}

	public void setNroLote(String nroLote) {
		this.nroLote = nroLote;
	}

	public Long getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(Long idFormula) {
		this.idFormula = idFormula;
	}

	public boolean isIncluirPendientes() {
		return excluirPendientes;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public boolean isExcluirPendientes() {
		return excluirPendientes;
	}

	public void setExcluirPendientes(boolean excluirPendientes) {
		this.excluirPendientes = excluirPendientes;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setIncluirPendientes(boolean incluirPendientes) {
		this.excluirPendientes = incluirPendientes;
	}

	public List<String> getEstados() {
		return estados;
	}

	public void setEstados(List<String> estados) {
		this.estados = estados;
	}

}
