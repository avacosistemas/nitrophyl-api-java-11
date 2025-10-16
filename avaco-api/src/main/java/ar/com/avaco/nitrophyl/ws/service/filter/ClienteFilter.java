package ar.com.avaco.nitrophyl.ws.service.filter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.ws.dto.ClienteFilterDTO;

public class ClienteFilter extends AbstractFilter {

	private String nombreRs;
	
	private String busquedaRapida;

	private Boolean activo;

	@Override
	public List<List<FilterData>> getOrFilterDatas() {
		List<List<FilterData>> orList = new ArrayList<>();

		if (StringUtils.isNotBlank(nombreRs)) {
			List<FilterData> list = new ArrayList<FilterData>();
			list.add(new FilterData("razonSocial", nombreRs, FilterDataType.LIKE));
			list.add(new FilterData("nombre", nombreRs, FilterDataType.LIKE));
			orList.add(list);
		}
		
		if (StringUtils.isNotBlank(busquedaRapida)) {
			List<FilterData> list = new ArrayList<FilterData>();
			list.add(new FilterData("razonSocial", busquedaRapida, FilterDataType.LIKE));
			list.add(new FilterData("nombre", busquedaRapida, FilterDataType.LIKE));
			list.add(new FilterData("cuit", busquedaRapida, FilterDataType.LIKE));
			list.add(new FilterData("email", busquedaRapida, FilterDataType.LIKE));
			list.add(new FilterData("telefono", busquedaRapida, FilterDataType.LIKE));
			list.add(new FilterData("domicilio", busquedaRapida, FilterDataType.LIKE));
			orList.add(list);
		}

		if (activo != null) {
			List<FilterData> list = new ArrayList<FilterData>();
			list.add(new FilterData("activo", activo, FilterDataType.EQUALS));
			orList.add(list);
		}

		return orList;
	}

	public ClienteFilter() {
	}

	public ClienteFilter(ClienteFilterDTO filter) {
		super(filter.getRows(), filter.getFirst(), filter.getAsc(), filter.getIdx());
		this.busquedaRapida = filter.getBusquedaRapida();
		this.activo = filter.getActivo();
		this.nombreRs = filter.getNombreRs();
	}

	public String getBusquedaRapida() {
		return busquedaRapida;
	}

	public void setBusquedaRapida(String busquedaRapida) {
		this.busquedaRapida = busquedaRapida;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}
