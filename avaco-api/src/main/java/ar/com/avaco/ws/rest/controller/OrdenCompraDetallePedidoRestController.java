package ar.com.avaco.ws.rest.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.ws.dto.OrdenCompraDetallePedidoDTO;
import ar.com.avaco.nitrophyl.ws.service.OrdenCompraDetallePedidoEPService;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class OrdenCompraDetallePedidoRestController extends AbstractAuditableDTORestController<OrdenCompraDetallePedidoDTO, Long, OrdenCompraDetallePedidoEPService> {

	@Override
	@RequestMapping(value = "/ordenCompraDetallePedido", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list() {
		return super.list();
	}

	@Override
	@RequestMapping(value = "/ordenCompraDetallePedido", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> create(@RequestBody OrdenCompraDetallePedidoDTO dto) throws BusinessException {
		return super.create(dto);
	}

	@Override
	@RequestMapping(value = "/ordenCompraDetallePedido/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> update(@PathVariable Long id, @RequestBody OrdenCompraDetallePedidoDTO dto)
			throws BusinessException {
		return super.update(id, dto);
	}

	@Override
	@RequestMapping(value = "/ordenCompraDetallePedido/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> delete(@PathVariable Long id) throws BusinessException {
		return super.delete(id);
	}

	@Resource(name = "ordenCompraDetallePedidoEPService")
	public void setService(OrdenCompraDetallePedidoEPService ordenCompraDetallePedidoEPService) {
		super.service = ordenCompraDetallePedidoEPService;
	}

}