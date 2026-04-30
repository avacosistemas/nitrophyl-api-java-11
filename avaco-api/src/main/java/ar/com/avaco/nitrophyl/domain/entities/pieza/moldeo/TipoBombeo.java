package ar.com.avaco.nitrophyl.domain.entities.pieza.moldeo;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoBombeo {
	
	AUTOMATICO("Automatico", ""),
	ESCALONADO("Escalonado", ""), 
	ESCALONADO_SUAVE("Escalonado Suave", ""), 
	SUAVE("Suave", ""), 
	TRANSFERENCIA_GRADUAL("Transferencia Gradual", ""), 
	FONDO("A Fondo", "");

	private String label;

	private String observaciones;
	
	TipoBombeo(String label, String observaciones) {
		this.label = label;
		this.observaciones = observaciones;
	}

	public String getLabel() {
		return label;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public String getClave() {
        return name();
    }
	
}
