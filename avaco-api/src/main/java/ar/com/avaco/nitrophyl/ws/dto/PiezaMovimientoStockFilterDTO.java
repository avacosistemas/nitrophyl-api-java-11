package ar.com.avaco.nitrophyl.ws.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import ar.com.avaco.nitrophyl.domain.entities.pieza.OrigenMovimientoStock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class PiezaMovimientoStockFilterDTO extends SortPageDTO {

	private Long idPieza;
	
	private OrigenMovimientoStock origen;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate fechaDesde;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate fechaHasta;
	
}
