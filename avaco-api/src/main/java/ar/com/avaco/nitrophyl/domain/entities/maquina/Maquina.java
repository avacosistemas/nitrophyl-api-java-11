package ar.com.avaco.nitrophyl.domain.entities.maquina;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "MAQUINA")
@Inheritance(strategy = InheritanceType.JOINED)
public class Maquina extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = -2296428532611007942L;

	@Id
	@GeneratedValue(generator = "MAQUINA_SEQ")
	@GenericGenerator(name = "MAQUINA_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "MAQUINA_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_MAQUINA", unique = true, nullable = false)
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "NORMA")
	private String norma;

	@Column(name = "OBSERVACIONES_REPORTE")
	private String observacionesReporte;

	@Column(name = "ESTADO")
	@Enumerated(EnumType.STRING)
	private EstadoMaquina estado;

	@Column(name = "POSICION")
	private Integer posicion;

	@Column(name = "VERSIONABLE")
	private boolean versionable;

	@OneToMany(targetEntity = MaquinaPrueba.class, mappedBy = "maquina", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<MaquinaPrueba> pruebas = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EstadoMaquina getEstado() {
		return estado;
	}

	public void setEstado(EstadoMaquina estado) {
		this.estado = estado;
	}

	public Set<MaquinaPrueba> getPruebas() {
		return pruebas;
	}

	public void setPruebas(Set<MaquinaPrueba> pruebas) {
		this.pruebas = pruebas;
	}

	public String getObservacionesReporte() {
		return observacionesReporte;
	}

	public void setObservacionesReporte(String observacionesReporte) {
		this.observacionesReporte = observacionesReporte;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public boolean isVersionable() {
		return versionable;
	}

	public void setVersionable(boolean versionable) {
		this.versionable = versionable;
	}

	public String getNorma() {
		return norma;
	}

	public void setNorma(String norma) {
		this.norma = norma;
	}

}
