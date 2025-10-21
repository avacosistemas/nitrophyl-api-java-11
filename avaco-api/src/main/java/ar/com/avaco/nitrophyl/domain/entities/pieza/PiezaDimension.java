package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.Date;

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
import ar.com.avaco.nitrophyl.domain.entities.moldes.TipoDimension;

@Entity
@Table(name = "PIEZA_DIMENSION")
public class PiezaDimension extends AuditableEntity<Long> {

	private static final long serialVersionUID = 7387245754379595320L;

	@Id
	@GeneratedValue(generator = "PIEZA_DIMENSION_SEQ")
	@GenericGenerator(name = "PIEZA_DIMENSION_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "PIEZA_DIMENSION_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_PIEZA_DIMENSION", unique = true, nullable = false)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_PIEZA")
	private Pieza pieza;

	@Column(name = "TIPO")
	@Enumerated(EnumType.STRING)
	private TipoDimension tipo;

	@Column(name = "VALOR")
	private Integer valor;

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	public PiezaDimension clonar(String username, Date fechaHora, Pieza pieza) {
		PiezaDimension clonada = new PiezaDimension();
		clonada.resetearCreacion(username, fechaHora);
		clonada.setObservaciones(observaciones);
		clonada.setPieza(pieza);
		clonada.setTipo(tipo);
		clonada.setValor(valor);
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

	public TipoDimension getTipo() {
		return tipo;
	}

	public void setTipo(TipoDimension tipo) {
		this.tipo = tipo;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
