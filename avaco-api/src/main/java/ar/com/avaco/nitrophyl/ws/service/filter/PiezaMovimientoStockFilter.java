package ar.com.avaco.nitrophyl.ws.service.filter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.domain.entities.pieza.OrigenMovimientoStock;
import ar.com.avaco.nitrophyl.ws.dto.PiezaMovimientoStockFilterDTO;

public class PiezaMovimientoStockFilter extends AbstractFilter {

	private Long idPieza;

	private OrigenMovimientoStock origen;

	private LocalDate fechaDesde;

	private LocalDate fechaHasta;

	public PiezaMovimientoStockFilter() {
	}

	public PiezaMovimientoStockFilter(PiezaMovimientoStockFilterDTO filter) {
		super(filter.getRows(), filter.getFirst(), filter.getAsc(), filter.getIdx());
		this.idPieza = filter.getIdPieza();
		this.origen = filter.getOrigen();
		this.fechaDesde = filter.getFechaDesde();
		this.fechaHasta = filter.getFechaHasta();
	}

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> filters = new ArrayList<FilterData>();

		if (idPieza != null)
			filters.add(new FilterData("pieza.id", idPieza, FilterDataType.EQUALS));

		if (origen != null)
			filters.add(new FilterData("origen", origen, FilterDataType.EQUALS));

		if (fechaDesde != null)
			filters.add(new FilterData("fecha", fechaDesde, FilterDataType.EQUALS_MORE_THAN));

		if (fechaHasta != null)
			filters.add(new FilterData("fecha", fechaHasta, FilterDataType.EQUALS_LESS_THAN));

		return filters;
	}

}
