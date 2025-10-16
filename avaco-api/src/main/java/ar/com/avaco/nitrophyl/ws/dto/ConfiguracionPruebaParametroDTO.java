package ar.com.avaco.nitrophyl.ws.dto;

public class ConfiguracionPruebaParametroDTO {

	private Long id;
	private MaquinaPruebaDTO maquinaPrueba;
	private Double minimo;
	private Double maximo;
	private String norma;

	public Integer getOrden() {
		return maquinaPrueba.getPosicion();
	}
	
	public String getNorma() {
		return norma;
	}

	public void setNorma(String norma) {
		this.norma = norma;
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

	public MaquinaPruebaDTO getMaquinaPrueba() {
		return maquinaPrueba;
	}

	public void setMaquinaPrueba(MaquinaPruebaDTO maquinaPrueba) {
		this.maquinaPrueba = maquinaPrueba;
	}

}
