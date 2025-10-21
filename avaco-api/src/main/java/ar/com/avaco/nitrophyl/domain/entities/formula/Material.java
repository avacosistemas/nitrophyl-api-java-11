package ar.com.avaco.nitrophyl.domain.entities.formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "MATERIAL")
@Inheritance(strategy = InheritanceType.JOINED)
public class Material extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = 8297742390134560118L;

	@Id
	@GeneratedValue(generator = "MATERIAL_SEQ")
	@GenericGenerator(name = "MATERIAL_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "MATERIAL_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_MATERIAL")
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "CODIGO")
	private String codigo;

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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
