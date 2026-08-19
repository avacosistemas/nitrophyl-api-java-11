package ar.com.avaco.nitrophyl.ws.dto.ordenfabricacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdenTrabajoResumenDTO {

	private String pieza;
	private String formula;
	private String hp;
	private String numeroOt;
	private String ordenCompra;
	private String fechaEntrega;
	private String sector;
	private String maquina;
	private Integer cantidadTotal;
	private Integer cantidadFabricada;

}
