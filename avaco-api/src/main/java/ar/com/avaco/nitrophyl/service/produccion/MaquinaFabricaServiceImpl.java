package ar.com.avaco.nitrophyl.service.produccion;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.MaquinaFabrica;
import ar.com.avaco.nitrophyl.repository.produccion.MaquinaFabricaRepository;

@Transactional
@Service("maquinaFabricaService")
public class MaquinaFabricaServiceImpl extends NJBaseService<Long, MaquinaFabrica, MaquinaFabricaRepository> implements MaquinaFabricaService {

	@Resource(name = "maquinaFabricaRepository")
	void setRepository(MaquinaFabricaRepository maquinaFabricaRepository) {
		this.repository = maquinaFabricaRepository;
	}

}
