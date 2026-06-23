package ar.com.avaco.nitrophyl.service.fabricacion;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenFabricacionEntrega;
import ar.com.avaco.nitrophyl.repository.fabricacion.OrdenFabricacionEntregaRepository;

@Transactional
@Service("ordenFabricacionEntregaService")
public class OrdenFabricacionEntregaServiceImpl extends NJBaseService<Long, OrdenFabricacionEntrega, OrdenFabricacionEntregaRepository>
		implements OrdenFabricacionEntregaService {

	@Resource(name = "ordenFabricacionEntregaRepository")
	void setRepository(OrdenFabricacionEntregaRepository ordenFabricacionEntregaRepository) {
		this.repository = ordenFabricacionEntregaRepository;
	}

}
