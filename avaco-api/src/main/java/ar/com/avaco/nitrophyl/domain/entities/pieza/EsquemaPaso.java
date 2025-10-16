package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "ESQUEMA_PASO")
@SequenceGenerator(name = "ESQUEMA_PASO_SEQ", sequenceName = "ESQUEMA_PASO_SEQ", allocationSize = 1)
public class EsquemaPaso extends AuditableEntity<Long> {

	private static final long serialVersionUID = 3947885269693718530L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESQUEMA_PASO_SEQ")
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
