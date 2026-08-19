/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

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
public class PiezaStockDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private String codigo;

	private String denominacion;

	private String tipo;

	private String material;

	private String formula;
	
	private Integer stockFisico;

	private Integer stockReservado;
	
	@Override
	public ProjectionList getProjections() {
		return Projections.projectionList().add(Projections.property("id"), "id")
				.add(Projections.property("pieza.denominacion"), "denominacion")
				.add(Projections.property("pieza.codigo"), "codigo")
				.add(Projections.property("pieza.tipo.nombre"), "tipo")
				.add(Projections.property("pieza.detalleFormula.formula.nombre"), "formula")
				.add(Projections.property("pieza.detalleFormula.formula.material.nombre"), "material")
				.add(Projections.property("stockFisico"), "stockFisico")
				.add(Projections.property("fechaActualizacion"), "fechaActualizacion")
				.add(Projections.property("stockReservado"), "stockReservado");
	}
	
}
