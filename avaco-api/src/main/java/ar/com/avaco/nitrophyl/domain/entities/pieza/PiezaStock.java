package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "PIEZA_STOCK")
public class PiezaStock extends AuditableEntity<Long> {

	private static final long serialVersionUID = 6425108848412976448L;

	@Id
	@Column(name = "ID_PIEZA", unique = true, nullable = false)
	private Long id;

	@OneToOne
	@MapsId
	@JoinColumn(name = "ID_PIEZA")
	private Pieza pieza;

	@Column(name = "STOCK_FISICO")
	private Integer stockFisico;

	@Column(name = "STOCK_RESERVADO")
	private Integer stockReservado;

	public void incrementarStockFisico(Integer cantidad) {
		this.stockFisico += cantidad;
	}

}
