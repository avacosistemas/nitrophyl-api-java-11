package ar.com.avaco.nitrophyl.ws.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.avaco.nitrophyl.domain.entities.pieza.UnidadMedidaInsumida;
import ar.com.avaco.nitrophyl.domain.entities.pieza.UnidadMedidaLongitud;
import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class InsumoTratadoDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private Long idPieza;

	private String insumo;

	private Long idInsumo;

	private TipoInsumoDTO tipo;

	private Integer unidades;

	private UnidadMedidaInsumida unidadMedida;

	private UnidadMedidaLongitud unidadMedidaLongitud;

	private Double medida1;

	private Double medida2;

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

	public Integer getUnidades() {
		return unidades;
	}

	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

	public UnidadMedidaInsumida getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedidaInsumida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public UnidadMedidaLongitud getUnidadMedidaLongitud() {
		return unidadMedidaLongitud;
	}

	public void setUnidadMedidaLongitud(UnidadMedidaLongitud unidadMedidaLongitud) {
		this.unidadMedidaLongitud = unidadMedidaLongitud;
	}

	public Double getMedida1() {
		return medida1;
	}

	public void setMedida1(Double medida1) {
		this.medida1 = medida1;
	}

	public Double getMedida2() {
		return medida2;
	}

	public void setMedida2(Double medida2) {
		this.medida2 = medida2;
	}

}
