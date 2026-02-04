package ar.com.avaco.ws.rest.controller;

import java.util.Comparator;
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
import ar.com.avaco.nitrophyl.ws.dto.LoteObservacionDTO;
import ar.com.avaco.nitrophyl.ws.service.LoteObservacionEPService;
import ar.com.avaco.ws.rest.dto.ErrorResponse;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class LoteObservacionRestController
		extends AbstractAuditableDTORestController<LoteObservacionDTO, Long, LoteObservacionEPService> {

	@Override
	@Resource(name = "loteObservacionEPService")
	public void setService(LoteObservacionEPService service) {
		super.service = service;
	}

	@RequestMapping(value = "/lote/observacion/{idLote}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> read(@PathVariable("idLote") Long idLote) throws BusinessException {
		List<LoteObservacionDTO> list = this.service.listEq("lote.id", idLote);
		list.stream().sorted(new Comparator<LoteObservacionDTO>() {
			@Override
			public int compare(LoteObservacionDTO o1, LoteObservacionDTO o2) {
				return o1.getFechaCreacion().compareTo(o2.getFechaCreacion());
			}
		});
		JSONResponse response = new JSONResponse();
		response.setData(list);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/lote/observacion", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> create(@RequestBody LoteObservacionDTO loteObservacionDTO)
			throws BusinessException {
		JSONResponse response = new JSONResponse();
		response.setStatus(JSONResponse.OK);
		try {
			LoteObservacionDTO saved = this.service.save(loteObservacionDTO);
			response.setData(saved);
		} catch (BusinessException e) {
			ErrorResponse eresp = new ErrorResponse();
			eresp.setStatus(JSONResponse.ERROR);
			eresp.setError(e.getMessage());
			response = eresp;
		}
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/lote/observacion/check/{idLoteObservacion}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> create(@PathVariable Long idLoteObservacion) throws BusinessException {
		JSONResponse response = new JSONResponse();
		response.setStatus(JSONResponse.OK);
		this.service.updateCheck(idLoteObservacion);
		response.setData(true);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

}