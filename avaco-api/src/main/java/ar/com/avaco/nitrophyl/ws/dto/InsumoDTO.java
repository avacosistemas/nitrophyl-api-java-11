package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.nitrophyl.domain.entities.pieza.Origen;
import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class InsumoDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private String nombre;

	private Long idTipo;

	private String tipoNombre;

	private String materiaPrimaNombre;

	private Long idMateriaPrima;

	private Double cantidadMateriaPrima;

	private String observaciones;

	private Origen origen;
	
	public Origen getOrigen() {
		return origen;
	}

	public void setOrigen(Origen origen) {
		this.origen = origen;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getMateriaPrimaNombre() {
		return materiaPrimaNombre;
	}

	public void setMateriaPrimaNombre(String materiaPrimaNombre) {
		this.materiaPrimaNombre = materiaPrimaNombre;
	}

	public Long getIdMateriaPrima() {
		return idMateriaPrima;
	}

	public void setIdMateriaPrima(Long idMateriaPrima) {
		this.idMateriaPrima = idMateriaPrima;
	}

	public Double getCantidadMateriaPrima() {
		return cantidadMateriaPrima;
	}

	public void setCantidadMateriaPrima(Double cantidadMateriaPrima) {
		this.cantidadMateriaPrima = cantidadMateriaPrima;
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

	public Long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

	public String getTipoNombre() {
		return tipoNombre;
	}

	public void setTipoNombre(String tipoNombre) {
		this.tipoNombre = tipoNombre;
	}

}
