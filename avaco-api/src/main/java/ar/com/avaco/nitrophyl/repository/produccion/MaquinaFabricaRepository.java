package ar.com.avaco.nitrophyl.repository.produccion;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.MaquinaFabrica;

public interface MaquinaFabricaRepository extends NJRepository<Long, MaquinaFabrica>, MaquinaFabricaRepositoryCustom {

}