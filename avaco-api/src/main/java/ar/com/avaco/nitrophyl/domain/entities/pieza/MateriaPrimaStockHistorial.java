package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name = "MATERIA_PRIMA_STOCK_HISTORIAL")
public class MateriaPrimaStockHistorial extends AuditableEntity<Long> {

	private static final long serialVersionUID = 9150027922915407679L;

	@Id
	@GeneratedValue(generator = "MATERIA_PRIMA_STOCK_HISTORIAL_SEQ")
	@GenericGenerator(name = "MATERIA_PRIMA_STOCK_HISTORIAL_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "MATERIA_PRIMA_STOCK_HISTORIAL_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_MATERIA_PRIMA_STOCK_HISTORIAL", unique = true, nullable = false)
	private Long id;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "ID_MATERIA_PRIMA", insertable = true, updatable = false)
	private MateriaPrima materiaPrima;

	@Column(name = "FECHA")
	private Date fecha;

	@Column(name = "CANTIDAD")
	private Double cantidad;

	@Column(name = "UNIDAD_MEDIDA")
	@Enumerated(EnumType.STRING)
	private UnidadMedidaMateriaPrima unidadMedida;

	@Column(name = "TIPO_MOVIMIENTO")
	@Enumerated(EnumType.STRING)
	private TipoMovimientoStock tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MateriaPrima getMateriaPrima() {
		return materiaPrima;
	}

	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public TipoMovimientoStock getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimientoStock tipo) {
		this.tipo = tipo;
	}

	public UnidadMedidaMateriaPrima getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedidaMateriaPrima unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

}
