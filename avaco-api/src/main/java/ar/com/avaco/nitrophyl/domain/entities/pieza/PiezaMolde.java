package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.moldes.Molde;

@Entity
@Table(name = "PIEZA_MOLDE")
public class PiezaMolde extends AuditableEntity<Long> {

	private static final long serialVersionUID = 8149041860982988369L;

	@Id
	@GeneratedValue(generator = "PIEZA_MOLDE_SEQ")
	@GenericGenerator(name = "PIEZA_MOLDE_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "PIEZA_MOLDE_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_PIEZA_MOLDE", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_PIEZA")
	private Pieza pieza;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_MOLDE")
	private Molde molde;

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	public PiezaMolde clonar(String username, Date fechaHora, Pieza pieza) {
		PiezaMolde clonada = new PiezaMolde();
		clonada.resetearCreacion(username, fechaHora);
		clonada.setMolde(this.molde);
		clonada.setObservaciones(this.observaciones);
		clonada.setPieza(pieza);
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

	public Molde getMolde() {
		return molde;
	}

	public void setMolde(Molde molde) {
		this.molde = molde;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
