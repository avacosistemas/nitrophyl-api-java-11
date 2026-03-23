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
import ar.com.avaco.nitrophyl.ws.dto.OrdenCompraDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenCompraListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.service.OrdenCompraEPService;
import ar.com.avaco.nitrophyl.ws.service.filter.OrdenCompraFilter;
import ar.com.avaco.nitrophyl.ws.service.filter.OrdenCompraFilterDTO;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class OrdenCompraRestController extends AbstractAuditableDTORestController<OrdenCompraDTO, Long, OrdenCompraEPService> {

	@RequestMapping(value = "/ordenCompra", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(OrdenCompraFilterDTO ordenCompraFilterDTO) {
		PageDTO<?> listFilterCount = this.service.listFilterCount(new OrdenCompraFilter(ordenCompraFilterDTO), OrdenCompraListadoDTO.class);
		return OK(listFilterCount);
	}

	@Override
	@RequestMapping(value = "/ordenCompra", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> create(@RequestBody OrdenCompraDTO dto) throws BusinessException {
		return super.create(dto);
	}

	@RequestMapping(value = "/ordenCompra/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> get(@PathVariable Long id) throws BusinessException {
		return super.get(id);
	}
	
	@Resource(name = "ordenCompraEPService")
	public void setService(OrdenCompraEPService ordenCompraEPService) {
		super.service = ordenCompraEPService;
	}

}