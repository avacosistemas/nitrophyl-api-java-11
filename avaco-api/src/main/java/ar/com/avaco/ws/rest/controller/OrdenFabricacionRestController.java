package ar.com.avaco.ws.rest.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.nitrophyl.ws.dto.ListadoOrdenFabricacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenFabricacionAsignacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenFabricacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenFabricacionEntregaDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenFabricacionFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.dto.ordenfabricacion.OrdenTrabajoResponseDTO;
import ar.com.avaco.nitrophyl.ws.dto.ordenfabricacion.OrdenTrabajoResumenDTO;
import ar.com.avaco.nitrophyl.ws.service.OrdenFabricacionEPService;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class OrdenFabricacionRestController
		extends AbstractAuditableDTORestController<OrdenFabricacionDTO, Long, OrdenFabricacionEPService> {

	@RequestMapping(value = "/ordenFabricacion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listOrdenesFabricacion(OrdenFabricacionFilterDTO ordenFabricacionFilterDTO) {
		PageDTO<ListadoOrdenFabricacionDTO> listFilterCount = this.service.listFilterCount(ordenFabricacionFilterDTO);
		return returnOK(listFilterCount);
	}

	@RequestMapping(value = "/ordenFabricacion/{idOrdenCompra}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listOrdenesFabricacion(@PathVariable Long idOrdenCompra) {
		this.service.create(idOrdenCompra);
		return returnOK();
	}

	@RequestMapping(value = "/ordenFabricacion/asignar/{idOrdenFabricacion}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> asignar(@PathVariable Long idOrdenFabricacion,
			@RequestBody OrdenFabricacionAsignacionDTO asignacion) {
		this.service.asignar(idOrdenFabricacion, asignacion);
		return returnOK();
	}

	@RequestMapping(value = "/ordenFabricacion/reordenar/{idOrdenFabricacion}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> asignar(@PathVariable Long idOrdenFabricacion,
			Integer nuevaPosicion) {
		this.service.reordenar(idOrdenFabricacion, nuevaPosicion);
		return returnOK();
	}

	@RequestMapping(value = "/ordenFabricacion/registrarEntrega/{idOrdenFabricacion}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> registrarEntrega(@PathVariable Long idOrdenFabricacion,
			@RequestBody OrdenFabricacionEntregaDTO entrega) {
		this.service.registrarEntrega(idOrdenFabricacion, entrega);
		return returnOK();
	}

	@RequestMapping(value = "/ordenFabricacion/ordenTrabajo/descargar/{idOrdenFabricacion}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> descargarOrdenTrabajo(@PathVariable Long idOrdenFabricacion) throws Exception {
		OrdenTrabajoResponseDTO ot = this.service.generarOrdenTrabajo(idOrdenFabricacion);
		return returnOK(ot);
	}

	@RequestMapping(value = "/ordenFabricacion/ordenTrabajo/resumen", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> generarResumenOrdenesTrabajo(@RequestBody Map<String, List<Long>> body)
			throws Exception {
		List<Long> ids = body.get("ids");
		Map<String, List<OrdenTrabajoResumenDTO>> resumen = this.service.generarResumen(ids);
		return returnOK(resumen);
	}

	@Resource(name = "ordenFabricacionEPService")
	public void setService(OrdenFabricacionEPService ordenFabricacionEPService) {
		super.service = ordenFabricacionEPService;
	}

}