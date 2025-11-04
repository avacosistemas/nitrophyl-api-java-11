package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "BOMBEO")
public class Bombeo extends AuditableEntity<Long> {

	private static final long serialVersionUID = -2376992889027069273L;

	@Id
	@GeneratedValue(generator = "BOMBEO_SEQ")
	@GenericGenerator(
	        name = "BOMBEO_SEQ",
	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	        parameters = {
	            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "BOMBEO_SEQ"),
	            @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
	            @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
	        }
	    )
	@Column(name = "ID_BOMBEO", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_PROCESO")
	private Proceso proceso;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO")
	private TipoBombeo tipo;

	@Column(name = "CANTIDAD")
	private Integer cantidad;

	@Column(name = "PRESION")
	private Double presion;

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPresion() {
		return presion;
	}

	public void setPresion(Double presion) {
		this.presion = presion;
	}

	public TipoBombeo getTipo() {
		return tipo;
	}

	public void setTipo(TipoBombeo tipo) {
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Bombeo clonar(String username, Date fechaHora, Proceso proceso) {
		Bombeo clonada = new Bombeo();
		clonada.resetearCreacion(username, fechaHora);
		clonada.setCantidad(cantidad);
		clonada.setPresion(presion);
		clonada.setProceso(proceso);
		clonada.setTipo(tipo);
		return clonada;
	}

}
