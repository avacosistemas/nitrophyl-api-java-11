package ar.com.avaco.nitrophyl.domain.entities.moldes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "MOLDE_OBSERVACION")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "MOLDE_OBS_SEQ", sequenceName = "MOLDE_OBS_SEQ", allocationSize = 1)
public class MoldeObservacion extends AuditableEntity<Long> {

	private static final long serialVersionUID = -2995718999288355222L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOLDE_OBS_SEQ")
	@Column(name = "ID_MOLDE_OBS", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_MOLDE", insertable = false, updatable = false)
	private Molde molde;

	@Column(name = "ID_MOLDE")
	private Long idMolde;

	@Column(name = "OBSERVACION")
	private String observacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Molde getMolde() {
		return molde;
	}

	public void setMolde(Molde molde) {
		this.molde = molde;
	}

	public Long getIdMolde() {
		return idMolde;
	}

	public void setIdMolde(Long idMolde) {
		this.idMolde = idMolde;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
