package ar.com.avaco.nitrophyl.repository.maquina;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.maquina.Maquina;

public interface MaquinaRepository extends NJRepository<Long, Maquina>, MaquinaRepositoryCustom {

}
