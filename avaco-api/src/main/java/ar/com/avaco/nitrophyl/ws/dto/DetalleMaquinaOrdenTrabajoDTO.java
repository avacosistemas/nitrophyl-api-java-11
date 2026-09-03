package ar.com.avaco.nitrophyl.ws.dto;

import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DetalleMaquinaOrdenTrabajoDTO {

	private Long id;
	private Long idMaquina;
	private String of;
	private String cliente;
	private String pieza;
	private String material;
	private Integer cantidad;
	@JsonFormat(pattern = "dd/MM/yy")
	private LocalDate fechaEntrega;
	private Integer posicion;

	public DetalleMaquinaOrdenTrabajoDTO() {
		// TODO Auto-generated constructor stub
	}

	public DetalleMaquinaOrdenTrabajoDTO(
	        Long id,
	        Long idMaquina,
	        Long numero,
	        Integer anio,
	        String cliente,
	        String pieza,
	        String material,
	        Integer cantidad,
	        LocalDate fechaEntrega,
	        Integer posicion) {

	    this.id = id;
	    this.idMaquina = idMaquina;
	    this.of = StringUtils.leftPad(numero.toString(), 3, "0")
	            + "/" + anio.toString().substring(2, 4);
	    this.cliente = cliente;
	    this.pieza = pieza;
	    this.material = material;
	    this.cantidad = cantidad;
	    this.fechaEntrega = fechaEntrega;
	    this.posicion = posicion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(Long idMaquina) {
		this.idMaquina = idMaquina;
	}

	public String getOf() {
		return of;
	}

	public void setOf(String of) {
		this.of = of;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getPieza() {
		return pieza;
	}

	public void setPieza(String pieza) {
		this.pieza = pieza;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

}
