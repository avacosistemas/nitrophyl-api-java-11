package ar.com.avaco.nitrophyl.ws.dto.ordenfabricacion;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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

	private String matriz;

	private String identificacion;

	private String ubicacion;

	private String pc;

	@JsonProperty("observaciones_item")
	private String observacionesItem;

	@JsonProperty("control_calidad")
	private List<ControlCalidadDTO> controlCalidad;

}
