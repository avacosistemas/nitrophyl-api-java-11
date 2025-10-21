package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "MATERIA_PRIMA")
public class MateriaPrima extends AuditableEntity<Long> {

	private static final long serialVersionUID = -3605360617753728453L;
	@Id
	@GeneratedValue(generator = "MATERIA_PRIMA_SEQ")
	@GenericGenerator(name = "MATERIA_PRIMA_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "MATERIA_PRIMA_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_MATERIA_PRIMA", unique = true, nullable = false)
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "CANTIDAD_STOCK")
	private Double cantidadStock;

	@Column(name = "UNIDAD_MEDIDA_STOCK")
	@Enumerated(EnumType.STRING)
	private UnidadMedidaMateriaPrima unidadMedidaStock;

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

	public Double getCantidadStock() {
		return cantidadStock;
	}

	public void setCantidadStock(Double cantidadStock) {
		this.cantidadStock = cantidadStock;
	}

	public UnidadMedidaMateriaPrima getUnidadMedidaStock() {
		return unidadMedidaStock;
	}

	public void setUnidadMedidaStock(UnidadMedidaMateriaPrima unidadMedidaStock) {
		this.unidadMedidaStock = unidadMedidaStock;
	}

	public static MateriaPrima ofId(Long id) {
		MateriaPrima materiaPrima = new MateriaPrima();
		materiaPrima.setId(id);
		return materiaPrima;
	}

}
