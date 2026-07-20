package ar.com.avaco.nitrophyl.domain.entities.fabricacion;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ORDEN_FABRICACION_ENTREGA_LOTE", joinColumns = @JoinColumn(name = "ID_ORDEN_FAB_ENT", referencedColumnName = "ID_ORDEN_FAB_ENT"), inverseJoinColumns = @JoinColumn(name = "ID_LOTE", referencedColumnName = "ID_LOTE"))
	private Set<Lote> lotes = new HashSet<Lote>();

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

	public Set<Lote> getLotes() {
		return lotes;
	}

	public void setLotes(Set<Lote> lotes) {
		this.lotes = lotes;
	}

	public Usuario getOperario() {
		return operario;
	}

	public void setOperario(Usuario operario) {
		this.operario = operario;
	}

}
