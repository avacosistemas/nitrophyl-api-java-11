package ar.com.avaco.nitrophyl.domain.entities.pieza;

public enum UnidadDureza {

	SHORE_A("Shore A"), SHORE_D("Shore D");

	private String label;

	UnidadDureza(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
