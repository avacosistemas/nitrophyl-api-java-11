package ar.com.avaco.ws.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.nitrophyl.service.fabricacion.MonitorFabricaService;
import ar.com.avaco.nitrophyl.ws.dto.DetalleMaquinaOrdenTrabajoDTO;
import ar.com.avaco.nitrophyl.ws.dto.ResumenMaquinaOrdenTrabajoDTO;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController("monitorFabrica")
public class MonitorFabricaRestController {

	@Autowired
	private MonitorFabricaService monitorFabricaService;
	
	@RequestMapping(value = "/monitorFabrica/resumenOTMaquinaSector", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> resumenOTMaquinaSector() throws Exception {
		List<ResumenMaquinaOrdenTrabajoDTO> obtenerResumen = this.monitorFabricaService.obtenerResumen();
		JSONResponse response = new JSONResponse();
		response.setData(obtenerResumen);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/monitorFabrica/detalleOTMaquinaSector", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> detalleOTMaquinaSector(@RequestParam Long idSector, @RequestParam(required = false)  Long idMaquina) throws Exception {
		List<DetalleMaquinaOrdenTrabajoDTO> ordenesTrabajo = this.monitorFabricaService.obtenerOrdenesTrabajo(idSector, idMaquina);
		JSONResponse response = new JSONResponse();
		response.setData(ordenesTrabajo);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}


}