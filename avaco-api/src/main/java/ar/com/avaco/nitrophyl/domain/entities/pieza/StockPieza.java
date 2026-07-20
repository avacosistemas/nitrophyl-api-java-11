package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
public class StockPieza extends AuditableEntity<Long> {

	@Id
	private Long id;

	@OneToOne
	@JoinColumn(name = "pieza_id")
	private Pieza pieza;

	private Deposito deposito;
	
	private Integer stockFisico;

	private Integer stockReservado;

}
