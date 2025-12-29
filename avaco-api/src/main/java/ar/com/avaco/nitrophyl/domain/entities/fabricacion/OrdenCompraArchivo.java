package ar.com.avaco.nitrophyl.domain.entities.fabricacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "ORDEN_COMPRA_ARCHIVO")
public class OrdenCompraArchivo extends AuditableEntity<Long> {

	private static final long serialVersionUID = 7484455990242911192L;

	@Id
	@Column(name = "ID_OC_ARCHIVO", unique = true, nullable = false)
	private Long id;

	@OneToOne
    @MapsId
    @JoinColumn(name = "ID_OC_ARCHIVO")
    private OrdenCompra ordenDeCompra;
	
	@Column(name = "ARCHIVO", nullable = true)
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] archivo;

	@Column(name = "NOMBRE")
	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
