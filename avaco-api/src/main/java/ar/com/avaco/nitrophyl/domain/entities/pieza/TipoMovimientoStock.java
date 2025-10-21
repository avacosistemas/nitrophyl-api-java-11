package ar.com.avaco.nitrophyl.domain.entities.pieza;

public enum TipoMovimientoStock {

	INGRESO(1),
	CONSUMIDO(-1),
	DESPERDICIO(-1),
	RECUENTO_ANUAL(-1),
	STOCK_INICIAL(1);

	private Integer multiplicador;
	
	private TipoMovimientoStock(Integer i) {
		this.multiplicador = i;
	}
	
	public Integer getMultiplicador() {
		return multiplicador;
	}
	
}
