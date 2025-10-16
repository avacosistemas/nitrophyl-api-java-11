package ar.com.avaco.nitrophyl.ws.service.filter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.ws.dto.FormulaFilterDTO;

public class FormulaFilter extends AbstractFilter {

	private String nombre;

	private Long idMaterial;

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> list = new ArrayList<FilterData>();
		if (StringUtils.isNotBlank(nombre)) {
			list.add(new FilterData("nombre", nombre, FilterDataType.LIKE));
		}
		if (idMaterial != null) {
			list.add(new FilterData("material.id", idMaterial, FilterDataType.EQUALS));
		}
		return list;
	}

	public FormulaFilter() {
	}

	public FormulaFilter(FormulaFilterDTO ffdto) {
		super(ffdto.getRows(), ffdto.getFirst(), ffdto.getAsc(), ffdto.getIdx());
		this.nombre = ffdto.getNombre();
		this.idMaterial = ffdto.getIdMaterial();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Long idMaterial) {
		this.idMaterial = idMaterial;
	}

}
