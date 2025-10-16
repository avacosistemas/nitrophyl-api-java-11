package ar.com.avaco.nitrophyl.domain.entities.cliente;

public enum TipoCliente {

	ALUMNO("Alumno"), 
	PROFESOR("Profesor"), 
	MIXTO("Mixto");

	private String label;

	TipoCliente(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
