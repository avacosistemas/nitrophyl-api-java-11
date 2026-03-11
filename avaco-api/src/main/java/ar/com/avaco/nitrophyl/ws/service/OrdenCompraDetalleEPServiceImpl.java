package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompraDetalle;
import ar.com.avaco.nitrophyl.service.fabricacion.OrdenCompraDetalleService;
import ar.com.avaco.nitrophyl.ws.dto.OrdenCompraDetalleDTO;
import ar.com.avaco.nitrophyl.ws.service.OrdenCompraDetalleEPService;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("ordenCompraDetalleEPService")
public class OrdenCompraDetalleEPServiceImpl extends CRUDAuditableEPBaseService<Long, OrdenCompraDetalleDTO, OrdenCompraDetalle, OrdenCompraDetalleService>
		implements OrdenCompraDetalleEPService {

	public OrdenCompraDetalleEPServiceImpl() {
		super(OrdenCompraDetalle.class, OrdenCompraDetalleDTO.class);
	}

	@Override
	@Resource(name = "ordenCompraDetalleService")
	protected void setService(OrdenCompraDetalleService service) {
		this.service = service;
	}

}