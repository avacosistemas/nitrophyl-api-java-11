package ar.com.avaco.nitrophyl.domain.entities.pieza;

public enum TipoBombeo {

	
	AUTOMATICO("Automatico"),ESCALONADO("Escalonado"), SUAVE("Suave"), FONDO("A Fondo");

	private String label;

	TipoBombeo(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
