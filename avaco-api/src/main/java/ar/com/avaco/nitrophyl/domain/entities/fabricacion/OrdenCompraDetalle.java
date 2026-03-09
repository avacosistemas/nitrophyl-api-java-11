package ar.com.avaco.nitrophyl.domain.entities.fabricacion;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;

@Entity
@Table(name = "ORDEN_COMPRA_DETALLE")
public class OrdenCompraDetalle extends AuditableEntity<Long> {

	@Id
	@GeneratedValue(generator = "ORDEN_COMPRA_DETALLE_SEQ")
	@GenericGenerator(name = "ORDEN_COMPRA_DETALLE_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "ORDEN_COMPRA_DETALLE_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_ORDEN_COMPRA_DETALLE", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_ORDEN_COMPRA", nullable = false)
	private OrdenCompra ordenCompra;

	@Column(name = "ESTADO")
	private OrdenCompraEstado estado;

	@Column(name = "COMPROBANTE")
	private String comprobante;

	@Column(name = "FECHA")
	private Date fecha;

	@OneToOne(mappedBy = "ordenDeCompra", cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
	private OrdenCompraArchivo archivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public OrdenCompraEstado getEstado() {
		return estado;
	}

	public void setEstado(OrdenCompraEstado estado) {
		this.estado = estado;
	}

	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	public OrdenCompraArchivo getArchivo() {
		return archivo;
	}

	public void setArchivo(OrdenCompraArchivo archivo) {
		this.archivo = archivo;
	}

}
