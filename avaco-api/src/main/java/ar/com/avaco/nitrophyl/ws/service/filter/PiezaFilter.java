package ar.com.avaco.nitrophyl.ws.service.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.ws.dto.PiezaFilterDTO;

public class PiezaFilter extends AbstractFilter {

	private String nombre;

	private Long idFormula;

	private Long idMaterial;

	private Boolean soloVigentes;

	private String idsTipoPieza;
	
	private Long idCliente;

	public PiezaFilter() {
	}

	public PiezaFilter(PiezaFilterDTO filter) {
		super(filter.getRows(), filter.getFirst(), filter.getAsc(), filter.getIdx());
		this.nombre = filter.getNombre();
		this.idFormula = filter.getIdFormula();
		this.idMaterial = filter.getIdMaterial();
		this.soloVigentes = filter.getSoloVigentes();
		this.idsTipoPieza = filter.getIdTipoPieza();
		this.idCliente = filter.getIdCliente();
	}

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> filters = new ArrayList<FilterData>();

		if (StringUtils.isNotBlank(nombre))
			filters.add(new FilterData("denominacion", nombre, FilterDataType.LIKE));

		if (idFormula != null)
			filters.add(new FilterData("detalleFormula.formula.id", idFormula, FilterDataType.EQUALS));

		if (idMaterial != null)
			filters.add(new FilterData("detalleFormula.formula.material.id", idMaterial, FilterDataType.EQUALS));

		if (idCliente != null)
			filters.add(new FilterData("piezaCliente.cliente.id", idCliente, FilterDataType.EQUALS));

		if (soloVigentes != null && soloVigentes.booleanValue())
			filters.add(new FilterData("vigente", true, FilterDataType.EQUALS));

		if (idsTipoPieza != null) {
			List<Integer> lista = Stream.of(idsTipoPieza.split(",")).map(Integer::parseInt)
					.collect(Collectors.toList());
			filters.add(new FilterData("tipo.id", lista, FilterDataType.IN));
		}

		return filters;
	}

}
