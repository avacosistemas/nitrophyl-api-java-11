package ar.com.avaco.nitrophyl.service.fabricacion;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompraDetallePedido;
import ar.com.avaco.nitrophyl.repository.fabricacion.OrdenCompraDetallePedidoRepository;

@Transactional
@Service("ordenCompraDetallePedidoService")
public class OrdenCompraDetallePedidoServiceImpl extends NJBaseService<Long, OrdenCompraDetallePedido, OrdenCompraDetallePedidoRepository> implements OrdenCompraDetallePedidoService {

	@Resource(name = "ordenCompraDetallePedidoRepository")
	void setRepository(OrdenCompraDetallePedidoRepository ordenCompraDetallePedidoRepository) {
		this.repository = ordenCompraDetallePedidoRepository;
	}

}
