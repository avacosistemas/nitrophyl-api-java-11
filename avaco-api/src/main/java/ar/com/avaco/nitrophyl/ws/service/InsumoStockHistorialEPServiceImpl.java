package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.InsumoStockHistorial;
import ar.com.avaco.nitrophyl.service.pieza.InsumoStockHistorialService;
import ar.com.avaco.nitrophyl.ws.dto.InsumoStockHistorialDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("insumoStockHistorialEPService")
public class InsumoStockHistorialEPServiceImpl extends CRUDAuditableEPBaseService<Long, InsumoStockHistorialDTO, InsumoStockHistorial, InsumoStockHistorialService>
		implements InsumoStockHistorialEPService {

	public InsumoStockHistorialEPServiceImpl() {
		super(InsumoStockHistorial.class, InsumoStockHistorialDTO.class);
	}

	@Override
	@Resource(name = "insumoStockHistorialService")
	protected void setService(InsumoStockHistorialService service) {
		this.service = service;
	}

}