package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaMovimientoStock;
import ar.com.avaco.nitrophyl.repository.pieza.PiezaMovimientoStockRepository;

@Transactional
@Service("piezaMovimientoStockService")
public class PiezaMovimientoStockServiceImpl extends NJBaseService<Long, PiezaMovimientoStock, PiezaMovimientoStockRepository>
		implements PiezaMovimientoStockService {

	@Resource(name = "piezaMovimientoStockRepository")
	void setPiezaMovimientoStockRepository(PiezaMovimientoStockRepository piezaMovimientoStockRepository) {
		this.repository = piezaMovimientoStockRepository;
	}

}
