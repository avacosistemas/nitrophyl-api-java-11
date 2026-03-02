package ar.com.avaco.nitrophyl.domain.entities.pieza;

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

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "PIEZA_CONTROL")
public class PiezaControl extends AuditableEntity<Long> {

	private static final long serialVersionUID = 2865115894963877402L;

	@Id
	@GeneratedValue(generator = "PIEZA_CONTROL_SEQ")
	@GenericGenerator(name = "PIEZA_CONTROL_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "PIEZA_CONTROL_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_PIEZA_CONTROL", unique = true, nullable = false)
	private Long id;

	@Column(name = "CONTROL", nullable = false)
	private String control;

	@ManyToOne
	@JoinColumn(name = "ID_PIEZA", nullable = false)
	private Pieza pieza;

	@Column(name = "TIPO")
	@Enumerated(EnumType.STRING)
	private TipoControl tipo;

	public PiezaControl() {
		// TODO Auto-generated constructor stub
	}

	public PiezaControl(String control, Pieza pieza, TipoControl tipo) {
		super();
		this.control = control;
		this.pieza = pieza;
		this.tipo = tipo;
	}

	public TipoControl getTipo() {
		return tipo;
	}

	public void setTipo(TipoControl tipo) {
		this.tipo = tipo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

}
