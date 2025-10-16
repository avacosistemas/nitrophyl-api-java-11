package ar.com.avaco.nitrophyl.repository.lote;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.moldes.LoteGrafico;
import ar.com.avaco.nitrophyl.ws.dto.LoteGraficoSinArchivoDTO;

@Repository("loteGraficoRepository")
public class LoteGraficoRepositoryImpl extends NJBaseRepository<Long, LoteGrafico>
		implements LoteGraficoRepositoryCustom {

	public LoteGraficoRepositoryImpl(EntityManager entityManager) {
		super(LoteGrafico.class, entityManager);
	}

	@Override
	public List<LoteGraficoSinArchivoDTO> listGraficoSinArchivo(Long idLote) {
		String query = " select cast(lg.id_lote_grafico as integer) as id, cast(lg.fecha as date) as fecha, " + 
				" m.nombre as maquina from lote_grafico lg " + 
				" left join maquina m on m.id_maquina = lg.id_maquina " + 
				" where lg.id_lote = :idLote order by lg.fecha desc, m.nombre asc ";

		SQLQuery createSQLQuery = getCurrentSession().createSQLQuery(query)
				.setResultSetMapping("LoteGraficoSinArchivoDTOMapper");

		createSQLQuery.setLong("idLote", idLote);

		List<LoteGraficoSinArchivoDTO> list = createSQLQuery.list();
		
		return list;
	}
	
}
