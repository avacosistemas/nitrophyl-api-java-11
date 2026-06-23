package ar.com.avaco.nitrophyl.domain.entities.fabricacion;

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
@Table(name = "MAQUINA_FABRICA")
public class MaquinaFabrica extends AuditableEntity<Long> {

	private static final long serialVersionUID = -7495791419883480910L;

	@Id
	@GeneratedValue(generator = "MAQUINA_FABRICA_SEQ")
	@GenericGenerator(name = "MAQUINA_FABRICA_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "MAQUINA_FABRICA_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_MAQUINA_FABRICA", unique = true, nullable = false)
	private Long id;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO", nullable = false)
	private TipoMaquinaFabrica tipo;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_SECTOR")
	private SectorFabrica sector;

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

	public TipoMaquinaFabrica getTipo() {
		return tipo;
	}

	public void setTipo(TipoMaquinaFabrica tipo) {
		this.tipo = tipo;
	}

	public SectorFabrica getSector() {
		return sector;
	}

	public void setSector(SectorFabrica sector) {
		this.sector = sector;
	}

	public static MaquinaFabrica ofId(Long idMaquina) {
		MaquinaFabrica mf = new MaquinaFabrica();
		mf.setId(idMaquina);
		return mf;
	}

}
