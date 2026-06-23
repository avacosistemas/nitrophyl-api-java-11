package ar.com.avaco.nitrophyl.ws.service.filter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;

public class MaquinaFabricaFilter extends AbstractFilter {

	private Long idSector;

	private String nombre;

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> filters = new ArrayList<FilterData>();

		if (idSector != null)
			filters.add(new FilterData("sector.id", idSector, FilterDataType.EQUALS));

		if (StringUtils.isNotBlank(nombre))
			filters.add(new FilterData("nombre", nombre, FilterDataType.LIKE));

		return filters;
	}

	public Long getIdSector() {
		return idSector;
	}

	public void setIdSector(Long idSector) {
		this.idSector = idSector;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
