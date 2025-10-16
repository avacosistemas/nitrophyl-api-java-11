package ar.com.avaco.nitrophyl.ws.dto;

public class ReporteResultadoEnsayoDTO {

	private Long idMaquinaPrueba;

	private String redondeo;

	private String resultado;

	public Long getIdMaquinaPrueba() {
		return idMaquinaPrueba;
	}

	public void setIdMaquinaPrueba(Long idMaquinaPrueba) {
		this.idMaquinaPrueba = idMaquinaPrueba;
	}

	public String getRedondeo() {
		return redondeo;
	}

	public void setRedondeo(String redondeo) {
		this.redondeo = redondeo;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

}
