package ar.com.avaco.nitrophyl.repository.lote;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.molde.LoteGrafico;

public interface LoteGraficoRepository extends NJRepository<Long, LoteGrafico>, LoteGraficoRepositoryCustom {

	Optional<LoteGrafico> findFirstByIdLoteOrderByFecha(Long idLote);

	@Query(value = "select idLote from LoteGrafico where idLote in (?1)")
	List<Long> findLoteIdLoteByIdLoteIn(List<Long> loteIds);
	
}
