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
import ar.com.avaco.nitrophyl.ws.dto.PiezaControlDTO;
import ar.com.avaco.nitrophyl.ws.service.PiezaControlEPService;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class PiezaControlRestController
		extends AbstractAuditableDTORestController<PiezaControlDTO, Long, PiezaControlEPService> {

	@RequestMapping(value = "/piezaControl/{idPieza}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(@PathVariable Long idPieza) {
		List<PiezaControlDTO> controlesGenerales = this.service.listEq("pieza.id", idPieza);
		List<PiezaControlDTO> controles = this.service.listControlesConfigurados(idPieza);
		controles.addAll(controlesGenerales);
		JSONResponse response = new JSONResponse();
		response.setData(controles);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/piezaControl", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> create(@RequestBody PiezaControlDTO dto) throws BusinessException {
		return super.create(dto);
	}

	@Override
	@RequestMapping(value = "/piezaControl/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> update(@PathVariable Long id, @RequestBody PiezaControlDTO dto)
			throws BusinessException {
		return super.update(id, dto);
	}

	@Override
	@RequestMapping(value = "/piezaControl/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> delete(@PathVariable Long id) throws BusinessException {
		return super.delete(id);
	}

	@Resource(name = "piezaControlEPService")
	public void setService(PiezaControlEPService piezaControlEPService) {
		super.service = piezaControlEPService;
	}

}