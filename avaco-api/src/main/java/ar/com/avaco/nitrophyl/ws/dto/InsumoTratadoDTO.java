package ar.com.avaco.nitrophyl.ws.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class InsumoTratadoDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private Long idPieza;

	private String insumo;

	private Long idInsumo;

	private TipoInsumoDTO tipo;

	private String medidaValor;

	private String medidaObservaciones;

	private String observaciones;

	private List<AdhesivoDTO> adhesivos = new ArrayList<>();

	private List<TratamientoDTO> tratamientos = new ArrayList<>();

	public String getInsumo() {
		return insumo;
	}

	public void setInsumo(String insumo) {
		this.insumo = insumo;
	}

	public Long getIdInsumo() {
		return idInsumo;
	}

	public void setIdInsumo(Long idInsumo) {
		this.idInsumo = idInsumo;
	}

	public String getMedidaValor() {
		return medidaValor;
	}

	public void setMedidaValor(String medidaValor) {
		this.medidaValor = medidaValor;
	}

	public String getMedidaObservaciones() {
		return medidaObservaciones;
	}

	public void setMedidaObservaciones(String medidaObservaciones) {
		this.medidaObservaciones = medidaObservaciones;
	}

	public List<TratamientoDTO> getTratamientos() {
		return tratamientos;
	}

	public void setTratamientos(List<TratamientoDTO> tratamientos) {
		this.tratamientos = tratamientos;
	}

	public List<AdhesivoDTO> getAdhesivos() {
		return adhesivos;
	}

	public void setAdhesivos(List<AdhesivoDTO> adhesivos) {
		this.adhesivos = adhesivos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Long getIdPieza() {
		return idPieza;
	}

	public void setIdPieza(Long idPieza) {
		this.idPieza = idPieza;
	}

	public TipoInsumoDTO getTipo() {
		return tipo;
	}

	public void setTipo(TipoInsumoDTO tipo) {
		this.tipo = tipo;
	}

}
