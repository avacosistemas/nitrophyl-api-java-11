package ar.com.avaco.nitrophyl.repository.fabricacion;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenFabricacionEntrega;

public interface OrdenFabricacionEntregaRepository
		extends NJRepository<Long, OrdenFabricacionEntrega>, OrdenFabricacionEntregaRepositoryCustom {

}