package ar.com.avaco.nitrophyl.ws.dto;

public class ControlPiezaDTO {

	private Long id;

	private String control;

	private Boolean aprobado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public Boolean getAprobado() {
		return aprobado;
	}

	public void setAprobado(Boolean aprobado) {
		this.aprobado = aprobado;
	}

}
