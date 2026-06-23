package ar.com.avaco.nitrophyl.service.produccion;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.SectorFabrica;
import ar.com.avaco.nitrophyl.repository.produccion.SectorFabricaRepository;

@Transactional
@Service("sectorFabricaService")
public class SectorFabricaServiceImpl extends NJBaseService<Long, SectorFabrica, SectorFabricaRepository> implements SectorFabricaService {

	@Resource(name = "sectorFabricaRepository")
	void setRepository(SectorFabricaRepository sectorFabricaRepository) {
		this.repository = sectorFabricaRepository;
	}

}
