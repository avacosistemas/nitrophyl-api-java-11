package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "INSUMO")
@SequenceGenerator(name = "INSUMO_SEQ", sequenceName = "INSUMO_SEQ", allocationSize = 1)
public class Insumo extends AuditableEntity<Long> {

	private static final long serialVersionUID = -4766509268712069513L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INSUMO_SEQ")
	@Column(name = "ID_INSUMO", unique = true, nullable = false)
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_TIPO")
	private TipoInsumo tipo;

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

}
