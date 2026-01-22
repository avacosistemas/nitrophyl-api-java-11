package ar.com.avaco.nitrophyl.domain.entities.molde;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MoldeClienteId implements Serializable {

	private static final long serialVersionUID = 3474087386414431438L;

	@Column(name = "ID_MOLDE")
	private Long idMolde;

	@Column(name = "ID_CLIENTE")
	private Long idCliente;

	public MoldeClienteId() {
	}

	public MoldeClienteId(Long idMolde, Long idCliente) {
		this.idMolde = idMolde;
		this.idCliente = idCliente;
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof MoldeClienteId))
			return false;
		MoldeClienteId that = (MoldeClienteId) o;
		return Objects.equals(idMolde, that.idMolde) && Objects.equals(idCliente, that.idCliente);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMolde, idCliente);
	}
}
