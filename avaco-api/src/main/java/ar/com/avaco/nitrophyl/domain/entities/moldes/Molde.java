package ar.com.avaco.nitrophyl.domain.entities.moldes;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaTipo;
import ar.com.avaco.nitrophyl.ws.dto.MoldeListadoDTO;

@SqlResultSetMapping(name = "MoldeListadoDTOMapper", classes = {
		@ConstructorResult(targetClass = MoldeListadoDTO.class, columns = {
				@ColumnResult(name = "id", type = Integer.class), 
				@ColumnResult(name = "codigo", type = String.class),
				@ColumnResult(name = "estado", type = String.class),
				@ColumnResult(name = "nombre", type = String.class),
				@ColumnResult(name = "ubicacion", type = String.class),
				@ColumnResult(name = "alto", type = Integer.class), 
				@ColumnResult(name = "ancho", type = Integer.class),
				@ColumnResult(name = "diametro", type = Integer.class),
				@ColumnResult(name = "profundidad", type = Integer.class),
				@ColumnResult(name = "piezas", type = String.class),
				@ColumnResult(name = "ultimoRegistro", type = String.class),
				@ColumnResult(name = "totalRows", type = Integer.class) }) })

@Entity
@Table(name = "MOLDES")
@Inheritance(strategy = InheritanceType.JOINED)
public class Molde extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "MOLDES_SEQ")
	@GenericGenerator(name = "MOLDES_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "MOLDES_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_MOLDE", unique = true, nullable = false)
	private Long id;

	@Column(name = "CODIGO", unique = true, nullable = false)
	private String codigo;

	@Column(name = "ESTADO", unique = false, nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoMolde estado;

	@Column(name = "NOMBRE", unique = false, nullable = false)
	private String nombre;

	@Column(name = "UBICACION")
	private String ubicacion;

	@Column(name = "BOCAS")
	private Integer bocas;

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	@Column(name = "PROPIO")
	private boolean propio;

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "ID_CLIENTE_DUENIO", nullable = true)
	private Cliente duenio;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "MOLDE_CLIENTE", joinColumns = @JoinColumn(name = "ID_MOLDE", referencedColumnName = "ID_MOLDE"), inverseJoinColumns = @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE"))
	@Fetch(FetchMode.SELECT)
	private Set<Cliente> clientes = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "MOLDE_TIPO_PIEZA", joinColumns = @JoinColumn(name = "ID_MOLDE", referencedColumnName = "ID_MOLDE"), inverseJoinColumns = @JoinColumn(name = "ID_PIEZA_TIPO", referencedColumnName = "ID_PIEZA_TIPO"))
	@Fetch(FetchMode.SELECT)
	private Set<PiezaTipo> tiposPieza = new HashSet<>();

	public Set<PiezaTipo> getTiposPieza() {
		return tiposPieza;
	}

	public void setTiposPieza(Set<PiezaTipo> tiposPieza) {
		this.tiposPieza = tiposPieza;
	}

	public Molde() {
		super();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public EstadoMolde getEstado() {
		return estado;
	}

	public void setEstado(EstadoMolde estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Integer getBocas() {
		return bocas;
	}

	public void setBocas(Integer bocas) {
		this.bocas = bocas;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}

	public boolean isPropio() {
		return propio;
	}

	public void setPropio(boolean propio) {
		this.propio = propio;
	}

	public Cliente getDuenio() {
		return duenio;
	}

	public void setDuenio(Cliente duenio) {
		this.duenio = duenio;
	}

}
