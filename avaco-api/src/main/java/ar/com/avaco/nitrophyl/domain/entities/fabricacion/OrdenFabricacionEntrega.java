package ar.com.avaco.nitrophyl.domain.entities.fabricacion;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.arc.sec.domain.Usuario;
import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;

@Entity
@Table(name = "ORDEN_FABRICACION_ENTREGA")
public class OrdenFabricacionEntrega extends AuditableEntity<Long> {

	private static final long serialVersionUID = -3488533801030554441L;

	@Id
	@GeneratedValue(generator = "ORDEN_FAB_ENT_SEQ")
	@GenericGenerator(name = "ORDEN_FAB_ENT_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "ORDEN_FAB_ENT_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_ORDEN_FAB_ENT", unique = true, nullable = false)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_ORDEN_FABRICACION")
	private OrdenFabricacion ordenFabricacion;

	@Column(name = "CANTIDAD")
	private Integer cantidad;

	@Column(name = "FECHA")
	private LocalDate fecha;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_LOTE")
	private Lote lote;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_OPERARIO")
	private Usuario operario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrdenFabricacion getOrdenFabricacion() {
		return ordenFabricacion;
	}

	public void setOrdenFabricacion(OrdenFabricacion ordenFabricacion) {
		this.ordenFabricacion = ordenFabricacion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public Usuario getOperario() {
		return operario;
	}

	public void setOperario(Usuario operario) {
		this.operario = operario;
	}

}
