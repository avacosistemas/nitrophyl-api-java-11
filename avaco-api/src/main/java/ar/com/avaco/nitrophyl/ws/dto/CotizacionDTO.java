package ar.com.avaco.nitrophyl.ws.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class CotizacionDTO extends DTOAuditableEntity<Long> {

	private Long id;
	private String cliente;
	private Long idCliente;

	private String pieza;
	private Long idPieza;

	private String formula;

	private Double valor;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date fecha;
	private String observaciones;

	private Long revisionPieza;

	public CotizacionDTO() {
	}

	public CotizacionDTO(Long id, String cliente, Long idCliente, String pieza, Long idPieza, String formula,
			Double valor, Date fecha, String observaciones, Long revisionPieza) {
		this.id = id;
		this.cliente = cliente;
		this.idCliente = idCliente;
		this.pieza = pieza;
		this.idPieza = idPieza;
		this.formula = formula;
		this.valor = valor;
		this.fecha = fecha;
		this.observaciones = observaciones;
		this.revisionPieza = revisionPieza;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getPieza() {
		return pieza;
	}

	public void setPieza(String pieza) {
		this.pieza = pieza;
	}

	public Long getIdPieza() {
		return idPieza;
	}

	public void setIdPieza(Long idPieza) {
		this.idPieza = idPieza;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public Long getRevisionPieza() {
		return revisionPieza;
	}

	public void setRevisionPieza(Long revisionPieza) {
		this.revisionPieza = revisionPieza;
	}

}
