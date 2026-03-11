package ar.com.avaco.nitrophyl.service.fabricacion;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompraDetalle;
import ar.com.avaco.nitrophyl.repository.fabricacion.OrdenCompraDetalleRepository;

@Transactional
@Service("ordenCompraDetalleService")
public class OrdenCompraDetalleServiceImpl extends NJBaseService<Long, OrdenCompraDetalle, OrdenCompraDetalleRepository> implements OrdenCompraDetalleService {

	@Resource(name = "ordenCompraDetalleRepository")
	void setRepository(OrdenCompraDetalleRepository ordenCompraDetalleRepository) {
		this.repository = ordenCompraDetalleRepository;
	}

}
