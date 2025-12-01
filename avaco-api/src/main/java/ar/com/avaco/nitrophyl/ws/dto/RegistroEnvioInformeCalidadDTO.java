package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class RegistroEnvioInformeCalidadDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private String cliente;

	private String lote;

	private String formula;

	private String emailEnviado;

	private String observacionesMail;

	private String observacionesInforme;

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getEmailEnviado() {
		return emailEnviado;
	}

	public void setEmailEnviado(String emailEnviado) {
		this.emailEnviado = emailEnviado;
	}

	public String getObservacionesMail() {
		return observacionesMail;
	}

	public void setObservacionesMail(String observacionesMail) {
		this.observacionesMail = observacionesMail;
	}

	public String getObservacionesInforme() {
		return observacionesInforme;
	}

	public void setObservacionesInforme(String observacionesInforme) {
		this.observacionesInforme = observacionesInforme;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
