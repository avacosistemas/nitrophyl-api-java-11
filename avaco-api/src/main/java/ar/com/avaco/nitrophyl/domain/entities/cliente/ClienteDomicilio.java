package ar.com.avaco.nitrophyl.domain.entities.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

/**
 * @author el betazo
 *
 */
@Entity
@Table(name = "CLIENTE_DOMICILIO")
@Inheritance(strategy = InheritanceType.JOINED)
public class ClienteDomicilio extends AuditableEntity<Long> {

	private static final long serialVersionUID = -7000386488020724188L;

	@Id
	@GeneratedValue(generator = "CLIENTE_DOMICILIO_SEQ")
	@GenericGenerator(name = "CLIENTE_DOMICILIO_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "CLIENTE_DOMICILIO_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_CLIENTE_DOMICILIO")
	private Long id;

	@Column(name = "DOMICILIO")
	private String domicilio;

	@Column(name = "TIPO")
	@Enumerated(EnumType.STRING)
	private TipoContacto tipo;

	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE", nullable = false)
	private Cliente cliente;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static ClienteDomicilio ofId(Long idClienteDomicilio) {
		ClienteDomicilio cliente = new ClienteDomicilio();
		cliente.setId(idClienteDomicilio);
		return cliente;
	}

}
