package ar.com.avaco.ws.rest.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaStockDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaStockFilterDTO;
import ar.com.avaco.nitrophyl.ws.service.PiezaStockEPService;
import ar.com.avaco.nitrophyl.ws.service.filter.PiezaStockFilter;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class PiezaStockRestController
		extends AbstractAuditableDTORestController<PiezaStockDTO, Long, PiezaStockEPService> {

	@RequestMapping(value = "/pieza/stock", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> piezaStock(PiezaStockFilterDTO filterDTO) {
		PageDTO<PiezaStockDTO> listFilterCount = this.service.listFilterCount(new PiezaStockFilter(filterDTO), PiezaStockDTO.class);
		return OK(listFilterCount);
	}

	@Resource(name = "piezaStockEPService")
	public void setService(PiezaStockEPService piezaStockEPService) {
		super.service = piezaStockEPService;
	}

}