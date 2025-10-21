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

@Entity
@Table(name = "ESQUEMA_PASO")
public class EsquemaPaso extends AuditableEntity<Long> {

	private static final long serialVersionUID = 3947885269693718530L;

	@Id
	@GeneratedValue(generator = "ESQUEMA_PASO_SEQ")
	@GenericGenerator(name = "ESQUEMA_PASO_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "ESQUEMA_PASO_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_ESQUEMA_PASO", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_ESQUEMA")
	private Esquema esquema;

	@Column(name = "PASO")
	private Long paso;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	public Esquema getEsquema() {
		return esquema;
	}

	public void setEsquema(Esquema esquema) {
		this.esquema = esquema;
	}

	public Long getPaso() {
		return paso;
	}

	public void setPaso(Long paso) {
		this.paso = paso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EsquemaPaso clonar(String username, Date fechaHora, Esquema esquema) {
		EsquemaPaso clonada = new EsquemaPaso();
		clonada.resetearCreacion(username, fechaHora);
		clonada.setDescripcion(descripcion);
		clonada.setEsquema(esquema);
		clonada.setPaso(paso);
		return clonada;
	}

}
