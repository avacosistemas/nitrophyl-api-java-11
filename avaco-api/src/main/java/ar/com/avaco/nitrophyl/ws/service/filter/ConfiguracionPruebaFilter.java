package ar.com.avaco.nitrophyl.ws.service.filter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.ws.dto.ConfiguracionPruebaFilterDTO;

public class ConfiguracionPruebaFilter extends AbstractFilter {

	private String idFormula;

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> list = new ArrayList<FilterData>();
		if (StringUtils.isNotBlank(idFormula)) {
			list.add(new FilterData("idFormula", Long.valueOf(idFormula), FilterDataType.EQUALS));
		}
		return list;
	}

	public ConfiguracionPruebaFilter() {
	}

	public ConfiguracionPruebaFilter(ConfiguracionPruebaFilterDTO ffdto) {
		super(ffdto.getRows(), ffdto.getFirst(), ffdto.getAsc(), ffdto.getIdx());
		this.idFormula = ffdto.getIdFormula();
	}

	public String getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(String idFormula) {
		this.idFormula = idFormula;
	}

}
