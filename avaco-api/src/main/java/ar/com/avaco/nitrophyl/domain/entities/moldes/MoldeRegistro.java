package ar.com.avaco.nitrophyl.domain.entities.moldes;

import java.util.Date;

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
@Table(name = "MOLDEREGISTRO")
@Inheritance(strategy = InheritanceType.JOINED)
public class MoldeRegistro extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "MOLDEREGISTRO_SEQ")
	@GenericGenerator(name = "MOLDEREGISTRO_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "MOLDEREGISTRO_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
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
