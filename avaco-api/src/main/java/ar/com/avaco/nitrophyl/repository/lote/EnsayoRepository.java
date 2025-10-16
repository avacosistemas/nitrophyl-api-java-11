package ar.com.avaco.nitrophyl.repository.lote;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.lote.Ensayo;

public interface EnsayoRepository extends NJRepository<Long, Ensayo>, EnsayoRepositoryCustom {

	List<Ensayo> findByLoteId(Long idLote);

	int countByLoteId(Long idLote);

}
