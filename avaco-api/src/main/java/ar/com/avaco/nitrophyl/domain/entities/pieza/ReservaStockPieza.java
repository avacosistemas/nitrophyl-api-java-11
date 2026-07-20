package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.time.LocalDateTime;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompraDetalle;
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
public class ReservaStockPieza extends AuditableEntity<Long> {

	private Long id;

	private Pieza pieza;

	private OrdenCompraDetalle pedido;

	private Integer cantidadReservada;

	private EstadoReserva estado;

	private LocalDateTime fechaReserva;
}
