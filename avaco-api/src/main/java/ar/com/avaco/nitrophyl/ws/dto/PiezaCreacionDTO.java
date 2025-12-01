/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class PiezaCreacionDTO extends DTOEntity<Long> {

	private Long id;//

	private Long revisionIncial;//

	private String denominacion;//

	private Long idTipoPieza;//

	private Long idMolde; //

	private String observacionesMolde;

	private String codigo; //

	private Long idFormula;//

	private Double pesoCrudo;//

	private String observacionesPesoCrudo;//

	private Long idCliente; //

	private String nombrePiezaCliente; //

	private byte[] planoArchivo;

	private String planoCodigo;

	private String planoRevision;

	private String planoClasificacion;

	private String planoObservaciones;

	private List<EspesorDTO> espesores = new ArrayList<>();

	private Double cotizacionCliente;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date cotizacionFecha;

	private String observacionesCotizacionCliente;

	private String hojaProceso;

	public String getHojaProceso() {
		return hojaProceso;
	}

	public void setHojaProceso(String hojaProceso) {
		this.hojaProceso = hojaProceso;
	}

	public List<EspesorDTO> getEspesores() {
		return espesores;
	}

	public void setEspesores(List<EspesorDTO> espesores) {
		this.espesores = espesores;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public Long getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(Long idFormula) {
		this.idFormula = idFormula;
	}

	public Long getIdTipoPieza() {
		return idTipoPieza;
	}

	public void setIdTipoPieza(Long idTipoPieza) {
		this.idTipoPieza = idTipoPieza;
	}

	public Long getIdMolde() {
		return idMolde;
	}

	public void setIdMolde(Long idMolde) {
		this.idMolde = idMolde;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombrePiezaCliente() {
		return nombrePiezaCliente;
	}

	public void setNombrePiezaCliente(String nombrePiezaCliente) {
		this.nombrePiezaCliente = nombrePiezaCliente;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Double getPesoCrudo() {
		return pesoCrudo;
	}

	public void setPesoCrudo(Double pesoCrudo) {
		this.pesoCrudo = pesoCrudo;
	}

	public String getObservacionesPesoCrudo() {
		return observacionesPesoCrudo;
	}

	public void setObservacionesPesoCrudo(String observacionesPesoCrudo) {
		this.observacionesPesoCrudo = observacionesPesoCrudo;
	}

	public byte[] getPlanoArchivo() {
		return planoArchivo;
	}

	public void setPlanoArchivo(byte[] planoArchivo) {
		this.planoArchivo = planoArchivo;
	}

	public String getPlanoCodigo() {
		return planoCodigo;
	}

	public void setPlanoCodigo(String planoCodigo) {
		this.planoCodigo = planoCodigo;
	}

	public String getPlanoRevision() {
		return planoRevision;
	}

	public void setPlanoRevision(String planoRevision) {
		this.planoRevision = planoRevision;
	}

	public String getPlanoClasificacion() {
		return planoClasificacion;
	}

	public void setPlanoClasificacion(String planoClasificacion) {
		this.planoClasificacion = planoClasificacion;
	}

	public String getPlanoObservaciones() {
		return planoObservaciones;
	}

	public void setPlanoObservaciones(String planoObservaciones) {
		this.planoObservaciones = planoObservaciones;
	}

	public Long getRevisionIncial() {
		return revisionIncial;
	}

	public void setRevisionIncial(Long revisionIncial) {
		this.revisionIncial = revisionIncial;
	}

	public String getObservacionesMolde() {
		return observacionesMolde;
	}

	public void setObservacionesMolde(String observacionesMolde) {
		this.observacionesMolde = observacionesMolde;
	}

	public Double getCotizacionCliente() {
		return cotizacionCliente;
	}

	public void setCotizacionCliente(Double cotizacionCliente) {
		this.cotizacionCliente = cotizacionCliente;
	}

	public Date getCotizacionFecha() {
		return cotizacionFecha;
	}

	public void setCotizacionFecha(Date cotizacionFecha) {
		this.cotizacionFecha = cotizacionFecha;
	}

	public String getObservacionesCotizacionCliente() {
		return observacionesCotizacionCliente;
	}

	public void setObservacionesCotizacionCliente(String observacionesCotizacionCliente) {
		this.observacionesCotizacionCliente = observacionesCotizacionCliente;
	}

}
