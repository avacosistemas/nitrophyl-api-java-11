package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "INSUMO_TRATADO")
@SequenceGenerator(name = "INSUMO_TRATADO_SEQ", sequenceName = "INSUMO_TRATADO_SEQ", allocationSize = 1)
public class InsumoTratado extends AuditableEntity<Long> {

	private static final long serialVersionUID = 797497464400538571L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INSUMO_TRATADO_SEQ")
	@Column(name = "ID_INSUMO_TRATADO", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_PIEZA")
	private Pieza pieza;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "INSUMO_TRATADO_TRATAMIENTO", joinColumns = @JoinColumn(name = "ID_INSUMO_TRATADO", referencedColumnName = "ID_INSUMO_TRATADO"), inverseJoinColumns = @JoinColumn(name = "ID_TRATAMIENTO", referencedColumnName = "ID_TRATAMIENTO"))
	@Fetch(FetchMode.SELECT)
	private Set<Tratamiento> tratamientos = new HashSet<>();

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "ID_INSUMO")
	private Insumo insumo;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "INSUMO_TRATADO_ADHESIVO", joinColumns = @JoinColumn(name = "ID_INSUMO_TRATADO", referencedColumnName = "ID_INSUMO_TRATADO"), inverseJoinColumns = @JoinColumn(name = "ID_ADHESIVO", referencedColumnName = "ID_ADHESIVO"))
	@Fetch(FetchMode.SELECT)
	private Set<Adhesivo> adhesivos = new HashSet<>();

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	@Column(name = "MEDIDA_VALOR")
	private String medidaValor;

	@Column(name = "MEDIDA_OBSERVACIONES")
	private String medidaObservaciones;

	public InsumoTratado clonar(String username, Date fechaHora, Pieza pieza) {
		InsumoTratado clonada = new InsumoTratado();
		clonada.resetearCreacion(username, fechaHora);
		clonada.getAdhesivos().addAll(this.adhesivos);
		clonada.setInsumo(this.insumo);
		clonada.setMedidaObservaciones(this.medidaObservaciones);
		clonada.setMedidaValor(this.medidaValor);
		clonada.setObservaciones(this.observaciones);
		clonada.setPieza(pieza);
		clonada.getTratamientos().addAll(this.tratamientos);
		return clonada;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	public Set<Tratamiento> getTratamientos() {
		return tratamientos;
	}

	public void setTratamientos(Set<Tratamiento> tratamientos) {
		this.tratamientos = tratamientos;
	}

	public Set<Adhesivo> getAdhesivos() {
		return adhesivos;
	}

	public void setAdhesivos(Set<Adhesivo> adhesivos) {
		this.adhesivos = adhesivos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getMedidaValor() {
		return medidaValor;
	}

	public void setMedidaValor(String medidaValor) {
		this.medidaValor = medidaValor;
	}

	public String getMedidaObservaciones() {
		return medidaObservaciones;
	}

	public void setMedidaObservaciones(String medidaObservaciones) {
		this.medidaObservaciones = medidaObservaciones;
	}

}
