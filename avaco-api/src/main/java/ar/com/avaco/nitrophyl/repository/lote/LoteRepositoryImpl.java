package ar.com.avaco.nitrophyl.repository.lote;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.nitrophyl.ws.dto.RegistroEnsayoLotePorMaquinaDTO;
import ar.com.avaco.nitrophyl.ws.dto.ReporteEnsayoLotePorMaquinaFilterDTO;
import ar.com.avaco.utils.DateUtils;

@Repository("loteRepository")
public class LoteRepositoryImpl extends NJBaseRepository<Long, Lote> implements LoteRepositoryCustom {

	public LoteRepositoryImpl(EntityManager entityManager) {
		super(Lote.class, entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<RegistroEnsayoLotePorMaquinaDTO> getEnsayosLotePorMaquina(ReporteEnsayoLotePorMaquinaFilterDTO filtro) {
		
		if (filtro.getAsc() == null) {
			filtro.setAsc(true);
		}
		
		if (filtro.getIdx().isEmpty()) {
			filtro.setIdx("d.fecha");
		}
		
		String query = "WITH datos AS ( " + 
				"    SELECT  " + 
				"        cast(l.id_lote as integer) as idLote, " + 
				"        l.nro_lote as nroLote, " + 
				"        l.fecha AS fecha, " + 
				"        e.observaciones, " + 
				"        cast(l.id_formula as integer) as idFormula, " + 
				"        f.nombre as nombreFormula, " + 
				"        cpp.id_maquina_prueba as idMaquinaPrueba, " + 
				"        er.redondeo, " + 
				"        er.resultado, " + 
				"        e.estado AS estadoEnsayo " + 
				"    FROM lote l " + 
				"    INNER JOIN formula f  " + 
				"        ON f.id_formula = l.id_formula " + 
				"    INNER JOIN ensayo e  " + 
				"        ON e.id_lote = l.id_lote " + 
				"    INNER JOIN ensayo_resultado er  " + 
				"        ON er.id_ensayo = e.id_ensayo " + 
				"    INNER JOIN conf_prueba_param cpp " + 
				"        ON cpp.id_conf_prueba_param = er.id_conf_prueba_param " + 
				"    INNER JOIN conf_prueba cp " + 
				"        ON cp.id_conf_prueba = e.id_conf_prueba " + 
				"    WHERE cp.id_maquina = " + filtro.getIdMaquina(); 
				
		if (filtro.getFechaDesde() != null) {
			query += " and l.fecha >= '" + DateUtils.toString(filtro.getFechaDesde(), DateUtils.yyyyMMdd) + "'";
		}

		if (filtro.getFechaHasta() != null) {
			query += " and l.fecha <= '" + DateUtils.toString(filtro.getFechaHasta(), DateUtils.yyyyMMdd) + "'";
		}
		
		if (StringUtils.isNotBlank(filtro.getNroLote())) {
			query += " and l.nro_lote like '%" + filtro.getNroLote() + "%' ";
		}
	
		if (filtro.getIdFormula() != null) {
			query += " and l.id_formula = " + filtro.getIdFormula();
		}

		if (!filtro.getEstadoEnsayo().isEmpty()) {
			query += " and e.estado = '" + filtro.getEstadoEnsayo() + "' ";
		}
		
				
		query += "), " + 
				"conteo AS ( " + 
				"    SELECT CAST(COUNT(*) AS integer) AS rows FROM datos " + 
				") " + 
				"SELECT  " + 
				"    c.rows, " + 
				"    cast(ROW_NUMBER() OVER ( " + 
				"        ORDER BY  "; 
		
		query += filtro.getIdx()  + " " + (filtro.getAsc() ? " ASC ": " DESC "); 
				
		
		query += "    ) as integer) AS row, " + 
				"    d.* " + 
				"FROM datos d " + 
				"CROSS JOIN conteo c " + 
				"ORDER BY  "; 
		query += filtro.getIdx()  + " " + (filtro.getAsc() ? " ASC ": " DESC ");  
		query += "LIMIT " + filtro.getRows() + " OFFSET " + filtro.getFirst() + ";";
		
		NativeQuery<RegistroEnsayoLotePorMaquinaDTO> nativeQuery = getCurrentSession().createNativeQuery(query)
				.setResultSetMapping("RegistroEnsayoLotePorMaquinaDTOMapper");
		
		List<RegistroEnsayoLotePorMaquinaDTO> list = nativeQuery.getResultList();
		
		return list;
	}


}
