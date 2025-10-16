package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.moldes.PlanoClasificacion;

@Entity
@Table(name = "PIEZA_PLANO")
@SequenceGenerator(name = "PIEZA_PLANO_SEQ", sequenceName = "PIEZA_PLANO_SEQ", allocationSize = 1)
public class PiezaPlano extends AuditableEntity<Long> {

	private static final long serialVersionUID = 6238103037912066925L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PIEZA_PLANO_SEQ")
	@Column(name = "ID_PIEZA_PLANO", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_PIEZA")
	private Pieza pieza;
	
	@Column(name = "ARCHIVO", nullable = true)
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] archivo;

	@Column(name = "CODIGO", nullable = true)
	private String codigo;

	@Column(name = "REVISION")
	private Integer revision;

	@Column(name = "CLASIFICACION")
	@Enumerated(EnumType.STRING)
	private PlanoClasificacion clasificacion;

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	public PiezaPlano clonar(String username, Date fechaHora, Pieza pieza) {
		PiezaPlano clonada = new PiezaPlano();
		clonada.resetearCreacion(username, fechaHora);
		clonada.setArchivo(this.archivo);
		clonada.setClasificacion(clasificacion);
		clonada.setCodigo(codigo);
		clonada.setObservaciones(observaciones);
		clonada.setPieza(pieza);
		clonada.setRevision(revision);
		return clonada;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getRevision() {
		return revision;
	}

	public void setRevision(Integer revision) {
		this.revision = revision;
	}

	public PlanoClasificacion getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(PlanoClasificacion clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

}
