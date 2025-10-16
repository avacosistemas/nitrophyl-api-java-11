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
@Table(name = "MOLDEBOCA")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "MOLDEBOCA_SEQ", sequenceName = "MOLDEBOCA_SEQ", allocationSize = 1)
public class MoldeBoca extends ar.com.avaco.arc.core.domain.Entity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOLDEBOCA_SEQ")
	@Column(name = "ID_MOLDE_BOCA", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_MOLDE", insertable = false, updatable = false)
	private Molde molde;

	@Column(name = "ID_MOLDE")
	private Long idMolde;

	@Column(name = "NROBOCA", unique = false, nullable = false)
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
