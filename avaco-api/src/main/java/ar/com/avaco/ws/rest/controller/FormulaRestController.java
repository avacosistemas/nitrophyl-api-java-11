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
import ar.com.avaco.nitrophyl.ws.dto.FormulaDTO;
import ar.com.avaco.nitrophyl.ws.dto.FormulaFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.RevisionParametrosDTO;
import ar.com.avaco.nitrophyl.ws.service.FormulaEPService;
import ar.com.avaco.nitrophyl.ws.service.filter.FormulaFilter;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class FormulaRestController extends AbstractDTORestController<FormulaDTO, Long, FormulaEPService> {

	@Resource(name = "formulaEPService")
	public void setService(FormulaEPService formulaEPService) {
		super.service = formulaEPService;
	}

	@RequestMapping(value = "/formula", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(FormulaFilterDTO filter) {
		List<FormulaDTO> listFilter = super.service.listFilter(new FormulaFilter(filter));
		JSONResponse response = new JSONResponse();
		response.setData(listFilter);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/formula/autocomplete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listAutocomplete(FormulaFilterDTO filter) {
		filter.setAsc(true);
		filter.setIdx("nombre");
		List<FormulaDTO> listFilter = super.service.listFilter(new FormulaFilter(filter));
		List<ComboDTO> formulas = new ArrayList<ComboDTO>();
		listFilter.forEach(x -> formulas.add(new ComboDTO(x.getLabelCombo(), x.getId().toString())));
		JSONResponse response = new JSONResponse();
		response.setData(formulas);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/formula/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listCount(FormulaFilterDTO filter) {
		int listCount = super.service.listCount(new FormulaFilter(filter));
		JSONResponse response = new JSONResponse();
		response.setData(listCount);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/formula", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> create(@RequestBody FormulaDTO formulaDTO) throws BusinessException {
		formulaDTO.setId(null);
		FormulaDTO saved = this.service.save(formulaDTO);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/formula/{idFormula}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> update(@PathVariable("idFormula") Long idFormula,
			@RequestBody FormulaDTO formulaDTO) throws BusinessException {
		formulaDTO.setId(idFormula);
		FormulaDTO cloned = this.service.clone(formulaDTO);
		JSONResponse response = new JSONResponse();
		response.setData(cloned);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/formula/marcarrevision/{idFormula}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> marcarrevision(@PathVariable("idFormula") Long idFormula) throws BusinessException {
		RevisionParametrosDTO dto = this.service.marcarRevision(idFormula);
		JSONResponse response = new JSONResponse();
		response.setData(dto);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/formula/{idFormula}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> get(@PathVariable("idFormula") Long idFormula) {
		FormulaDTO formulaDTO = this.service.get(idFormula);
		JSONResponse response = new JSONResponse();
		response.setData(formulaDTO);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

}