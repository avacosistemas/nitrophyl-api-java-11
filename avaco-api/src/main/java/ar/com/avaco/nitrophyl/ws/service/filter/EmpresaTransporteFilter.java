package ar.com.avaco.nitrophyl.ws.service.filter;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.ws.dto.EmpresaTransporteFilterDTO;

public class EmpresaTransporteFilter extends AbstractFilter {

	private String nombre;

	private String direccion;

	private List<String> mediosEnvio = new ArrayList<String>();

	public EmpresaTransporteFilter() {
	}

	public EmpresaTransporteFilter(EmpresaTransporteFilterDTO filter) {
		super(filter.getRows(), filter.getFirst(), filter.getAsc(), filter.getIdx());
		this.nombre = filter.getNombre();
		this.direccion = filter.getDireccion();
		this.mediosEnvio = filter.getMediosEnvio();
	}

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> filters = new ArrayList<FilterData>();

		if (StringUtils.isNotBlank(nombre))
			filters.add(new FilterData("nombre", nombre, FilterDataType.LIKE));

		if (StringUtils.isNotBlank(direccion))
			filters.add(new FilterData("direccion", direccion, FilterDataType.LIKE));

		if (mediosEnvio != null && !mediosEnvio.isEmpty()) {
			mediosEnvio.forEach(medio -> {
				filters.add(new FilterData("mediosEnvio", medio, FilterDataType.LIKE));
			});
		}

		return filters;
	}

}
