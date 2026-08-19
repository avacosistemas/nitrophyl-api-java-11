package ar.com.avaco.nitrophyl.ws.service;

import java.time.LocalDate;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.nitrophyl.domain.entities.pieza.OrigenMovimientoStock;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaMovimientoStock;
import ar.com.avaco.nitrophyl.service.pieza.PiezaMovimientoStockService;
import ar.com.avaco.nitrophyl.service.pieza.PiezaService;
import ar.com.avaco.nitrophyl.service.pieza.PiezaStockService;
import ar.com.avaco.nitrophyl.ws.dto.PiezaMovimientoStockDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Transactional
@Service("piezaMovimientoStockEPService")
public class PiezaMovimientoStockEPServiceImpl extends
		CRUDAuditableEPBaseService<Long, PiezaMovimientoStockDTO, PiezaMovimientoStock, PiezaMovimientoStockService>
		implements PiezaMovimientoStockEPService {

	@Autowired
	private PiezaStockService piezaStockService;
	
	public PiezaMovimientoStockEPServiceImpl() {
		super(PiezaMovimientoStock.class, PiezaMovimientoStockDTO.class);
	}

	@Override
	@Resource(name = "piezaMovimientoStockService")
	protected void setService(PiezaMovimientoStockService service) {
		this.service = service;
	}

	@Override
	public void registrarIngresoManual(Long idPieza, Integer cantidad, LocalDate fecha, String observacion) {
		PiezaMovimientoStock pms = PiezaMovimientoStock.builder().cantidad(cantidad).fecha(fecha)
				.observacion(observacion).origen(OrigenMovimientoStock.INGRESO_MANUAL).pieza(Pieza.ofId(idPieza))
				.build();
		
		piezaStockService.incrementarStockFisico(idPieza, cantidad);
		this.service.save(pms);
	}

}
