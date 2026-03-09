package ar.com.avaco.nitrophyl.domain.entities.pieza.insumo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "INSUMO_TRATADO_OBS_CONTROL")
public class InsumoTratadoObservacionControl extends AuditableEntity<Long> {

	private static final long serialVersionUID = -4766509268712069513L;

	@Id
	@GeneratedValue(generator = "INSUMO_TRATADO_OBS_CONTROL_SEQ")
	@GenericGenerator(name = "INSUMO_TRATADO_OBS_CONTROL_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "INSUMO_TRATADO_OBS_CONTROL_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_INSUMO_TRATADO_OBS_CONTROL", unique = true, nullable = false)
	private Long id;

	@Column(name = "OBSERVACION")
	private String observacion;

	@Column(name = "CONTROLAR")
	private Boolean controlar;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "ID_INSUMO_TRATADO")
	private InsumoTratado insumoTratado;

	public InsumoTratadoObservacionControl() {
		// TODO Auto-generated constructor stub
	}

	public InsumoTratadoObservacionControl(String observacion, Boolean controlar, InsumoTratado insumoTratado) {
		super();
		this.observacion = observacion;
		this.controlar = controlar;
		this.insumoTratado = insumoTratado;
	}

	public InsumoTratado getInsumoTratado() {
		return insumoTratado;
	}

	public void setInsumoTratado(InsumoTratado insumoTratado) {
		this.insumoTratado = insumoTratado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Boolean getControlar() {
		return controlar;
	}

	public void setControlar(Boolean controlar) {
		this.controlar = controlar;
	}

	public static InsumoTratadoObservacionControl ofId(Long id) {
		InsumoTratadoObservacionControl insumo = new InsumoTratadoObservacionControl();
		insumo.setId(id);
		return insumo;
	}

}
