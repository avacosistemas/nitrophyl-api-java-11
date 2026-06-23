package ar.com.avaco.nitrophyl.ws.dto.ordenfabricacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ControlCalidadDTO {

	private String tipo;
	private String valor;	

}
