/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class PiezaEdicionDTO extends DTOEntity<Long> {

	private Long id;

	private String denominacion;

	private String tipo;

	private String codigo;

	private String nombreFormula;

	private Double pesoCrudo;

	private String observacionesPesoCrudo;

	private Long revision;

	private Date fechaRevision;

	private Boolean vigente;

	private Date fechaCreacionPiezaProceso;

	private String observacionesRevision;

	private Integer precalentamientoValor;

	private String precalentamientoUnidad;

	private Set<PrensaDTO> prensas = new HashSet<>();

	private Long vulcanizacionTiempo;

	private Double vulcanizacionTemperaturaMin;

	private Double vulcanizacionTemperaturaMax;

	private Set<BombeoDTO> bombeos = new HashSet<>();

	private Set<EspesorDTO> espesores = new HashSet<>();

	private String desmoldante;

	private String postCura;

	private String hojaProceso;

	public String getHojaProceso() {
		return hojaProceso;
	}

	public void setHojaProceso(String hojaProceso) {
		this.hojaProceso = hojaProceso;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombreFormula() {
		return nombreFormula;
	}

	public void setNombreFormula(String nombreFormula) {
		this.nombreFormula = nombreFormula;
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

	public Boolean getVigente() {
		return vigente;
	}

	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}

	public Date getFechaCreacionPiezaProceso() {
		return fechaCreacionPiezaProceso;
	}

	public void setFechaCreacionPiezaProceso(Date fechaCreacionPiezaProceso) {
		this.fechaCreacionPiezaProceso = fechaCreacionPiezaProceso;
	}

	public String getObservacionesRevision() {
		return observacionesRevision;
	}

	public void setObservacionesRevision(String observacionesRevision) {
		this.observacionesRevision = observacionesRevision;
	}

	public Integer getPrecalentamientoValor() {
		return precalentamientoValor;
	}

	public void setPrecalentamientoValor(Integer precalentamientoValor) {
		this.precalentamientoValor = precalentamientoValor;
	}

	public String getPrecalentamientoUnidad() {
		return precalentamientoUnidad;
	}

	public void setPrecalentamientoUnidad(String precalentamientoUnidad) {
		this.precalentamientoUnidad = precalentamientoUnidad;
	}

	public Set<PrensaDTO> getPrensas() {
		return prensas;
	}

	public void setPrensas(Set<PrensaDTO> prensas) {
		this.prensas = prensas;
	}

	public Long getVulcanizacionTiempo() {
		return vulcanizacionTiempo;
	}

	public void setVulcanizacionTiempo(Long vulcanizacionTiempo) {
		this.vulcanizacionTiempo = vulcanizacionTiempo;
	}

	public Double getVulcanizacionTemperaturaMin() {
		return vulcanizacionTemperaturaMin;
	}

	public void setVulcanizacionTemperaturaMin(Double vulcanizacionTemperaturaMin) {
		this.vulcanizacionTemperaturaMin = vulcanizacionTemperaturaMin;
	}

	public Double getVulcanizacionTemperaturaMax() {
		return vulcanizacionTemperaturaMax;
	}

	public void setVulcanizacionTemperaturaMax(Double vulcanizacionTemperaturaMax) {
		this.vulcanizacionTemperaturaMax = vulcanizacionTemperaturaMax;
	}

	public Set<BombeoDTO> getBombeos() {
		return bombeos;
	}

	public void setBombeos(Set<BombeoDTO> bombeos) {
		this.bombeos = bombeos;
	}

	public String getDesmoldante() {
		return desmoldante;
	}

	public void setDesmoldante(String desmoldante) {
		this.desmoldante = desmoldante;
	}

	public String getPostCura() {
		return postCura;
	}

	public void setPostCura(String postCura) {
		this.postCura = postCura;
	}

	public Set<EspesorDTO> getEspesores() {
		return espesores;
	}

	public void setEspesores(Set<EspesorDTO> espesores) {
		this.espesores = espesores;
	}

}
