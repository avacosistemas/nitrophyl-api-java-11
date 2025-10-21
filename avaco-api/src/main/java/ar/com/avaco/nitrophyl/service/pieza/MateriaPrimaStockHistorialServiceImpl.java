package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.MateriaPrima;
import ar.com.avaco.nitrophyl.domain.entities.pieza.MateriaPrimaStockHistorial;
import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoMovimientoStock;
import ar.com.avaco.nitrophyl.repository.pieza.MateriaPrimaStockHistorialRepository;

@Transactional
@Service("materiaPrimaStockHistorialService")
public class MateriaPrimaStockHistorialServiceImpl extends NJBaseService<Long, MateriaPrimaStockHistorial, MateriaPrimaStockHistorialRepository> implements MateriaPrimaStockHistorialService {

	@Autowired
	private MateriaPrimaService materiaPrimaService;
	
	@Override
	public MateriaPrimaStockHistorial save(MateriaPrimaStockHistorial entity) {
		MateriaPrima materiaPrima = materiaPrimaService.get(entity.getMateriaPrima().getId());
		Double stockActual = materiaPrima.getCantidadStock();
		Double nuevoStock = calcularStock(stockActual, entity.getTipo(), entity.getCantidad());
		materiaPrima.setCantidadStock(nuevoStock);
		materiaPrima.setUnidadMedidaStock(entity.getUnidadMedida());
		super.updateUserDateModificacion(materiaPrima);
		entity.setMateriaPrima(materiaPrima);
		return super.save(entity);
	}
	
	private Double calcularStock(Double stockActual, TipoMovimientoStock tipo, Double cantidad) {
		return stockActual + (cantidad * tipo.getMultiplicador());
	}
	
	
	@Resource(name = "materiaPrimaStockHistorialRepository")
	void setRepository(MateriaPrimaStockHistorialRepository materiaPrimaStockHistorialRepository) {
		this.repository = materiaPrimaStockHistorialRepository;
	}

}
