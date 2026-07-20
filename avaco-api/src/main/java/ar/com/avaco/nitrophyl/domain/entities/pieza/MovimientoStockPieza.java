package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.time.LocalDateTime;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
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
public class MovimientoStockPieza extends AuditableEntity<Long> {

	private Long id;
	private Pieza pieza;

	private TipoMovimientoStock tipo;

	private Integer cantidad;

	private LocalDateTime fecha;

	private String observacion;

}
