package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "INSUMO_MATERIA_PRIMA")
public class InsumoMateriaPrima extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = 4711223900146608699L;

	@Id
	@GeneratedValue(generator = "INSUMO_MATERIA_PRIMA_SEQ")
    @GenericGenerator(
            name = "INSUMO_MATERIA_PRIMA_SEQ",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "INSUMO_MATERIA_PRIMA_SEQ"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
        )
	@Column(name = "ID_INSUMO_MATERIA_PRIMA", unique = true, nullable = false)
	private Long id;

	@Column(name = "CANTIDAD")
	private Double cantidad;

	@ManyToOne
	@JoinColumn(name = "ID_MATERIA_PRIMA")
	private MateriaPrima materiaPrima;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public MateriaPrima getMateriaPrima() {
		return materiaPrima;
	}

	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

}
