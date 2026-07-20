package ar.com.avaco.nitrophyl.ws.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrdenFabricacionEntregaDTO {

	private Integer cantidad;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate fecha;

	private List<Long> idLote = new ArrayList<Long>();

	private Long idUsuario;

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<Long> getIdLote() {
		return idLote;
	}

	public void setIdLote(List<Long> idLote) {
		this.idLote = idLote;
	}

}
