package ar.com.avaco.nitrophyl.domain.entities.fabricacion;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.administracion.EmpresaTransporte;
import ar.com.avaco.nitrophyl.domain.entities.administracion.TipoDespacho;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.cliente.ClienteDomicilio;

@Entity
@Table(name = "ORDEN_COMPRA")
public class OrdenCompra extends AuditableEntity<Long> {

	private static final long serialVersionUID = -1168818575560524811L;

	@Id
	@GeneratedValue(generator = "ORDEN_COMPRA_SEQ")
	@GenericGenerator(name = "ORDEN_COMPRA_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "ORDEN_COMPRA_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_ORDEN_COMPRA", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE", nullable = false)
	private Cliente cliente;

	@Column(name = "ESTADO")
	@Enumerated(EnumType.STRING)
	private OrdenCompraEstado estado;

	@Column(name = "COMPROBANTE")
	private String comprobante;

	@Column(name = "FECHA")
	private LocalDate fecha;

	@OneToOne(mappedBy = "ordenDeCompra", cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
	private OrdenCompraArchivo archivo;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "ordenCompra", orphanRemoval = true)
	private Set<OrdenCompraDetalle> detalle = new HashSet<OrdenCompraDetalle>();

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_DESPACHO")
	private TipoDespacho tipoDespacho;

	@ManyToOne(optional = true)
	@JoinColumn(name = "ID_EMPRESA_TRANSPORTE")
	private EmpresaTransporte empresaTransporte;

	@Column(name = "MEDIOS_ENVIO")
	private String mediosEnvio;

	@ManyToOne(optional = true)
	@JoinColumn(name = "ID_DOMICILIO_ENVIO")
	private ClienteDomicilio domicilioEnvio;

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	public TipoDespacho getTipoDespacho() {
		return tipoDespacho;
	}

	public void setTipoDespacho(TipoDespacho tipoDespacho) {
		this.tipoDespacho = tipoDespacho;
	}

	public EmpresaTransporte getEmpresaTransporte() {
		return empresaTransporte;
	}

	public void setEmpresaTransporte(EmpresaTransporte empresaTransporte) {
		this.empresaTransporte = empresaTransporte;
	}

	public String getMediosEnvio() {
		return mediosEnvio;
	}

	public void setMediosEnvio(String mediosEnvio) {
		this.mediosEnvio = mediosEnvio;
	}

	public ClienteDomicilio getDomicilioEnvio() {
		return domicilioEnvio;
	}

	public void setDomicilioEnvio(ClienteDomicilio domicilioEnvio) {
		this.domicilioEnvio = domicilioEnvio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
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
		if (archivo != null) {
			archivo.setOrdenDeCompra(this);
		}
	}

	public Set<OrdenCompraDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(Set<OrdenCompraDetalle> detalle) {
		this.detalle = detalle;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
