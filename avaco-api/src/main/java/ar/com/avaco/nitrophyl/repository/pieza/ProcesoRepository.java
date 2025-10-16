package ar.com.avaco.nitrophyl.repository.pieza;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Proceso;

public interface ProcesoRepository extends NJRepository<Long, Proceso>, ProcesoRepositoryCustom {

}