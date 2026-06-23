package ar.com.avaco.nitrophyl.domain.entities.fabricacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "ORDEN_FABRICACION_CHECK_CONTROL")
public class OrdenFabricacionControl extends AuditableEntity<Long> {

	private static final long serialVersionUID = 5385730709560346853L;

	@Id
	@GeneratedValue(generator = "ORDEN_FABRICACION_CONTROL_SEQ")
	@GenericGenerator(name = "ORDEN_FABRICACION_CONTROL_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "ORDEN_FABRICACION_CONTROL_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_ORDEN_FABRICACION_CONTROL", unique = true, nullable = false)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_ORDEN_FAB_ENT")
	private OrdenFabricacionEntrega ordenFabricacionEntrega;

	@Column(name = "CONTROL")
	private String control;

	@Column(name = "APROBADO")
	private Boolean aprobado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public Boolean getAprobado() {
		return aprobado;
	}

	public void setAprobado(Boolean aprobado) {
		this.aprobado = aprobado;
	}

	public OrdenFabricacionEntrega getOrdenFabricacionEntrega() {
		return ordenFabricacionEntrega;
	}

	public void setOrdenFabricacionEntrega(OrdenFabricacionEntrega ordenFabricacionEntrega) {
		this.ordenFabricacionEntrega = ordenFabricacionEntrega;
	}

}
