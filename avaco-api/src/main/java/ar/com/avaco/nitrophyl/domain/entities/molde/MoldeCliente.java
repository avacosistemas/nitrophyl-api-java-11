package ar.com.avaco.nitrophyl.domain.entities.molde;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;

@Entity
@Table(name = "MOLDE_CLIENTE")
public class MoldeCliente extends ar.com.avaco.arc.core.domain.Entity<MoldeClienteId> {

	private static final long serialVersionUID = 2405429573329459054L;

	@EmbeddedId
	private MoldeClienteId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idMolde")
	@JoinColumn(name = "ID_MOLDE")
	private Molde molde;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idCliente")
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	public MoldeCliente() {
	}

	public MoldeCliente(Molde molde, Cliente cliente) {
		this.molde = molde;
		this.cliente = cliente;
		this.id = new MoldeClienteId(molde.getId(), cliente.getId());
	}

	public MoldeClienteId getId() {
		return id;
	}

	public void setId(MoldeClienteId id) {
		this.id = id;
	}

	public Molde getMolde() {
		return molde;
	}

	public void setMolde(Molde molde) {
		this.molde = molde;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
