package ar.com.avaco.nitrophyl.ws.service.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.ws.dto.PiezaStockFilterDTO;

public class PiezaStockFilter extends AbstractFilter {

	private Long idFormula;

	private String codigo;

	private String denominacion;

	public PiezaStockFilter() {
	}

	public PiezaStockFilter(PiezaStockFilterDTO filter) {
		super(filter.getRows(), filter.getFirst(), filter.getAsc(), filter.getIdx());
		this.denominacion = filter.getDenominacion();
		this.idFormula = filter.getIdFormula();
		this.codigo = filter.getCodigo();
	}

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> filters = new ArrayList<FilterData>();

		if (StringUtils.isNotBlank(denominacion))
			filters.add(new FilterData("pieza.denominacion", denominacion, FilterDataType.LIKE));

		if (idFormula != null)
			filters.add(new FilterData("pieza.detalleFormula.formula.id", idFormula, FilterDataType.EQUALS));

		if (codigo != null)
			filters.add(new FilterData("pieza.codigo", codigo, FilterDataType.LIKE));

		return filters;
	}

}
