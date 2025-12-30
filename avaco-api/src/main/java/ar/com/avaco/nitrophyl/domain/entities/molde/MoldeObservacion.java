package ar.com.avaco.nitrophyl.domain.entities.molde;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "MOLDE_OBSERVACION")
@Inheritance(strategy = InheritanceType.JOINED)
public class MoldeObservacion extends AuditableEntity<Long> {

	private static final long serialVersionUID = -2995718999288355222L;

	@Id
	@GeneratedValue(generator = "MOLDE_OBS_SEQ")
	@GenericGenerator(name = "MOLDE_OBS_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "MOLDE_OBS_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
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
