package ar.com.avaco.nitrophyl.ws.service.filter;

import java.util.ArrayList;
import java.util.List;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.ws.dto.CotizacionFilterDTO;

public class CotizacionFilter extends AbstractFilter {

	private Long idPieza;

	private Long idCliente;

	public CotizacionFilter() {
	}

	public CotizacionFilter(CotizacionFilterDTO filter) {
		super(filter.getRows(), filter.getFirst(), filter.getAsc(), filter.getIdx());
		this.idPieza = filter.getIdPieza();
		this.idCliente = filter.getIdCliente();
	}

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> filters = new ArrayList<FilterData>();

		if (idCliente != null)
			filters.add(new FilterData("piezaCliente.cliente.id", idCliente, FilterDataType.EQUALS));

		if (idPieza != null)
			filters.add(new FilterData("piezaCliente.pieza.id", idPieza, FilterDataType.EQUALS));

		return filters;
	}

}
