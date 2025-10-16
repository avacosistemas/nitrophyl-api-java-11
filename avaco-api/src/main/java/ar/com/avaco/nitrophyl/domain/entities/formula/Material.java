package ar.com.avaco.nitrophyl.domain.entities.formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MATERIAL")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "MATERIAL_SEQ", sequenceName = "MATERIAL_SEQ", allocationSize = 1)
public class Material extends ar.com.avaco.arc.core.domain.Entity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8297742390134560118L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATERIAL_SEQ")
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
