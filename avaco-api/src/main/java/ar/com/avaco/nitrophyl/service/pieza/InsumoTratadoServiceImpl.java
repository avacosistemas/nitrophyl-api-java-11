package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.InsumoTratado;
import ar.com.avaco.nitrophyl.repository.pieza.InsumoTratadoRepository;

@Transactional
@Service("insumoTratadoService")
public class InsumoTratadoServiceImpl extends NJBaseService<Long, InsumoTratado, InsumoTratadoRepository> implements InsumoTratadoService {

	@Resource(name = "insumoTratadoRepository")
	void setRepository(InsumoTratadoRepository insumoTratadoRepository) {
		this.repository = insumoTratadoRepository;
	}

}
