package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
@Table(name = "TIPO_INSUMO")
public class TipoInsumo extends AuditableEntity<Long> {

	private static final long serialVersionUID = 303448339978364112L;

	@Id
	@GeneratedValue(generator = "TIPO_INSUMO_SEQ")
	@GenericGenerator(name = "TIPO_INSUMO_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "TIPO_INSUMO_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_TIPO_INSUMO", unique = true, nullable = false)
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_PADRE")
	private TipoInsumo padre;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_STOCK")
	private TipoStock tipoStock;

	public TipoStock getTipoStock() {
		return tipoStock;
	}

	public void setTipoStock(TipoStock tipoStock) {
		this.tipoStock = tipoStock;
	}

	public TipoInsumo getPadre() {
		return padre;
	}

	public void setPadre(TipoInsumo padre) {
		this.padre = padre;
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

	public static TipoInsumo ofId(Long id) {
		TipoInsumo ti = new TipoInsumo();
		ti.setId(id);
		return ti;
	}

	@Transient
	public String getNombreCompleto() {
		List<String> nombres = new ArrayList<>();
		TipoInsumo actual = this;

		while (actual != null) {
			nombres.add(actual.getNombre());
			actual = actual.getPadre();
		}

		// Invertir la lista para tener la ruta desde la raz hasta la hoja
		Collections.reverse(nombres);

		return String.join(" > ", nombres);
	}

}
