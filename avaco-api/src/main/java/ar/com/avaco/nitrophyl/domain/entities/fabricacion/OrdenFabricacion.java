package ar.com.avaco.nitrophyl.domain.entities.fabricacion;

import java.util.Date;
import java.util.List;

import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;

public class OrdenFabricacion {
	
	private Long id;

	private Date fecha;
	
	// numero y anio combinados son unique.
	private Long numero;
	private Long anio;
	
	// Solo permitir seleccionar las que esten en estado pendiente o asignacion parcial
	// Debe filtrarse solo las del cliente seleccionado. Puede ser null si la orden es interna
	private OrdenCompra ordenCompra;

	// Si el cliente es null, entonces es interna propia de nitro
	private Cliente cliente;
	
	// Revisar los demás estados. llegar solo hasta pendiente asignacion
	private EstadoOrdenFabricacion estado;
	
	private List<OrdenFabricacionDetalle> detalle;
	
	
	
}
