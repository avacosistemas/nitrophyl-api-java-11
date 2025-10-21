package ar.com.avaco.nitrophyl.domain.entities.cliente;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author el betazo
 *
 */
@Entity
@Table(name = "CLIENTE")
@Inheritance(strategy = InheritanceType.JOINED)
public class Cliente extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = 2843597480559139677L;

	@Id
	@GeneratedValue(generator = "CLIENTE_SEQ")
	@GenericGenerator(name = "CLIENTE_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "CLIENTE_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_CLIENTE")
	private Long id;

	/**
	 * La razon social.
	 */
	@Column(name = "RAZON_SOCIAL", nullable = false)
	private String razonSocial;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	/**
	 * Contacto con el cliente.
	 * 
	 * @NotNull
	 * @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	 * @PrimaryKeyJoinColumn private Contacto contacto;
	 */

	@OneToMany(targetEntity = Contacto.class, mappedBy = "cliente")
	private Set<Contacto> contactos = new HashSet<>();

	/**
	 * El domicilio completo.
	 */
	@Column(name = "DOMICILIO")
	private String domicilio;

	@Column(name = "TEL_FIJO")
	private String telefono;

	/**
	 * El codigo postal.
	 */
	@Column(name = "CODIGO_POSTAL", length = 10)
	private String codigoPostal;

	/**
	 * Localidad.
	 */
	@Column(name = "LOCALIDAD")
	private String localidad;

	/**
	 * Provincia.
	 */
	@Enumerated
	@Column(name = "PROVINCIA")
	private Provincia provincia;

	/**
	 * Email del cliente.
	 */
	@Column(name = "EMAIL")
	private String email;

	/**
	 * Sitio web.
	 */
	@Column(name = "WEB_SITE")
	private String webSite;

	/**
	 * CUIT
	 */
	@Column(name = "CUIT")
	private String cuit;

	@Column(name = "OBS_COBRANZAS")
	private String observacionesCobranzas;

	@Column(name = "OBS_ENTREGA")
	private String observacionesEntrega;

	@Column(name = "OBS_FACTURACION")
	private String observacionesFacturacion;

	@Column(name = "ACTIVO")
	private Boolean activo;

	@Column(name = "EMPRESA")
	@Enumerated(EnumType.STRING)
	private EmpresaCliente empresa;

	public Cliente() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public Set<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(Set<Contacto> contactos) {
		this.contactos = contactos;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getCUIT() {
		return cuit;
	}

	public void setCUIT(String cuit) {
		this.cuit = cuit;
	}

	public String getObservacionesCobranzas() {
		return observacionesCobranzas;
	}

	public void setObservacionesCobranzas(String observacionesCobranzas) {
		this.observacionesCobranzas = observacionesCobranzas;
	}

	public String getObservacionesEntrega() {
		return observacionesEntrega;
	}

	public void setObservacionesEntrega(String observacionesEntrega) {
		this.observacionesEntrega = observacionesEntrega;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObservacionesFacturacion() {
		return observacionesFacturacion;
	}

	public void setObservacionesFacturacion(String observacionesFacturacion) {
		this.observacionesFacturacion = observacionesFacturacion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public EmpresaCliente getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaCliente empresa) {
		this.empresa = empresa;
	}

	public static Cliente ofId(Long idCliente) {
		Cliente cliente = new Cliente();
		cliente.setId(idCliente);
		return cliente;
	}

}
