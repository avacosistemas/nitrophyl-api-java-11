package ar.com.avaco.nitrophyl.domain.entities.administracion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.cliente.ClienteDomicilio;

@Entity
@Table(name = "EMPRESA_TRANSPORTE")
public class EmpresaTransporte extends AuditableEntity<Long> {

	private static final long serialVersionUID = 3868279756784146751L;

	@Id
	@GeneratedValue(generator = "EMPRESA_TRANSPORTE_SEQ")
	@GenericGenerator(name = "EMPRESA_TRANSPORTE_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "EMPRESA_TRANSPORTE_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_EMPRESA_TRANSPORTE", unique = true, nullable = false)
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "DIRECCION")
	private String direccion;

	@Column(name = "TELEFONO")
	private String telefono;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "HORARIO_ATENCION")
	private String horarioAtencion;

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	@Column(name = "MEDIOS_ENVIO")
	private String mediosEnvio;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHorarioAtencion() {
		return horarioAtencion;
	}

	public void setHorarioAtencion(String horarioAtencion) {
		this.horarioAtencion = horarioAtencion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getMediosEnvio() {
		return mediosEnvio;
	}

	public void setMediosEnvio(String mediosEnvio) {
		this.mediosEnvio = mediosEnvio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static EmpresaTransporte ofId(Long idEmpresaTransporte) {
		EmpresaTransporte et = new EmpresaTransporte();
		et.setId(idEmpresaTransporte);
		return et;
	}

}
