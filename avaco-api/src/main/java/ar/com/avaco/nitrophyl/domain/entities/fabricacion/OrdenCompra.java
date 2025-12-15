package ar.com.avaco.nitrophyl.domain.entities.fabricacion;

import java.util.Date;

import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;

public class OrdenCompra {

	private Long id;

	private Cliente cliente;

	private String nroComprobante;

	// Ver de usarlo para saber si esa orden de compra ya se completó o no de manera total o parcial.
	// PENDIENTE
	// ASIGNADA PARCIAL
	// ASIGNADA TOTAL
	// Si esta pendiente o asignada parcial debería poder generse una orden de fabricacion 
	private String estado;
	
	// codigo del cliente + secuencial + anio sugerir
	private String nroInterno;

	private Date fecha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNroComprobante() {
		return nroComprobante;
	}

	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	public String getNroInterno() {
		return nroInterno;
	}

	public void setNroInterno(String nroInterno) {
		this.nroInterno = nroInterno;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
