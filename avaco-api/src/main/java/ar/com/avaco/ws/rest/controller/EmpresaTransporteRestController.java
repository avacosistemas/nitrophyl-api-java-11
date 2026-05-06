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
import ar.com.avaco.nitrophyl.ws.dto.EmpresaTransporteDTO;
import ar.com.avaco.nitrophyl.ws.dto.EmpresaTransporteFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.service.EmpresaTransporteEPService;
import ar.com.avaco.nitrophyl.ws.service.filter.EmpresaTransporteFilter;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class EmpresaTransporteRestController extends AbstractAuditableDTORestController<EmpresaTransporteDTO, Long, EmpresaTransporteEPService> {

	@RequestMapping(value = "/empresaTransporte", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(EmpresaTransporteFilterDTO dto) {
		PageDTO<EmpresaTransporteDTO> listFilterCount = this.service.listFilterCount(new EmpresaTransporteFilter(dto));
		JSONResponse response = new JSONResponse();
		response.setData(listFilterCount);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/empresaTransporte", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> create(@RequestBody EmpresaTransporteDTO dto) throws BusinessException {
		return super.create(dto);
	}

	@Override
	@RequestMapping(value = "/empresaTransporte/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> update(@PathVariable Long id, @RequestBody EmpresaTransporteDTO dto)
			throws BusinessException {
		return super.update(id, dto);
	}

	@Override
	@RequestMapping(value = "/empresaTransporte/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> delete(@PathVariable Long id) throws BusinessException {
		return super.delete(id);
	}

	@Resource(name = "empresaTransporteEPService")
	public void setService(EmpresaTransporteEPService empresaTransporteEPService) {
		super.service = empresaTransporteEPService;
	}

}