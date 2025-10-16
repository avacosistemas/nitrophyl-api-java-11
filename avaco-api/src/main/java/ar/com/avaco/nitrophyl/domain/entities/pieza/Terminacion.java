package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "TERMINACION")
public class Terminacion extends AuditableEntity<Long> {

	private static final long serialVersionUID = -5745677178144225795L;

	@Id
	@Column(name = "ID_TERMINACION", unique = true, nullable = false)
	private Long id;

	@OneToOne
	@MapsId
	@JoinColumn(name = "ID_TERMINACION")
	private Proceso proceso;

	@Column(name = "REFILADO")
	private String refilado;

	@Column(name = "IDENTIFICACION")
	private String identificacion;

	@Column(name = "EMBALAJE")
	private String embalaje;

	@Column(name = "IMAGEN_TERMINADA", nullable = true)
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] imagenTerminada;

	public String getRefilado() {
		return refilado;
	}

	public void setRefilado(String refilado) {
		this.refilado = refilado;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getEmbalaje() {
		return embalaje;
	}

	public void setEmbalaje(String embalaje) {
		this.embalaje = embalaje;
	}

	public byte[] getImagenTerminada() {
		return imagenTerminada;
	}

	public void setImagenTerminada(byte[] imagenTerminada) {
		this.imagenTerminada = imagenTerminada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Terminacion clonar(String username, Date fechaHora, Proceso proceso) {
		Terminacion clonada = new Terminacion();
		clonada.resetearCreacion(username, fechaHora);
		clonada.setEmbalaje(embalaje);
		clonada.setIdentificacion(identificacion);
		clonada.setImagenTerminada(imagenTerminada);
		clonada.setProceso(proceso);
		clonada.setRefilado(refilado);
		return clonada;
	}

}
