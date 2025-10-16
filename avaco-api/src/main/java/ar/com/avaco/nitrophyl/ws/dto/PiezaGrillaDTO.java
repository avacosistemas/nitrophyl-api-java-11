package ar.com.avaco.nitrophyl.ws.dto;

import java.util.Date;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class PiezaGrillaDTO extends DTOEntity<Long> {

	private Long id;

	private Boolean vigente;

	private String codigo;

	private String denominacion;

	private String tipo;

	private String material;

	private String formula;

	private Long revision;

	private Date fechaRevision;

	private Integer rows;

	private Boolean puedeMarcarVigente;

	private Boolean puedeGenerarRevision;

	public PiezaGrillaDTO(Integer rows, String denominacion, Integer idPieza, String codigo, Boolean vigente,
			Integer revision, Date fechaRevision, String tipo, String material, Boolean puedeGenerarRevision,
			String formula, Boolean puedeMarcarVigente) {
		this.rows = rows;
		this.denominacion = denominacion;
		this.id = Long.valueOf(idPieza);
		this.codigo = codigo;
		this.vigente = vigente;
		this.revision = Long.valueOf(revision);
		this.fechaRevision = fechaRevision;
		this.tipo = tipo;
		this.material = material;
		this.formula = formula;
		this.puedeMarcarVigente = puedeMarcarVigente;
		this.puedeGenerarRevision = puedeGenerarRevision;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getVigente() {
		return vigente;
	}

	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public Long getRevision() {
		return revision;
	}

	public void setRevision(Long revision) {
		this.revision = revision;
	}

	public Date getFechaRevision() {
		return fechaRevision;
	}

	public void setFechaRevision(Date fechaRevision) {
		this.fechaRevision = fechaRevision;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Boolean getPuedeMarcarVigente() {
		return puedeMarcarVigente;
	}

	public void setPuedeMarcarVigente(Boolean puedeMarcarVigente) {
		this.puedeMarcarVigente = puedeMarcarVigente;
	}

	public Boolean getPuedeGenerarRevision() {
		return puedeGenerarRevision;
	}

	public void setPuedeGenerarRevision(Boolean puedeGenerarRevision) {
		this.puedeGenerarRevision = puedeGenerarRevision;
	}

}
