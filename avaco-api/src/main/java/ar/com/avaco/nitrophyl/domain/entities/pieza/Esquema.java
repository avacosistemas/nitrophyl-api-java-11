package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "ESQUEMA")
@SequenceGenerator(name = "ESQUEMA_SEQ", sequenceName = "ESQUEMA_SEQ", allocationSize = 1)
public class Esquema extends AuditableEntity<Long> {

	private static final long serialVersionUID = 4586583639462393466L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESQUEMA_SEQ")
	@Column(name = "ID_ESQUEMA", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_PROCESO")
	private Proceso proceso;

	@Column(name = "TITULO")
	private String titulo;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "esquema", orphanRemoval = true)
	private Set<EsquemaPaso> pasos = new HashSet<>();

	@Column(name = "IMAGEN", nullable = true)
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] imagen;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public Set<EsquemaPaso> getPasos() {
		return pasos;
	}

	public void setPasos(Set<EsquemaPaso> pasos) {
		this.pasos = pasos;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Esquema clonar(String username, Date fechaHora, Proceso proceso) {
		Esquema clonada = new Esquema();
		clonada.resetearCreacion(username, fechaHora);
		clonada.setImagen(imagen);
		this.pasos.forEach(paso -> clonada.getPasos().add(paso.clonar(username, fechaHora, clonada)));
		clonada.setProceso(proceso);
		clonada.setTitulo(titulo);
		return clonada;
	}

}
