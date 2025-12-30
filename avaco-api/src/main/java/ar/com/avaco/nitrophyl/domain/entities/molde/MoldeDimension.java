package ar.com.avaco.nitrophyl.domain.entities.molde;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "MOLDEDIMENSION")
@Inheritance(strategy = InheritanceType.JOINED)
public class MoldeDimension extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "MOLDEDIMENSION_SEQ")
	@GenericGenerator(name = "MOLDEDIMENSION_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "MOLDEDIMENSION_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_MOLDE_DIMENSION", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_MOLDE", insertable = false, updatable = false)
	private Molde molde;
	
	@Column(name = "ID_MOLDE")
	private Long idMolde;
	

	@Column(name = "TIPODIMENSION")
	@Enumerated(EnumType.STRING)
	private TipoDimension tipodimension;

	@Column(name = "VALORDIMENSION")
	private Integer valordimension;

	public MoldeDimension() {
		super();
	}

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

	public TipoDimension getTipodimension() {
		return tipodimension;
	}

	public void setTipodimension(TipoDimension tipodimension) {
		this.tipodimension = tipodimension;
	}

	public Integer getValordimension() {
		return valordimension;
	}

	public void setValordimension(Integer valordimension) {
		this.valordimension = valordimension;
	}

	public Long getIdMolde() {
		return idMolde;
	}

	public void setIdMolde(Long idMolde) {
		this.idMolde = idMolde;
	}

}
