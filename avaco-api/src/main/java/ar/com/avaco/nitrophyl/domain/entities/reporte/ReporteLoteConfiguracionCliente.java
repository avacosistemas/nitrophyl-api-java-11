package ar.com.avaco.nitrophyl.domain.entities.reporte;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.maquina.Maquina;
import ar.com.avaco.nitrophyl.domain.entities.maquina.MaquinaPrueba;

@Entity
@Table(name = "REPORTE_LOTE_CONF_CLIENTE")
public class ReporteLoteConfiguracionCliente extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = -8915059528176139461L;

	@Id
	@GeneratedValue(generator = "REPORTE_LOTE_CONF_CLIENTE_SEQ")
	@GenericGenerator(name = "REPORTE_LOTE_CONF_CLIENTE_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "REPORTE_LOTE_CONF_CLIENTE_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_REPORTE_LOTE_CONF_CLIENTE")
	private Long id;

	@JoinColumn(name = "ID_CLIENTE", nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	@JoinColumn(name = "ID_FORMULA", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Formula formula;

	@JoinColumn(name = "ID_MAQUINA", nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private Maquina maquina;

	@Column(name = "MOSTRAR_PARAMETROS")
	private Boolean mostrarParametros;

	@Column(name = "MOSTRAR_RESULTADOS")
	private Boolean mostrarResultados;

	@Column(name = "MOSTRAR_CONDICIONES")
	private Boolean mostrarCondiciones;

	@Column(name = "MOSTRAR_OBS_PARAM")
	private Boolean mostrarObservacionesParametro;

	@Column(name = "ENVIAR_GRAFICO")
	private Boolean enviarGrafico;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "REPORTE_LOTE_CONF_PRUEBA", joinColumns = @JoinColumn(name = "ID_REPORTE_LOTE_CONF_CLIENTE", referencedColumnName = "ID_REPORTE_LOTE_CONF_CLIENTE"), inverseJoinColumns = @JoinColumn(name = "ID_MAQUINA_PRUEBA", referencedColumnName = "ID_MAQUINA_PRUEBA"))
	@Fetch(FetchMode.SELECT)
	private Set<MaquinaPrueba> pruebas = new HashSet<>();

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

	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public Boolean getMostrarParametros() {
		return mostrarParametros;
	}

	public void setMostrarParametros(Boolean mostrarParametros) {
		this.mostrarParametros = mostrarParametros;
	}

	public Boolean getMostrarResultados() {
		return mostrarResultados;
	}

	public void setMostrarResultados(Boolean mostrarResultados) {
		this.mostrarResultados = mostrarResultados;
	}

	public Boolean getMostrarCondiciones() {
		return mostrarCondiciones;
	}

	public void setMostrarCondiciones(Boolean mostrarCondiciones) {
		this.mostrarCondiciones = mostrarCondiciones;
	}

	public Boolean getMostrarObservacionesParametro() {
		return mostrarObservacionesParametro;
	}

	public void setMostrarObservacionesParametro(Boolean mostrarObservacionesParametro) {
		this.mostrarObservacionesParametro = mostrarObservacionesParametro;
	}

	public Boolean getEnviarGrafico() {
		return enviarGrafico;
	}

	public void setEnviarGrafico(Boolean enviarGrafico) {
		this.enviarGrafico = enviarGrafico;
	}

	public Set<MaquinaPrueba> getPruebas() {
		return pruebas;
	}

	public void setPruebas(Set<MaquinaPrueba> pruebas) {
		this.pruebas = pruebas;
	}

}
