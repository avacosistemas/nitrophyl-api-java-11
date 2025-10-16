package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.fabrica.Prensa;

@Entity
@Table(name = "PROCESO")
public class Proceso extends AuditableEntity<Long> {

	private static final long serialVersionUID = -9161533934017681173L;

	@Id
	@Column(name = "ID_PROCESO", unique = true, nullable = false)
	private Long id;

	@OneToOne
	@MapsId
	@JoinColumn(name = "ID_PROCESO")
	private Pieza pieza;

	@Embedded
	private Precalentamiento precalentamiento = new Precalentamiento();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "PROCESO_PRENSA", joinColumns = @JoinColumn(name = "ID_PROCESO", referencedColumnName = "ID_PROCESO"), inverseJoinColumns = @JoinColumn(name = "ID_PRENSA", referencedColumnName = "ID_PRENSA"))
	@Fetch(FetchMode.SELECT)
	private Set<Prensa> prensas = new HashSet<>();

	@Embedded
	private Vulcanizacion vulcanizacion = new Vulcanizacion();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "proceso", orphanRemoval = true)
	private Set<Bombeo> bombeos = new HashSet<>();

	@Column(name = "DESMOLDANTE")
	private String desmoldante;

	@Column(name = "POSTCURA")
	private String postCura;

	@OneToOne(mappedBy = "proceso", cascade = CascadeType.ALL, orphanRemoval = true)
	private Terminacion terminacion;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "proceso", orphanRemoval = true)
	private Set<Esquema> esquema = new HashSet<>();

	public Proceso clonar(String username, Date fechaHora, Pieza pieza) {
		Proceso clonada = new Proceso();
		clonada.resetearCreacion(username, fechaHora);
		this.bombeos.forEach(bombeo -> clonada.getBombeos().add(bombeo.clonar(username, fechaHora, clonada)));
		this.esquema.forEach(esquema -> clonada.getEsquema().add(esquema.clonar(username, fechaHora, clonada)));
		clonada.setDesmoldante(desmoldante);
		clonada.setPieza(pieza);
		clonada.setPostCura(postCura);
		clonada.setPrecalentamiento(precalentamiento);
		clonada.getPrensas().addAll(prensas);
		clonada.setTerminacion(terminacion.clonar(username, fechaHora, clonada));
		clonada.setVulcanizacion(vulcanizacion);
		return clonada;
	}

	public Terminacion getTerminacion() {
		return terminacion;
	}

	public void setTerminacion(Terminacion terminacion) {
		this.terminacion = terminacion;
	}

	public Precalentamiento getPrecalentamiento() {
		return precalentamiento;
	}

	public void setPrecalentamiento(Precalentamiento precalentamiento) {
		this.precalentamiento = precalentamiento;
	}

	public Set<Prensa> getPrensas() {
		return prensas;
	}

	public void setPrensas(Set<Prensa> prensas) {
		this.prensas = prensas;
	}

	public Vulcanizacion getVulcanizacion() {
		return vulcanizacion;
	}

	public void setVulcanizacion(Vulcanizacion vulcanizacion) {
		this.vulcanizacion = vulcanizacion;
	}

	public Set<Bombeo> getBombeos() {
		return bombeos;
	}

	public void setBombeos(Set<Bombeo> bombeos) {
		this.bombeos = bombeos;
	}

	public String getDesmoldante() {
		return desmoldante;
	}

	public void setDesmoldante(String desmoldante) {
		this.desmoldante = desmoldante;
	}

	public String getPostCura() {
		return postCura;
	}

	public void setPostCura(String postCura) {
		this.postCura = postCura;
	}

	public Set<Esquema> getEsquema() {
		return esquema;
	}

	public void setEsquema(Set<Esquema> esquema) {
		this.esquema = esquema;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	public static Proceso ofId(Long id) {
		Proceso p = new Proceso();
		p.setId(id);
		p.setPieza(Pieza.ofId(id));
		return p;
	}

}
