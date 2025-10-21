package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "INSUMO")
public class Insumo extends AuditableEntity<Long> {

    private static final long serialVersionUID = -4766509268712069513L;

    @Id
    @GeneratedValue(generator = "INSUMO_SEQ")
    @GenericGenerator(
        name = "INSUMO_SEQ",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "INSUMO_SEQ"),
            @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
            @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        }
    )
    @Column(name = "ID_INSUMO", unique = true, nullable = false)
    private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_TIPO")
	private TipoInsumo tipo;

	/**
	 * La cantidad en stock.
	 */
	@Column(name = "CANTIDAD_STOCK")
	private Double cantidadStock;

	/**
	 * La unidad de medida para almacenar stock: M2, Metro lineal, unidades.
	 */
	@Column(name = "UNIDAD_MEDIDA_STOCK")
	@Enumerated(EnumType.STRING)
	private UnidadMedida unidadMedidaStock;

	/**
	 * El insumo puede fabricarse usando materia prima. Por ejemplo algunas partes
	 * de las piezas se fabrica con resina. Puede tener como no. Depende del tipo de
	 * insumo. Se usa para saber cuanta materia prima se necesita para fabricar el
	 * insumo.
	 */
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "ID_INSUMO_MATERIA_PRIMA")
	private InsumoMateriaPrima insumoMateriaPrima;

	public UnidadMedida getUnidadMedidaStock() {
		return unidadMedidaStock;
	}

	public void setUnidadMedidaStock(UnidadMedida unidadMedidaStock) {
		this.unidadMedidaStock = unidadMedidaStock;
	}

	public InsumoMateriaPrima getInsumoMateriaPrima() {
		return insumoMateriaPrima;
	}

	public void setInsumoMateriaPrima(InsumoMateriaPrima insumoMateriaPrima) {
		this.insumoMateriaPrima = insumoMateriaPrima;
	}

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

	public TipoInsumo getTipo() {
		return tipo;
	}

	public void setTipo(TipoInsumo tipo) {
		this.tipo = tipo;
	}

	public Double getCantidadStock() {
		return cantidadStock;
	}

	public void setCantidadStock(Double cantidadStock) {
		this.cantidadStock = cantidadStock;
	}

}
