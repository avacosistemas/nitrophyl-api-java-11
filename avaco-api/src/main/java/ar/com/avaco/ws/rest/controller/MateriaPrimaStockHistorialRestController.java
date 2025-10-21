package ar.com.avaco.ws.rest.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.ws.dto.MateriaPrimaStockHistorialDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.service.MateriaPrimaStockHistorialEPService;
import ar.com.avaco.nitrophyl.ws.service.filter.SimplePager;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class MateriaPrimaStockHistorialRestController extends AbstractAuditableDTORestController<MateriaPrimaStockHistorialDTO, Long, MateriaPrimaStockHistorialEPService> {

	@RequestMapping(value = "/materiaPrimaStockHistorial", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(SimplePager sp) {
		PageDTO<MateriaPrimaStockHistorialDTO> page = this.service.listFilterCount(sp);
		return OK(page);
	}

	@Override
	@RequestMapping(value = "/materiaPrimaStockHistorial", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> create(@RequestBody MateriaPrimaStockHistorialDTO dto) throws BusinessException {
		return super.create(dto);
	}

	@Resource(name = "materiaPrimaStockHistorialEPService")
	public void setService(MateriaPrimaStockHistorialEPService materiaPrimaStockHistorialEPService) {
		super.service = materiaPrimaStockHistorialEPService;
	}

}