package ar.com.avaco.nitrophyl.domain.entities.moldes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MOLDEDIMENSION")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "MOLDEDIMENSION_SEQ", sequenceName = "MOLDEDIMENSION_SEQ", allocationSize = 1)
public class MoldeDimension extends ar.com.avaco.arc.core.domain.Entity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOLDEDIMENSION_SEQ")
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
