package ar.com.avaco.nitrophyl.domain.entities.pieza;

public enum TipoStock {

	ROLLOM2DIAM(UnidadMedida.M2), 
	UNIDAD(UnidadMedida.ITEMS),
	GRAMOSUNIDAD(UnidadMedida.ITEMS),
	UNIDADXMETRO(UnidadMedida.M);

	private UnidadMedida um;
	
	TipoStock(UnidadMedida m) {
		um = m;
	}
	
	public UnidadMedida getUnidadMedida() {
		return um;
	}
	
}
