package ar.com.avaco.arc.sec.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PARAMETRO_GENERAL")
public class ParametroGeneral {

	public static final String DATOS_GENERADOS = "DATOS_GENERADOS";

	@Id
	@GeneratedValue(generator = "PARAMETRO_GENERAL_SEQ")
	@GenericGenerator(name = "PARAMETRO_GENERAL_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "PARAMETRO_GENERAL_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_PARAMETRO_GENERAL")
	private Long id;

	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}