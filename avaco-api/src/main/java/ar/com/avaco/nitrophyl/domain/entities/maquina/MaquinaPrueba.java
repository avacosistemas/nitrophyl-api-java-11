package ar.com.avaco.nitrophyl.domain.entities.maquina;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "MAQUINA_PRUEBA")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "MAQUINA_PRUEBA_SEQ", sequenceName = "MAQUINA_PRUEBA_SEQ", allocationSize = 1)
public class MaquinaPrueba extends ar.com.avaco.arc.core.domain.Entity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2296428532611007942L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MAQUINA_PRUEBA_SEQ")
	@Column(name = "ID_MAQUINA_PRUEBA", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_MAQUINA", insertable = false, updatable = false)
	private Maquina maquina;

	@Column(name = "ID_MAQUINA")
	private Long idMaquina;

	@Column(name = "NOMBRE")
	private String nombre;

	private Integer posicion;

	public MaquinaPrueba() {
	}

	public MaquinaPrueba(String nombre, Long idMaquina, int posicion) {
		this.nombre = nombre;
		this.idMaquina = idMaquina;
		this.posicion = posicion;
	}

	public MaquinaPrueba(Long idMaquinaPrueba) {
		this.id = idMaquinaPrueba;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(Long idMaquina) {
		this.idMaquina = idMaquina;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

}
