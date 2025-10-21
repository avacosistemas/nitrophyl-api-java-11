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
import org.hibernate.annotations.Type;

@Entity
@Table(name = "MOLDEPLANO")
@Inheritance(strategy = InheritanceType.JOINED)
public class MoldePlano extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = 1176263407598988058L;

	@Id
	@GeneratedValue(generator = "MOLDEPLANO_SEQ")
	@GenericGenerator(name = "MOLDEPLANO_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "MOLDEPLANO_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_MOLDE_PLANO", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_MOLDE", insertable = false, updatable = false)
	private Molde molde;

	@Column(name = "ID_MOLDE")
	private Long idMolde;

	@Column(name = "NOMBREARCHIVO")
	private String nombreArchivo;

	@Column(name = "VERSION")
	private Integer version;

	@Column(name = "FECHA")
	private Date fecha;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Column(name = "ARCHIVO", nullable = true)
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] archivo;

	@Enumerated(EnumType.STRING)
	private PlanoClasificacion clasificacion;

	public MoldePlano() {
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

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public PlanoClasificacion getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(PlanoClasificacion clasificacion) {
		this.clasificacion = clasificacion;
	}

}
