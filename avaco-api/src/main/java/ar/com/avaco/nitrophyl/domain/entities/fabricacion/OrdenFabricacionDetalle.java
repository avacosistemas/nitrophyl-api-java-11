package ar.com.avaco.nitrophyl.domain.entities.fabricacion;

import java.util.Date;

import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;

public class OrdenFabricacionDetalle {

	private Long id;
	
	private Long cantidad;
	
	private Pieza pieza;
	
	private Date fechaEntregaEstimada;
	
	
	// Se usan para armar el nro de la OF nroSecuencia/nroAnio
	
	private Long nroSecuencia;
	
	private Long nroAnio;
	
}
