package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class FormulaDTO extends DTOEntity<Long> {

	private Long id;
	private String nombre;
	private Long idMaterial;
	private String material;
	private Integer version;
	private String fecha;
	private String norma;
	private String observaciones;
	private RevisionParametrosDTO rpdto;

	public String getNorma() {
		return norma;
	}

	public void setNorma(String norma) {
		this.norma = norma;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getLabelCombo() {
		return this.nombre;
	}

	public RevisionParametrosDTO getRpdto() {
		return rpdto;
	}

	public void setRpdto(RevisionParametrosDTO rpdto) {
		this.rpdto = rpdto;
	}

}
