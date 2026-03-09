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
import ar.com.avaco.nitrophyl.ws.dto.LoteAprobarDTO;
import ar.com.avaco.nitrophyl.ws.dto.LoteDTO;
import ar.com.avaco.nitrophyl.ws.dto.LoteFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.LoteRechazarDTO;
import ar.com.avaco.nitrophyl.ws.service.LoteEPService;
import ar.com.avaco.nitrophyl.ws.service.filter.LoteFilter;
import ar.com.avaco.ws.rest.dto.ErrorResponse;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class LoteRestController extends AbstractDTORestController<LoteDTO, Long, LoteEPService> {

	@Resource(name = "loteEPService")
	public void setService(LoteEPService loteEPService) {
		super.service = loteEPService;
	}

	@RequestMapping(value = "/lote/{idLote}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> read(@PathVariable("idLote") Long idLote) throws BusinessException {
		return super.get(idLote);
	}

	@RequestMapping(value = "/lote/{idLote}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> delete(@PathVariable("idLote") Long idLote) throws BusinessException {
		return super.delete(idLote);
	}

	@RequestMapping(value = "/lote", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(LoteFilterDTO filter) {
		List<LoteDTO> listFilter = super.service.listFilter(new LoteFilter(filter));
		return OK(listFilter);
	}

	@RequestMapping(value = "/lotes/autocomplete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listLotesAutocomplete(LoteFilterDTO filterDTO) throws Exception {
		filterDTO.setAsc(true);
		filterDTO.setIdx("nroLote");
		List<LoteDTO> listFilter = super.service.listFilter(new LoteFilter(filterDTO));
		List<ComboDTO> lotes = new ArrayList<ComboDTO>();
		listFilter.forEach(x -> lotes.add(new ComboDTO(x.getNroLote(), x.getId().toString())));
		JSONResponse response = new JSONResponse();
		response.setData(lotes);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/lote/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listCount(LoteFilterDTO filter) {
		int listCount = super.service.listCount(new LoteFilter(filter));
		JSONResponse response = new JSONResponse();
		response.setData(listCount);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/lote/monitor", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listMonitor(LoteFilterDTO filter) {
		filter.setAsc(false);
		filter.setIdx("fechaEstado");
		filter.setExcluirPendientes(false);
		List<LoteDTO> listFilter = super.service.listFilter(new LoteFilter(filter));
		return OK(listFilter);
	}

	@RequestMapping(value = "/lote/monitor/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listMonitorCount(LoteFilterDTO filter) {
		filter.setAsc(false);
		filter.setIdx("fechaEstado");
		filter.setExcluirPendientes(false);
		int listCount = super.service.listCount(new LoteFilter(filter));
		JSONResponse response = new JSONResponse();
		response.setData(listCount);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/lote", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> create(@RequestBody LoteDTO loteDTO) throws BusinessException {
		JSONResponse response = new JSONResponse();
		response.setStatus(JSONResponse.OK);
		try {
			LoteDTO saved = this.service.save(loteDTO);
			response.setData(saved);
		} catch (BusinessException e) {
			ErrorResponse eresp = new ErrorResponse();
			eresp.setStatus(JSONResponse.ERROR);
			eresp.setError(e.getMessage());
			response = eresp;
		}
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/lote/aprobar/{idLote}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> aprobar(@PathVariable("idLote") Long idLote, @RequestBody LoteAprobarDTO dto)
			throws BusinessException {
		this.service.aprobar(idLote, dto.getEstado(), dto.getObservaciones());
		JSONResponse response = new JSONResponse();
		response.setData(dto);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/lote/rechazar/{idLote}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> rechazar(@PathVariable("idLote") Long idLote, @RequestBody LoteRechazarDTO dto)
			throws BusinessException {
		this.service.rechazar(idLote, dto.getObservaciones());
		JSONResponse response = new JSONResponse();
		response.setData(dto);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/lote/update/{idLote}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> update(@PathVariable("idLote") Long idLote, @RequestBody LoteDTO loteDTO)
			throws BusinessException {
		JSONResponse response = new JSONResponse();
		response.setStatus(JSONResponse.OK);
		try {
			super.update(idLote, loteDTO);
			response.setData(loteDTO);
		} catch (BusinessException e) {
			ErrorResponse eresp = new ErrorResponse();
			eresp.setStatus(JSONResponse.ERROR);
			eresp.setError(e.getMessage());
			response = eresp;
		}
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/lote/revisiones", method = RequestMethod.GET)
	public ResponseEntity<JSONResponse> revisiones() throws BusinessException {
		JSONResponse response = new JSONResponse();
		try {
			this.service.revisiones();
			response.setStatus(JSONResponse.OK);
		} catch (Exception e) {
			ErrorResponse eresp = new ErrorResponse();
			eresp.setStatus(JSONResponse.ERROR);
			eresp.setError(e.getMessage());
			response = eresp;
		}
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/lote/hasEnsayos/{idLote}", method = RequestMethod.GET)
	public ResponseEntity<JSONResponse> hasEnsayos(@PathVariable("idLote") Long idLote) throws BusinessException {
		JSONResponse response = new JSONResponse();
		try {
			Boolean hasEnsayos = this.service.hasEnsayos(idLote);
			response.setData(hasEnsayos);
			response.setStatus(JSONResponse.OK);
		} catch (Exception e) {
			ErrorResponse eresp = new ErrorResponse();
			eresp.setStatus(JSONResponse.ERROR);
			eresp.setError(e.getMessage());
			response = eresp;
		}
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

}