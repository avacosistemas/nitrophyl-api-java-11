package ar.com.avaco.nitrophyl.ws.dto.ordenfabricacion;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.avaco.nitrophyl.ws.dto.OrdenTrabajoEntregaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemOrdenTrabajoDTO {

	@JsonProperty("id_item")
	private Long idItem;

	private String titulo;

	@JsonProperty("cantidad_total")
	private Integer cantidadTotal;

	private String material;

	private String formula;

	private String batch;

	private String fabrico;

	private String hp;

	@JsonProperty("plano_rev")
	private String planoRev;

	@JsonProperty("observaciones_item")
	private String observacion;
	
	private String descuento;

	private String observacionDescuento;
	
	@JsonProperty("precio_descuento")
	private String precioDescuento;
	
	private String matriz;

	private String identificacion;

	private String ubicacion;

	private String pc;
	
	private String identficacion;

	private Double cotizacion;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date fechaCotizacion;
	
	@JsonProperty("control_calidad")
	private List<ControlCalidadDTO> controlCalidad;
	
	private List<OrdenTrabajoEntregaDTO> entregas;

}
