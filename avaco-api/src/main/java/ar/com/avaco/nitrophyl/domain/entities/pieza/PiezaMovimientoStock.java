package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.time.LocalDate;

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
@Table(name = "PIEZA_MOVIMIENTO_STOCK")
public class PiezaMovimientoStock extends AuditableEntity<Long> {

	private static final long serialVersionUID = 7323370582622832488L;

	@Id
	@GeneratedValue(generator = "PIEZA_MOV_STOCK_SEQ")
	@GenericGenerator(name = "PIEZA_MOV_STOCK_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "PIEZA_MOV_STOCK_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_PIEZA_MOVIMIENTO_STOCK", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_PIEZA", nullable = false)
	private Pieza pieza;

	@Column(name = "CANTIDAD", nullable = false)
	private Integer cantidad;

	@Column(name = "FECHA", nullable = false)
	private LocalDate fecha;

	@Column(name = "OBSERVACION")
	private String observacion;

	@Enumerated(EnumType.STRING)
	@Column(name = "ORIGEN")
	private OrigenMovimientoStock origen;

}
