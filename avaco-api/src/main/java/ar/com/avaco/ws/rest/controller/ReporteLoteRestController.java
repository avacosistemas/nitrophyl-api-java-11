package ar.com.avaco.ws.rest.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.ws.dto.ArchivoDTO;
import ar.com.avaco.nitrophyl.ws.dto.LoteDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.dto.ReporteEnsayoLotePorMaquinaDTO;
import ar.com.avaco.nitrophyl.ws.dto.ReporteEnsayoLotePorMaquinaFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.ReporteLoteClienteDTO;
import ar.com.avaco.nitrophyl.ws.service.LoteEPService;
import ar.com.avaco.ws.rest.dto.ErrorResponse;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class ReporteLoteRestController extends AbstractDTORestController<LoteDTO, Long, LoteEPService> {

	@Resource(name = "loteEPService")
	public void setService(LoteEPService loteEPService) {
		super.service = loteEPService;
	}

	@RequestMapping(value = "/loteReporte", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> generarReporte(ReporteLoteClienteDTO dto) {
		JSONResponse response = new JSONResponse();
		response.setStatus(JSONResponse.OK);
		try {
			ArchivoDTO archivo = this.service.generarReporteLoteCliente(dto.getIdLote(), dto.getIdCliente(),
					dto.getObservacionesInforme());
			response.setData(archivo);
		} catch (BusinessException e) {
			ErrorResponse eresp = new ErrorResponse();
			eresp.setStatus(JSONResponse.ERROR);
			eresp.setError(e.getMessage());
			response = eresp;
		}
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/loteReporte/enviar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> enviarReporte(@RequestBody ReporteLoteClienteDTO dto) {
		JSONResponse response = new JSONResponse();
		response.setStatus(JSONResponse.OK);
		try {
			this.service.enviarReporte(dto.getIdLote(), dto.getIdCliente(), dto.getArchivo(), dto.getNombreArchivo(),
					dto.getObservaciones(), dto.getObservacionesInforme());
			response.setData(true);
		} catch (Exception e) {
			ErrorResponse eresp = new ErrorResponse();
			eresp.setStatus(JSONResponse.ERROR);
			eresp.setError(e.getMessage());
			response = eresp;
		}
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/lotePorMaquinaReporte", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> generarReporte(ReporteEnsayoLotePorMaquinaFilterDTO filtro) {
		JSONResponse response = new JSONResponse();
		response.setStatus(JSONResponse.OK);
		try {
			PageDTO<ReporteEnsayoLotePorMaquinaDTO> generarReporteEnsayoLotePorMaquina = this.service
					.generarReporteEnsayoLotePorMaquina(filtro);
			response.setData(generarReporteEnsayoLotePorMaquina);
		} catch (Exception e) {
			ErrorResponse eresp = new ErrorResponse();
			eresp.setStatus(JSONResponse.ERROR);
			eresp.setError(e.getMessage());
			response = eresp;
		}
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

}