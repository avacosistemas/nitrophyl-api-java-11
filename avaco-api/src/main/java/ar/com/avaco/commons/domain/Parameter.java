/**
 * 
 */
package ar.com.avaco.commons.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author avaco
 */

@Entity
@Table(name = "PARAMETER")
public class Parameter extends ar.com.avaco.arc.core.domain.Entity<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8168268668794827990L;

	@Id
	@GeneratedValue(generator = "PARAMETER_SEQ")
	@GenericGenerator(name = "PARAMETER_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "PARAMETER_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_PARAMETER")
	private Integer id;

	@Column(name = "key")
	private String key;

	@Column(name = "value")
	private String value;

	@Column(name = "description")
	private String description;
	
	public Parameter() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}