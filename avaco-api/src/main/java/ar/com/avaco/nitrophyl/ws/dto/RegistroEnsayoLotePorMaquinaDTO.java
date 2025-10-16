package ar.com.avaco.nitrophyl.ws.dto;

import java.util.Date;

public class RegistroEnsayoLotePorMaquinaDTO {

	private Long rows;

	private Long row;

	private Long idLote;

	private String nroLote;

	private Date fecha;

	private String observaciones;

	private Long idFormula;

	private String nombreFormula;

	private Long idMaquinaPrueba;

	private Double redondeo;

	private Double resultado;

	private String estadoEnsayo;

	public RegistroEnsayoLotePorMaquinaDTO(Integer row, Integer rows, Integer idLote, String nroLote, Date fecha,
			String observaciones, Integer idFormula, String nombreFormula, Integer idMaquinaPrueba, Double redondeo,
			Double resultado, String estadoEnsayo) {
		super();
		this.row = row.longValue();
		this.rows = rows.longValue();
		this.idLote = idLote.longValue();
		this.nroLote = nroLote;
		this.fecha = fecha;
		this.observaciones = observaciones;
		this.idFormula = idFormula.longValue();
		this.nombreFormula = nombreFormula;
		this.idMaquinaPrueba = idMaquinaPrueba.longValue();
		this.redondeo = redondeo;
		this.resultado = resultado;
		this.estadoEnsayo = estadoEnsayo;
	}

	public Long getIdLote() {
		return idLote;
	}

	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}

	public String getNroLote() {
		return nroLote;
	}

	public void setNroLote(String nroLote) {
		this.nroLote = nroLote;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Long getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(Long idFormula) {
		this.idFormula = idFormula;
	}

	public String getNombreFormula() {
		return nombreFormula;
	}

	public void setNombreFormula(String nombreFormula) {
		this.nombreFormula = nombreFormula;
	}

	public Long getIdMaquinaPrueba() {
		return idMaquinaPrueba;
	}

	public void setIdMaquinaPrueba(Long idMaquinaPrueba) {
		this.idMaquinaPrueba = idMaquinaPrueba;
	}

	public Double getRedondeo() {
		return redondeo;
	}

	public void setRedondeo(Double redondeo) {
		this.redondeo = redondeo;
	}

	public Double getResultado() {
		return resultado;
	}

	public void setResultado(Double resultado) {
		this.resultado = resultado;
	}

	public String getEstadoEnsayo() {
		return estadoEnsayo;
	}

	public void setEstadoEnsayo(String estadoEnsayo) {
		this.estadoEnsayo = estadoEnsayo;
	}

	public Long getRows() {
		return rows;
	}

	public void setRows(Long rows) {
		this.rows = rows;
	}

	public Long getRow() {
		return row;
	}

	public void setRow(Long row) {
		this.row = row;
	}

}
