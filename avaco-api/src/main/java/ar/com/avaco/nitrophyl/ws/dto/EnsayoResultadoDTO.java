package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class EnsayoResultadoDTO extends DTOEntity<Long> {

	private Long id;

	private Long idEnsayo;

	private Long idConfiguracionPruebaParametro;

	private String nombre;

	private Double minimo;

	private Double maximo;

	private Double resultado;

	private Double redondeo;

	private String norma;

	public String getNorma() {
		return norma;
	}

	public void setNorma(String norma) {
		this.norma = norma;
	}

	public Double getResultado() {
		return resultado;
	}

	public void setResultado(Double resultado) {
		this.resultado = resultado;
	}

	public Double getRedondeo() {
		return redondeo;
	}

	public void setRedondeo(Double redondeo) {
		this.redondeo = redondeo;
	}

	public Long getIdEnsayo() {
		return idEnsayo;
	}

	public void setIdEnsayo(Long idEnsayo) {
		this.idEnsayo = idEnsayo;
	}

	public Long getIdConfiguracionPruebaParametro() {
		return idConfiguracionPruebaParametro;
	}

	public void setIdConfiguracionPruebaParametro(Long idConfiguracionPruebaParametro) {
		this.idConfiguracionPruebaParametro = idConfiguracionPruebaParametro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
