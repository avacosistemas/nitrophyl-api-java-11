package ar.com.avaco.nitrophyl.ws.service.filter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.ws.dto.MaterialFilterDTO;

public class MaterialFilter extends AbstractFilter {

	private String nombre;

	private String codigo;

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> list = new ArrayList<FilterData>();
		if (StringUtils.isNotBlank(nombre)) {
			list.add(new FilterData("nombre", nombre, FilterDataType.LIKE));
		}
		if (StringUtils.isNotBlank(codigo)) {
			list.add(new FilterData("codigo", nombre, FilterDataType.LIKE));
		}
		return list;
	}

	public MaterialFilter() {
	}

	public MaterialFilter(MaterialFilterDTO ffdto) {
		super(ffdto.getRows(), ffdto.getFirst(), ffdto.getAsc(), ffdto.getIdx());
		this.nombre = ffdto.getNombre();
		this.codigo = ffdto.getCodigo();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
