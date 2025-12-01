package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.nitrophyl.domain.entities.cliente.TipoContacto;
import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class ClienteDomicilioDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private Long idCliente;

	private String domicilio;

	private TipoContacto tipo;

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public TipoContacto getTipo() {
		return tipo;
	}

	public void setTipo(TipoContacto tipo) {
		this.tipo = tipo;
	}

}
