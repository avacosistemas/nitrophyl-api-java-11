package ar.com.avaco.nitrophyl.ws.service.filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.ws.dto.RegistroEnvioInformeCalidadFilterDTO;

public class RegistroEnvioInformeCalidadFilter extends AbstractFilter {

	private String nroLote;

	private Long idFormula;

	private Date fechaDesde;

	private Date fechaHasta;

	private Long idCliente;

	private String email;

	public RegistroEnvioInformeCalidadFilter() {
	}

	public RegistroEnvioInformeCalidadFilter(RegistroEnvioInformeCalidadFilterDTO filter) {
		super(filter.getRows(), filter.getFirst(), filter.getAsc(), filter.getIdx());
		this.nroLote = filter.getNroLote();
		this.idFormula = filter.getIdFormula();
		this.fechaDesde = filter.getFechaDesde();
		this.fechaHasta = filter.getFechaHasta();
		this.idCliente = filter.getIdCliente();
		this.email = filter.getEmail();
	}

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> list = new ArrayList<FilterData>();
		
		if (StringUtils.isNotBlank(this.nroLote)) {
			list.add(new FilterData("nroLote", this.nroLote, FilterDataType.LIKE));
		}
		
		if (idFormula != null && idFormula > 0) {
			list.add(new FilterData("lote.formula.id", idFormula, FilterDataType.EQUALS));
		}

		if (fechaDesde != null) {
			list.add(new FilterData("fechaCreacion", fechaDesde, FilterDataType.EQUALS_MORE_THAN));
		}
		
		if (fechaHasta != null) {
			list.add(new FilterData("fechaCreacion", fechaHasta, FilterDataType.EQUALS_LESS_THAN));
		}

		if (email != null && !email.isEmpty()) {
			list.add(new FilterData("emailEnviado", email, FilterDataType.LIKE));
		}
		
		if (idCliente != null && idCliente > 0) {
			list.add(new FilterData("cliente.id", idCliente, FilterDataType.EQUALS));
		}
		
		return list;
	}

	public String getNroLote() {
		return nroLote;
	}

	public void setNroLote(String nroLote) {
		this.nroLote = nroLote;
	}

	public Long getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(Long idFormula) {
		this.idFormula = idFormula;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
