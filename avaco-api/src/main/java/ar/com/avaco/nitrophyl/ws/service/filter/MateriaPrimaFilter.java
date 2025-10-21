package ar.com.avaco.nitrophyl.ws.service.filter;

import java.util.ArrayList;
import java.util.List;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.nitrophyl.ws.dto.MateriaPrimaFilterDTO;

public class MateriaPrimaFilter extends AbstractFilter {

	public MateriaPrimaFilter() {
	}

	public MateriaPrimaFilter(MateriaPrimaFilterDTO filter) {
		super(filter.getRows(), filter.getFirst(), filter.getAsc(), filter.getIdx());
	}

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> list = new ArrayList<FilterData>();
		return list;
	}

}
