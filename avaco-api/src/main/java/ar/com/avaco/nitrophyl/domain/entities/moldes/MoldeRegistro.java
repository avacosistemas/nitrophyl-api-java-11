package ar.com.avaco.nitrophyl.domain.entities.moldes;

import java.util.Date;

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
@Table(name = "MOLDEREGISTRO")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "MOLDEREGISTRO_SEQ", sequenceName = "MOLDEREGISTRO_SEQ", allocationSize = 1)
public class MoldeRegistro extends ar.com.avaco.arc.core.domain.Entity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOLDEREGISTRO_SEQ")
	@Column(name = "ID_MOLDE_REGISTRO", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_MOLDE", insertable = false, updatable = false)
	private Molde molde;

	@Column(name = "ID_MOLDE")
	private Long idMolde;

	@Column(name = "TIPOREGISTRO", unique = false, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoRegistroMolde tipoRegistro;

	@Column(name = "FECHA", unique = false, nullable = false)
	private Date fecha;

	@Column(name = "COMENTARIOS", unique = false, nullable = false)
	private String comentarios;

	public MoldeRegistro() {
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

	public Long getIdMolde() {
		return idMolde;
	}

	public void setIdMolde(Long idMolde) {
		this.idMolde = idMolde;
	}

	public TipoRegistroMolde getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(TipoRegistroMolde tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

}
