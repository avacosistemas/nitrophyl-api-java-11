package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Insumo;
import ar.com.avaco.nitrophyl.domain.entities.pieza.InsumoStockHistorial;
import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoMovimientoStock;
import ar.com.avaco.nitrophyl.repository.pieza.InsumoStockHistorialRepository;

@Transactional
@Service("insumoStockHistorialService")
public class InsumoStockHistorialServiceImpl extends NJBaseService<Long, InsumoStockHistorial, InsumoStockHistorialRepository> implements InsumoStockHistorialService {

	@Autowired
	private InsumoService insumoService;
	
	@Override
	public InsumoStockHistorial save(InsumoStockHistorial entity) {
		Insumo insumo = insumoService.get(entity.getInsumo().getId());
		Double stockActual = insumo.getCantidadStock();
		Double nuevoStock = TipoMovimientoStock.calcularStock(stockActual, entity.getTipo(), entity.getCantidad());
		insumo.setCantidadStock(nuevoStock);
		super.updateUserDateModificacion(insumo);
		entity.setInsumo(insumo);
		return super.save(entity);
	}
	
	@Resource(name = "insumoStockHistorialRepository")
	void setRepository(InsumoStockHistorialRepository insumoStockHistorialRepository) {
		this.repository = insumoStockHistorialRepository;
	}

}
