package ar.com.avaco.nitrophyl.service.fabricacion;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompra;
import ar.com.avaco.nitrophyl.repository.fabricacion.OrdenCompraRepository;

@Transactional
@Service("ordenCompraService")
public class OrdenCompraServiceImpl extends NJBaseService<Long, OrdenCompra, OrdenCompraRepository> implements OrdenCompraService {

	@Resource(name = "ordenCompraRepository")
	void setRepository(OrdenCompraRepository ordenCompraRepository) {
		this.repository = ordenCompraRepository;
	}

}
