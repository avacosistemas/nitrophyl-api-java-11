/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

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
public class PiezaMovimientoStockListadoDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private String piezaNombre;

	private Integer cantidad;

	private String fecha;

	private String observacion;

	private OrigenMovimientoStock origen;

	@Override
	public ProjectionList getProjections() {
		return Projections.projectionList().add(Projections.property("id"), "id")
				.add(Projections.property("pieza.denominacion"), "piezaNombre")
				.add(Projections.property("cantidad"), "cantidad")
				.add(Projections.property("observacion"), "observacion")
				.add(Projections.sqlProjection("to_char(FECHA, 'DD/MM/YYYY') as fecha", new String[] { "fecha" },
						new Type[] { StandardBasicTypes.STRING }))
				.add(Projections.property("origen"), "origen");
	}

}
