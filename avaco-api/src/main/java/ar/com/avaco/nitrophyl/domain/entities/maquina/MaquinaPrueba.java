package ar.com.avaco.nitrophyl.domain.entities.maquina;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "MAQUINA_PRUEBA")
@Inheritance(strategy = InheritanceType.JOINED)
public class MaquinaPrueba extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = -2296428532611007942L;

	@Id
	@GeneratedValue(generator = "MAQUINA_PRUEBA_SEQ")
	@GenericGenerator(name = "MAQUINA_PRUEBA_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "MAQUINA_PRUEBA_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
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
