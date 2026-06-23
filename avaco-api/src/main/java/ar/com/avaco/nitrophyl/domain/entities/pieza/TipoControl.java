package ar.com.avaco.nitrophyl.domain.entities.pieza;

public enum TipoControl {

	INSUMO("Insumos"), MEDIDA("Medidas"), GENERAL("General"), DUREZA("Dureza");

	private String label;

	TipoControl(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
