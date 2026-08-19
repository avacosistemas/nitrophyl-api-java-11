package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaStock;
import ar.com.avaco.nitrophyl.repository.pieza.PiezaStockRepository;

@Transactional
@Service("piezaStockService")
public class PiezaStockServiceImpl extends NJBaseService<Long, PiezaStock, PiezaStockRepository>
		implements PiezaStockService {

	@Autowired
	private PiezaService piezaService;
	
	@Resource(name = "piezaStockRepository")
	void setPiezaStockRepository(PiezaStockRepository piezaStockRepository) {
		this.repository = piezaStockRepository;
	}

	@Override
	public void incrementarStockFisico(Long idPieza, Integer cantidad) {
		if (this.exists(idPieza)) {
			PiezaStock piezaStock = this.get(idPieza);
			piezaStock.incrementarStockFisico(cantidad);
			this.update(piezaStock);
		} else {
			Pieza pieza = piezaService.get(idPieza);
			PiezaStock piezaStock = PiezaStock.builder().pieza(pieza).stockFisico(cantidad)
					.stockReservado(0).build();
			this.save(piezaStock);
		}
	}

}
