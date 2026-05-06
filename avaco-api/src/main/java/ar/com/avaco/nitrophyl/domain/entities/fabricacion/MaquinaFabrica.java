package ar.com.avaco.nitrophyl.domain.entities.fabricacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "MAQUINA_FABRICA")
public class MaquinaFabrica {

	@Id
	@GeneratedValue(generator = "MAQUINA_FABRICA_SEQ")
	@GenericGenerator(name = "MAQUINA_FABRICA_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "MAQUINA_FABRICA_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_MAQUINA_FABRICA", unique = true, nullable = false)
	private Long id;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO", nullable = false)
	private TipoMaquinaFabrica tipo;

	@Column(name = "SECCION")
	private String seccion;

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

	public TipoMaquinaFabrica getTipo() {
		return tipo;
	}

	public void setTipo(TipoMaquinaFabrica tipo) {
		this.tipo = tipo;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

}
