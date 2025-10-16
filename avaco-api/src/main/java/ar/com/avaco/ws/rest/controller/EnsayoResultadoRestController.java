package ar.com.avaco.ws.rest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.nitrophyl.ws.dto.EnsayoResultadoDTO;
import ar.com.avaco.nitrophyl.ws.service.EnsayoResultadoEPService;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class EnsayoResultadoRestController
		extends AbstractDTORestController<EnsayoResultadoDTO, Long, EnsayoResultadoEPService> {

	@Resource(name = "ensayoResultadoEPService")
	public void setService(EnsayoResultadoEPService ensayoResultadoEPService) {
		super.service = ensayoResultadoEPService;
	}

	@RequestMapping(value = "/ensayoResultado/{idEnsayo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(@PathVariable Long idEnsayo) {
		List<EnsayoResultadoDTO> ensayoResultados = super.service.listByEnsayo(idEnsayo);
		JSONResponse response = new JSONResponse();
		response.setData(ensayoResultados);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
}