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
import ar.com.avaco.nitrophyl.ws.dto.EnsayoDTO;
import ar.com.avaco.nitrophyl.ws.service.EnsayoEPService;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class EnsayoRestController extends AbstractDTORestController<EnsayoDTO, Long, EnsayoEPService> {

	@Resource(name = "ensayoEPService")
	public void setService(EnsayoEPService ensayoEPService) {
		super.service = ensayoEPService;
	}

	/**
	 * Devuelve el listado de ensayos de un lote en particular.
	 * @param idLote
	 * @return
	 */
	@RequestMapping(value = "/ensayo/{idLote}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(@PathVariable Long idLote) {
		List<EnsayoDTO> ensayos = super.service.listByLote(idLote);
		JSONResponse response = new JSONResponse();
		response.setData(ensayos);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	/**
	 * Guarda un nuevo ensayo.
	 */
	@RequestMapping(value = "/ensayo", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> create(@RequestBody EnsayoDTO ensayoDTO) throws BusinessException {
		EnsayoDTO saved = this.service.save(ensayoDTO);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo ensayo.
	 */
	@RequestMapping(value = "/ensayo", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> update(@RequestBody EnsayoDTO ensayoDTO) throws BusinessException {
		EnsayoDTO saved = this.service.update(ensayoDTO);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

}