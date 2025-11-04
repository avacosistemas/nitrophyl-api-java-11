package ar.com.avaco.nitrophyl.domain.entities.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CLIENTE_CONTACTO")
public class Contacto extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = -3260414467249766837L;

	@Id
	@GeneratedValue(generator = "CLIENTE_CONTACTO_SEQ")
	@GenericGenerator(name = "CLIENTE_CONTACTO_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "CLIENTE_CONTACTO_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_CONTACTO")
	private Long id;

	/**
	 * El cliente al cual pertenece el contacto.
	 */
	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE", nullable = false)
	private Cliente cliente;

	/**
	 * Tipo de contacto (COMPRAS, CONTADURIA, CALIDAD).
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_CONTACTO", nullable = false)
	private TipoContacto tipo;

	/**
	 * Nombre de la persona responsable con quien contactarse en caso de ser
	 * empresa.
	 */
	@Column(name = "NOMBRE_CONTACTO", nullable = false)
	private String nombre;

	/**
	 * Email del contacto.
	 */
	@Column(name = "EMAIL_CONTACTO")
	private String email;

	/**
	 * Telefono del contacto
	 */
	@Column(name = "TEL_CONTACTO")
	private String telefono;

	public Contacto() {
	}

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

	public TipoContacto getTipo() {
		return tipo;
	}

	public void setTipo(TipoContacto tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
