package ar.com.avaco.nitrophyl.ws.service.filter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.ws.dto.InsumoFilterDTO;

public class InsumoFilter extends AbstractFilter {

	private String nombre;

	private Long idTipo;

	public InsumoFilter() {
	}

	public InsumoFilter(InsumoFilterDTO filter) {
		super(filter.getRows(), filter.getFirst(), filter.getAsc(), filter.getIdx());
		this.nombre = filter.getNombre();
		this.idTipo = filter.getIdTipo();
	}

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> filters = new ArrayList<FilterData>();

		if (StringUtils.isNotBlank(nombre))
			filters.add(new FilterData("nombre", nombre, FilterDataType.LIKE));

		if (idTipo != null)
			filters.add(new FilterData("tipo.id", idTipo, FilterDataType.EQUALS));

		return filters;
	}

}
