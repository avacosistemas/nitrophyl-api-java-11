package ar.com.avaco.ws.rest.controller;

import java.util.ArrayList;
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
import ar.com.avaco.nitrophyl.ws.dto.ComboDTO;
import ar.com.avaco.nitrophyl.ws.dto.MaquinaDTO;
import ar.com.avaco.nitrophyl.ws.dto.MaquinaFilterDTO;
import ar.com.avaco.nitrophyl.ws.service.MaquinaEPService;
import ar.com.avaco.nitrophyl.ws.service.filter.MaquinaFilter;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class MaquinaRestController extends AbstractDTORestController<MaquinaDTO, Long, MaquinaEPService> {

	@RequestMapping(value = "/maquinas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(MaquinaFilterDTO filter) {
		List<MaquinaDTO> listFilter = super.service.listFilter(new MaquinaFilter(filter));
		JSONResponse response = new JSONResponse();
		response.setData(listFilter);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/maquinas/autocomplete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listAutocomplete(MaquinaFilterDTO filter) {
		filter.setAsc(true);
		filter.setIdx("nombre");
		List<MaquinaDTO> listFilter = super.service.listFilter(new MaquinaFilter(filter));
		List<ComboDTO> clientes = new ArrayList<ComboDTO>();
		listFilter.forEach(x -> clientes.add(new ComboDTO(x.getNombre(), x.getId().toString())));
		JSONResponse response = new JSONResponse();
		response.setData(clientes);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/maquina", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> create(@RequestBody MaquinaDTO maquinaDTO) throws BusinessException {
		maquinaDTO.setId(null);
		MaquinaDTO saved = this.service.save(maquinaDTO);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/maquina/{idMaquina}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> update(@PathVariable("idMaquina") Long idMaquina,
			@RequestBody MaquinaDTO maquinaDTO) throws BusinessException {
		maquinaDTO.setId(idMaquina);
		MaquinaDTO updated = this.service.update(maquinaDTO);
		JSONResponse response = new JSONResponse();
		response.setData(updated);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/maquina/{idMaquina}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> get(@PathVariable("idMaquina") Long idMaquina) {
		MaquinaDTO maquinaDTO = this.service.get(idMaquina);
		JSONResponse response = new JSONResponse();
		response.setData(maquinaDTO);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@Resource(name = "maquinaEPService")
	public void setService(MaquinaEPService maquinaEPService) {
		super.service = maquinaEPService;
	}

}