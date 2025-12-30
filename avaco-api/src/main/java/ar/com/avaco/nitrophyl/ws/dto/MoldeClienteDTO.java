package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.molde.Molde;

public class MoldeClienteDTO {

	private Long idMolde;
	private Long idCliente;
	private String nombre;

	public MoldeClienteDTO() {
		super();
	}

	public MoldeClienteDTO(Cliente cliente, Molde molde) {
		super();
		this.idMolde = molde.getId();
		this.idCliente = cliente.getId();
		this.nombre = cliente.getNombre();
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
