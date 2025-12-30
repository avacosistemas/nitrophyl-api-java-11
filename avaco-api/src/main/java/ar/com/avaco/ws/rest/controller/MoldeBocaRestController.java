package ar.com.avaco.ws.rest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.nitrophyl.ws.dto.MoldeBocaDTO;
import ar.com.avaco.nitrophyl.ws.service.MoldeBocaEPService;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class MoldeBocaRestController
		extends AbstractAuditableDTORestController<MoldeBocaDTO, Long, MoldeBocaEPService> {

	@Resource(name = "moldeBocaEPService")
	public void setService(MoldeBocaEPService moldeBocaEPService) {
		super.service = moldeBocaEPService;
	}

	@RequestMapping(value = "/moldeBoca", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(@RequestParam Long idMolde) throws Exception {
		List<MoldeBocaDTO> listByMoldeId = this.service.listByMoldeId(idMolde);
		JSONResponse response = new JSONResponse();
		response.setData(listByMoldeId);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/moldeBoca", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> addMolde(@RequestBody MoldeBocaDTO moldeBocaDTO) throws Exception {
		try {
			MoldeBocaDTO save = this.service.save(moldeBocaDTO);
			JSONResponse response = new JSONResponse();
			response.setData(save);
			response.setStatus(JSONResponse.OK);
			return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException(
					"Violacion de integridad, verifique que no exista un Molde con el mismo Codigo", e);
		}
	}

	@RequestMapping(value = "/moldeBoca/{idMoldeBoca}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> updateMolde(@PathVariable("idMoldeBoca") Long id,
			@RequestBody MoldeBocaDTO moldeBocaDTO) throws Exception {
		moldeBocaDTO.setId(id);
		MoldeBocaDTO update = this.service.update(moldeBocaDTO);
		JSONResponse response = new JSONResponse();
		response.setData(update);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/moldeBoca/{idMoldeBoca}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> removeMolde(@PathVariable("idMoldeBoca") Long id) throws Exception {
		this.service.remove(id);
		JSONResponse response = new JSONResponse();
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

}