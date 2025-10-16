package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.moldes.TipoDimension;

@Entity
@Table(name = "PIEZA_DIMENSION")
@SequenceGenerator(name = "PIEZA_DIMENSION_SEQ", sequenceName = "PIEZA_DIMENSION_SEQ", allocationSize = 1)
public class PiezaDimension extends AuditableEntity<Long> {

	private static final long serialVersionUID = 7387245754379595320L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PIEZA_DIMENSION_SEQ")
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
