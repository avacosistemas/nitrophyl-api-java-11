package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class ConfiguracionPruebaCondicionDTO extends DTOEntity<Long> {

	private Long id;
	private String nombre;
	private Double valor;

	public ConfiguracionPruebaCondicionDTO() {
	}

	public ConfiguracionPruebaCondicionDTO(String nombre, Double valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
