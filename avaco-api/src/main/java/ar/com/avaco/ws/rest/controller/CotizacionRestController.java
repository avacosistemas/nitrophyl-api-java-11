package ar.com.avaco.ws.rest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.ws.dto.CotizacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.CotizacionFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.service.CotizacionEPService;
import ar.com.avaco.nitrophyl.ws.service.filter.CotizacionFilter;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class CotizacionRestController
		extends AbstractAuditableDTORestController<CotizacionDTO, Long, CotizacionEPService> {

	@RequestMapping(value = "/cotizacion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(CotizacionFilterDTO filterDTO) {
		PageDTO<CotizacionDTO> page = this.service.list(filterDTO);
		
		JSONResponse response = new JSONResponse();
		response.setData(page);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/cotizacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> create(@RequestBody CotizacionDTO dto) throws BusinessException {
		return super.create(dto);
	}

	@Resource(name = "cotizacionEPService")
	public void setService(CotizacionEPService cotizacionEPService) {
		super.service = cotizacionEPService;
	}

}