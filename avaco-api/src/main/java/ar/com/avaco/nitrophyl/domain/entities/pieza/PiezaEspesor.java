package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "PIEZA_ESPESOR")
@SequenceGenerator(name = "PIEZA_ESPESOR_SEQ", sequenceName = "PIEZA_ESPESOR_SEQ", allocationSize = 1)
public class PiezaEspesor extends AuditableEntity<Long> {

	private static final long serialVersionUID = 8149041860982988369L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PIEZA_ESPESOR_SEQ")
	@Column(name = "ID_PIEZA_ESPESOR", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_PIEZA")
	private Pieza pieza;

	@Column(name = "ESPESOR_MIN")
	private Double espesorMinimo;

	@Column(name = "ESPESOR_MAX")
	private Double espesorMaximo;

	public PiezaEspesor clonar(String username, Date fechaHora, Pieza pieza) {
		PiezaEspesor clonada = new PiezaEspesor();
		clonada.resetearCreacion(username, fechaHora);
		clonada.setEspesorMinimo(this.espesorMinimo);
		clonada.setEspesorMaximo(this.espesorMaximo);
		clonada.setPieza(pieza);
		return clonada;
	}

	public Double getEspesorMinimo() {
		return espesorMinimo;
	}

	public void setEspesorMinimo(Double espesorMinimo) {
		this.espesorMinimo = espesorMinimo;
	}

	public Double getEspesorMaximo() {
		return espesorMaximo;
	}

	public void setEspesorMaximo(Double espesorMaximo) {
		this.espesorMaximo = espesorMaximo;
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

}
