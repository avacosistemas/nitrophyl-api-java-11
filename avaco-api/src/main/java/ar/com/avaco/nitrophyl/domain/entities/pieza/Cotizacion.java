package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.Date;

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
@Table(name = "COTIZACION")
public class Cotizacion extends AuditableEntity<Long> {

	private static final long serialVersionUID = -7515569203985933376L;

	@Id
	@GeneratedValue(generator = "COTIZACION_SEQ")
	@GenericGenerator(
	        name = "COTIZACION_SEQ",
	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	        parameters = {
	            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "COTIZACION_SEQ"),
	            @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
	            @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
	        }
	    )
	@Column(name = "ID_COTIZACION", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_PIEZA_CLIENTE")
	private PiezaCliente piezaCliente;

	@Column(name = "VALOR", nullable = false)
	private Double valor;

	@Column(name = "FECHA", nullable = false)
	private Date fecha;

	@Column(name = "OBSERVACIONES", nullable = false)
	private String observaciones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PiezaCliente getPiezaCliente() {
		return piezaCliente;
	}

	public void setPiezaCliente(PiezaCliente piezaCliente) {
		this.piezaCliente = piezaCliente;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
