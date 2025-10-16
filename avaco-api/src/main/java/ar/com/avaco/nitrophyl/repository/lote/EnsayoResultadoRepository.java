package ar.com.avaco.nitrophyl.repository.lote;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.lote.EnsayoResultado;

public interface EnsayoResultadoRepository extends NJRepository<Long, EnsayoResultado>, EnsayoResultadoRepositoryCustom {

	List<EnsayoResultado> findByEnsayoId(Long idEnsayo);

}
