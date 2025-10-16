package ar.com.avaco.ws.rest.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.dto.ReporteLoteConfiguracionClienteDTO;
import ar.com.avaco.nitrophyl.ws.dto.ReporteLoteConfiguracionClienteFilterDTO;
import ar.com.avaco.nitrophyl.ws.service.ReporteLoteConfiguracionClienteEPService;
import ar.com.avaco.nitrophyl.ws.service.filter.ReporteLoteConfiguracionClienteFilter;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class ReporteLoteConfiguracionClienteRestController extends
		AbstractDTORestController<ReporteLoteConfiguracionClienteDTO, Long, ReporteLoteConfiguracionClienteEPService> {

	@Resource(name = "reporteLoteConfiguracionClienteEPService")
	public void setService(ReporteLoteConfiguracionClienteEPService reporteLoteConfiguracionClienteEPService) {
		super.service = reporteLoteConfiguracionClienteEPService;
	}

	@Override
	@RequestMapping(value = "/configuracion/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> get(@PathVariable Long id) throws BusinessException {
		return super.get(id);
	}
	
	@RequestMapping(value = "/configuracion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(ReporteLoteConfiguracionClienteFilterDTO dto) {
		PageDTO<ReporteLoteConfiguracionClienteDTO> ret = this.service
				.listFilterCount(new ReporteLoteConfiguracionClienteFilter(dto));
		JSONResponse response = new JSONResponse();
		response.setData(ret);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/configuracion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> create(@RequestBody ReporteLoteConfiguracionClienteDTO dto) throws BusinessException {
		return super.create(dto);
	}

	@Override
	@RequestMapping(value = "/configuracion/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> update(@PathVariable Long id, @RequestBody ReporteLoteConfiguracionClienteDTO dto)
			throws BusinessException {
		return super.update(id, dto);
	}

	@Override
	@RequestMapping(value = "/configuracion/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResponse> delete(@PathVariable Long id) throws BusinessException {
		return super.delete(id);
	}

}