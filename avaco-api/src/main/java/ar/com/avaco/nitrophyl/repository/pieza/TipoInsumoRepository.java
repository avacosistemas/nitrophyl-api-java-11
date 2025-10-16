package ar.com.avaco.nitrophyl.repository.pieza;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoInsumo;

public interface TipoInsumoRepository extends NJRepository<Long, TipoInsumo>, TipoInsumoRepositoryCustom {

}
