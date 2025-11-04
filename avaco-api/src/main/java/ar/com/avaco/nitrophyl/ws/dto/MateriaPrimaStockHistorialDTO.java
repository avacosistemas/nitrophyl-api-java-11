package ar.com.avaco.nitrophyl.ws.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoMovimientoStock;
import ar.com.avaco.nitrophyl.domain.entities.pieza.UnidadMedidaMateriaPrima;
import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class MateriaPrimaStockHistorialDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private Long idMateriaPrima;

	private String materiaPrimaNombre;

	private Double cantidad;

	private TipoMovimientoStock tipo;

	private UnidadMedidaMateriaPrima unidadMedida;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date fecha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMateriaPrima() {
		return idMateriaPrima;
	}

	public void setIdMateriaPrima(Long idMateriaPrima) {
		this.idMateriaPrima = idMateriaPrima;
	}

	public String getMateriaPrimaNombre() {
		return materiaPrimaNombre;
	}

	public void setMateriaPrimaNombre(String materiaPrimaNombre) {
		this.materiaPrimaNombre = materiaPrimaNombre;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public TipoMovimientoStock getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimientoStock tipo) {
		this.tipo = tipo;
	}

	public UnidadMedidaMateriaPrima getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedidaMateriaPrima unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
