package ar.com.avaco.nitrophyl.repository.pieza;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.ws.dto.PiezaFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaGrillaDTO;

@Repository("piezaRepository")
public class PiezaRepositoryImpl extends NJBaseRepository<Long, Pieza> implements PiezaRepositoryCustom {

	public PiezaRepositoryImpl(EntityManager entityManager) {
		super(Pieza.class, entityManager);
	}

	@Override
	public List<PiezaGrillaDTO> listGrilla(PiezaFilterDTO filtro) {
		
		if (filtro.getNombre() != null)
			filtro.setNombre(filtro.getNombre().toUpperCase());
		
		String query = " SELECT cast(COUNT(*) OVER() as integer) as rows, p.denominacion as denominacion, " + 
				"  cast(p.id_pieza as integer) as idPieza, p.codigo, cast(p.vigente as boolean), " + 
				"  cast(p.revision as integer), p.fecha_revision as fechaRevision, " + 
				"  pt.nombre as tipo, m.nombre as material, " +
				"  CASE " + 
				"    WHEN p.vigente = true AND p.revision = v.max_revision THEN true" + 
				"    ELSE false" + 
				"  END AS puedeGenerarRevision," +
				"  f.nombre as formula, " + 
				"  CASE" + 
				"    WHEN p.vigente = false AND p.revision = v.max_revision THEN true " + 
				"    ELSE false " + 
				"  END AS puedeMarcarVigente " + 
				" FROM pieza p " + 
				" LEFT JOIN ( " + 
				"    SELECT codigo, MAX(revision) AS max_revision " + 
				"    FROM pieza " + 
				"    GROUP BY codigo " + 
				" ) v ON p.codigo = v.codigo " + 
				" left join formula f on f.id_formula = p.id_formula " + 
				" left join material m on m.id_material = f.id_material " + 
				" left join pieza_tipo pt on pt.id_pieza_tipo = p.id_tipo " + 
				" where 1 = 1 ";
		
		if (filtro.getSoloVigentes() != null && filtro.getSoloVigentes().booleanValue())
			query += " and p.vigente = true ";
		
		if (StringUtils.isNotBlank(filtro.getNombre()))
			query += " and (upper(p.denominacion) like '%" + filtro.getNombre() + "%' or upper(p.codigo) like '%" + filtro.getNombre() + "%') ";
		
		if (filtro.getIdFormula() != null)
			query += " and f.id_formula = " + filtro.getIdFormula();
			
		if (filtro.getIdMaterial() != null)
			query += " and m.id_material = " + filtro.getIdMaterial();
		
		if (StringUtils.isNotBlank(filtro.getIdTipoPieza()))
			query += " and p.id_tipo in (" + filtro.getIdTipoPieza() + ") ";
		
		if (StringUtils.isNotBlank(filtro.getIdx())) {
			query += " order by " + filtro.getIdx();
			if (filtro.getAsc() != null && filtro.getAsc().booleanValue()) {
				query += " asc ";
			} else {
				query += " desc ";
			}
		} else {
			query += " order by p.codigo desc, p.revision asc ";
		}
		
		if (filtro.getRows() != null && filtro.getFirst() != null) {
			query += " limit " + filtro.getRows();
			query += " offset " + (filtro.getFirst() - 1);
		}
		
		
		SQLQuery createSQLQuery = getCurrentSession().createSQLQuery(query)
				.setResultSetMapping("PiezaGrillaDTOMapper");

		return createSQLQuery.list();
		
	}
	
}
