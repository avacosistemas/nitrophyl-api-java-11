/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import ar.com.avaco.nitrophyl.domain.entities.pieza.OrigenMovimientoStock;
import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;
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
public class PiezaMovimientoStockDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private Long idPieza;

	private Integer cantidad;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate fecha;

	private String observacion;

	private OrigenMovimientoStock origen;

}
