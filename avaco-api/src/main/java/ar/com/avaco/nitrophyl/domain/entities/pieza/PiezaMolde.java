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
import ar.com.avaco.nitrophyl.domain.entities.moldes.Molde;

@Entity
@Table(name = "PIEZA_MOLDE")
@SequenceGenerator(name = "PIEZA_MOLDE_SEQ", sequenceName = "PIEZA_MOLDE_SEQ", allocationSize = 1)
public class PiezaMolde extends AuditableEntity<Long> {

	private static final long serialVersionUID = 8149041860982988369L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PIEZA_MOLDE_SEQ")
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
