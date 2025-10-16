/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

import java.util.ArrayList;
import java.util.List;

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

	private Double durezaMinima; //

	private Double durezaMaxima; //

	private String unidadDureza; //

	private Double pesoCrudo;//

	private String observacionesPesoCrudo;//

	private Long idCliente; //

	private String nombrePiezaCliente; //

	private byte[] planoArchivo;

	private String planoCodigo;

	private Integer planoRevision;

	private String planoClasificacion;

	private String planoObservaciones;

	private List<EspesorDTO> espesores = new ArrayList<>();

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

	public Double getDurezaMinima() {
		return durezaMinima;
	}

	public void setDurezaMinima(Double durezaMinima) {
		this.durezaMinima = durezaMinima;
	}

	public Double getDurezaMaxima() {
		return durezaMaxima;
	}

	public void setDurezaMaxima(Double durezaMaxima) {
		this.durezaMaxima = durezaMaxima;
	}

	public String getUnidadDureza() {
		return unidadDureza;
	}

	public void setUnidadDureza(String unidadDureza) {
		this.unidadDureza = unidadDureza;
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

	public Integer getPlanoRevision() {
		return planoRevision;
	}

	public void setPlanoRevision(Integer planoRevision) {
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

}
