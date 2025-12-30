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

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "MOLDE_BOCA")
@Inheritance(strategy = InheritanceType.JOINED)
public class MoldeBoca extends AuditableEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "MOLDEBOCA_SEQ")
	@GenericGenerator(name = "MOLDEBOCA_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "MOLDEBOCA_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_MOLDE_BOCA", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_MOLDE", insertable = false, updatable = false)
	private Molde molde;

	@Column(name = "ID_MOLDE")
	private Long idMolde;

	@Column(name = "NROBOCA", unique = false, nullable = false, updatable = false)
	private Integer nroBoca;

	@Column(name = "DESCRIPCION", unique = false, nullable = false)
	private String descripcion;

	@Column(name = "ESTADO", unique = false, nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoBoca estado;

	public MoldeBoca() {
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

	public Integer getNroBoca() {
		return nroBoca;
	}

	public void setNroBoca(Integer nroBoca) {
		this.nroBoca = nroBoca;
	}

	public EstadoBoca getEstado() {
		return estado;
	}

	public void setEstado(EstadoBoca estado) {
		this.estado = estado;
	}

	public Long getIdMolde() {
		return idMolde;
	}

	public void setIdMolde(Long idMolde) {
		this.idMolde = idMolde;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
