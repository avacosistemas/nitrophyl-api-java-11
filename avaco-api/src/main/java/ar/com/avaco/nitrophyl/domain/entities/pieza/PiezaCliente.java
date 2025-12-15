package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;

@Entity
@Table(name = "PIEZA_CLIENTE")
public class PiezaCliente extends AuditableEntity<Long> {

	private static final long serialVersionUID = 7387245754379595320L;

	@Id
	@GeneratedValue(generator = "PIEZA_CLIENTE_SEQ")
	@GenericGenerator(name = "PIEZA_CLIENTE_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "PIEZA_CLIENTE_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_PIEZA_CLIENTE", unique = true, nullable = false)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_PIEZA")
	private Pieza pieza;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;

	@Column(name = "NOMBRE_PIEZA_PERSONALIZADO")
	private String nombrePiezaPersonalizado;

	public PiezaCliente clonar(String username, Date fechaHora, Pieza pieza) {
		PiezaCliente clonada = new PiezaCliente();
		clonada.resetearCreacion(username, fechaHora);
		clonada.setPieza(pieza);
		clonada.setNombrePiezaPersonalizado(nombrePiezaPersonalizado);
		clonada.setCliente(cliente);
		return clonada;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNombrePiezaPersonalizado() {
		return nombrePiezaPersonalizado;
	}

	public void setNombrePiezaPersonalizado(String nombrePiezaPersonalizado) {
		this.nombrePiezaPersonalizado = nombrePiezaPersonalizado;
	}

}
