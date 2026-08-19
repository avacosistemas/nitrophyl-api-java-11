package ar.com.avaco.nitrophyl.service.pieza;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaStock;

public interface PiezaStockService extends NJService<Long, PiezaStock> {

	void incrementarStockFisico(Long idPieza, Integer cantidad);

}
