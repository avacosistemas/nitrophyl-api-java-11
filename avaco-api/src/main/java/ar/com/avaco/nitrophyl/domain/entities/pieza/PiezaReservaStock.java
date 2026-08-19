package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
@Entity
@Table(name = "PIEZA_RESERVA_STOCK")
public class PiezaReservaStock extends AuditableEntity<Long> {

	private static final long serialVersionUID = 5272753625649794342L;

	@Id
	@GeneratedValue(generator = "PIEZA_RESERVA_STOCK_SEQ")
	@GenericGenerator(name = "PIEZA_RESERVA_STOCK_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "PIEZA_RESERVA_STOCK_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_PIEZA_RESERVA_STOCK", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_ORDEN_COMPRA_DETALLE")
	private OrdenCompraDetalle ordenCompraDetalle;

	@Column(name = "CANTIDAD", nullable = false)
	private Integer cantidadReservada;

	@Column(name = "ESTADO")
	@Enumerated(EnumType.STRING)
	private EstadoReserva estado;

	@Column(name = "FECHA", nullable = false)
	private LocalDateTime fechaReserva;
}
