package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.InsumoStockHistorial;
import ar.com.avaco.nitrophyl.repository.pieza.InsumoStockHistorialRepository;

@Transactional
@Service("insumoStockHistorialService")
public class InsumoStockHistorialServiceImpl extends NJBaseService<Long, InsumoStockHistorial, InsumoStockHistorialRepository> implements InsumoStockHistorialService {

	@Resource(name = "insumoStockHistorialRepository")
	void setRepository(InsumoStockHistorialRepository insumoStockHistorialRepository) {
		this.repository = insumoStockHistorialRepository;
	}

}
