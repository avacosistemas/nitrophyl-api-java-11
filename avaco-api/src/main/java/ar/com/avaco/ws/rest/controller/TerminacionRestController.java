package ar.com.avaco.ws.rest.controller;

import java.util.List;

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
import ar.com.avaco.nitrophyl.ws.dto.TerminacionDTO;
import ar.com.avaco.nitrophyl.ws.service.TerminacionEPService;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class TerminacionRestController
		extends AbstractAuditableDTORestController<TerminacionDTO, Long, TerminacionEPService> {

	@RequestMapping(value = "/terminacion/{idProceso}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(@PathVariable Long idProceso) {
		List<TerminacionDTO> list = this.service.listEq("proceso.id", idProceso);
		JSONResponse response = new JSONResponse();
		response.setData(list.get(0));
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/terminacion/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> update(@PathVariable Long id, @RequestBody TerminacionDTO dto)
			throws BusinessException {
		return super.update(id, dto);
	}

	@Resource(name = "terminacionEPService")
	public void setService(TerminacionEPService terminacionEPService) {
		super.service = terminacionEPService;
	}

}