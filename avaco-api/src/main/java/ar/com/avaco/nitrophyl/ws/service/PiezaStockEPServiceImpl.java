package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaStock;
import ar.com.avaco.nitrophyl.service.pieza.PiezaStockService;
import ar.com.avaco.nitrophyl.ws.dto.PiezaStockDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Transactional
@Service("piezaStockEPService")
public class PiezaStockEPServiceImpl extends
		CRUDAuditableEPBaseService<Long, PiezaStockDTO, PiezaStock, PiezaStockService> implements PiezaStockEPService {

	public PiezaStockEPServiceImpl() {
		super(PiezaStock.class, PiezaStockDTO.class);
	}

	@Override
	@Resource(name = "piezaStockService")
	protected void setService(PiezaStockService service) {
		this.service = service;
	}

}
