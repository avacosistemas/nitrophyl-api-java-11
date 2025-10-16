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
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.ws.dto.MaquinaPruebaDTO;
import ar.com.avaco.nitrophyl.ws.service.MaquinaPruebaEPService;
import ar.com.avaco.ws.rest.dto.ErrorResponse;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class MaquinaPruebaRestController
		extends AbstractDTORestController<MaquinaPruebaDTO, Long, MaquinaPruebaEPService> {

	@RequestMapping(value = "/maquina/prueba/{idMaquina}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getPruebas(@PathVariable("idMaquina") Long idMaquina) throws Exception {
		List<MaquinaPruebaDTO> result = this.service.listPruebasByMaquina(idMaquina);
		JSONResponse response = new JSONResponse();
		response.setData(result);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/maquina/prueba", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> create(@RequestBody MaquinaPruebaDTO maquinaPruebaDTO)
			throws BusinessException {
		maquinaPruebaDTO.setId(null);
		MaquinaPruebaDTO saved = this.service.save(maquinaPruebaDTO);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/maquina/prueba/{idMaquinaPrueba}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> updatePruebas(@PathVariable("idMaquinaPrueba") Long idMaquinaPrueba,
			@RequestBody MaquinaPruebaDTO prueba) throws Exception {
		JSONResponse response = new JSONResponse();
		response.setStatus(JSONResponse.OK);
		try {
			prueba.setId(idMaquinaPrueba);
			MaquinaPruebaDTO update = this.service.update(prueba);
			response.setData(update);
		} catch (DataIntegrityViolationException e) {
			String message = "Error al actualizar listado de pruebas. Es probable que haya intentado borrar una prueba asociada a un ensayo.";
			response = new ErrorResponse(JSONResponse.ERROR, null, message,
					e.getCause().getCause().getLocalizedMessage());
		}
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/maquina/prueba/{idMaquinaPrueba}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> updatePruebas(@PathVariable("idMaquinaPrueba") Long idMaquinaPrueba)
			throws Exception {
		JSONResponse response = new JSONResponse();
		response.setStatus(JSONResponse.OK);
		try {
			this.service.remove(idMaquinaPrueba);
			response.setData(true);
		} catch (DataIntegrityViolationException e) {
			String message = "Error al borrar la prueba. Es probable que haya intentado borrar una que este asociada a un ensayo o parametrizacin.";
			response = new ErrorResponse(JSONResponse.ERROR, null, message,
					e.getCause().getCause().getLocalizedMessage());
		}
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@Override
	@Resource(name = "maquinaPruebaEPService")
	public void setService(MaquinaPruebaEPService service) {
		this.service = service;

	}

}