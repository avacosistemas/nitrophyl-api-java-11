package ar.com.avaco.nitrophyl.domain.entities.formula;

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

import ar.com.avaco.nitrophyl.domain.entities.maquina.MaquinaPrueba;

@Entity
@Table(name = "CONF_PRUEBA_PARAM")
@SequenceGenerator(name = "CONF_PRUEBA_PARAM_SEQ", sequenceName = "CONF_PRUEBA_PARAM_SEQ", allocationSize = 1)
public class ConfiguracionPruebaParametro extends ar.com.avaco.arc.core.domain.Entity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2296428532611007942L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONF_PRUEBA_PARAM_SEQ")
	@Column(name = "ID_CONF_PRUEBA_PARAM", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "ID_CONF_PRUEBA")
	private ConfiguracionPrueba configuracionPrueba;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "ID_MAQUINA_PRUEBA")
	private MaquinaPrueba maquinaPrueba;

	@Column(name = "VALOR_MINIMO")
	private Double minimo;

	@Column(name = "VALOR_MAXIMO")
	private Double maximo;

	@Column(name = "NORMA")
	private String norma;

	public ConfiguracionPruebaParametro() {
	}

	public ConfiguracionPruebaParametro(ConfiguracionPrueba configuracionPrueba, MaquinaPrueba maquinaPrueba,
			Double minimo, Double maximo, String norma, Long id) {
		super();
		this.id = id;
		this.minimo = minimo;
		this.maximo = maximo;
		this.configuracionPrueba = configuracionPrueba;
		this.norma = norma;
		this.maquinaPrueba = maquinaPrueba;
	}

	public ConfiguracionPruebaParametro(Long idConfiguracionPruebaParametro) {
		this.id = idConfiguracionPruebaParametro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ConfiguracionPrueba getConfiguracionPrueba() {
		return configuracionPrueba;
	}

	public void setConfiguracionPrueba(ConfiguracionPrueba configuracionPrueba) {
		this.configuracionPrueba = configuracionPrueba;
	}

	public Double getMinimo() {
		return minimo;
	}

	public void setMinimo(Double minimo) {
		this.minimo = minimo;
	}

	public Double getMaximo() {
		return maximo;
	}

	public void setMaximo(Double maximo) {
		this.maximo = maximo;
	}

	public String getNorma() {
		return norma;
	}

	public void setNorma(String norma) {
		this.norma = norma;
	}

	public MaquinaPrueba getMaquinaPrueba() {
		return maquinaPrueba;
	}

	public void setMaquinaPrueba(MaquinaPrueba maquinaPrueba) {
		this.maquinaPrueba = maquinaPrueba;
	}

}
