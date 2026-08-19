package ar.com.avaco.ws.rest.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaMovimientoStockDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaMovimientoStockFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaMovimientoStockListadoDTO;
import ar.com.avaco.nitrophyl.ws.service.PiezaMovimientoStockEPService;
import ar.com.avaco.nitrophyl.ws.service.filter.PiezaMovimientoStockFilter;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class PiezaMovimientoStockRestController
		extends AbstractAuditableDTORestController<PiezaMovimientoStockDTO, Long, PiezaMovimientoStockEPService> {

	@RequestMapping(value = "/pieza/stock/movimiento", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> piezaStock(PiezaMovimientoStockFilterDTO filterDTO) {
		PageDTO<PiezaMovimientoStockListadoDTO> listFilterCount = this.service
				.listFilterCount(new PiezaMovimientoStockFilter(filterDTO), PiezaMovimientoStockListadoDTO.class);
		return OK(listFilterCount);
	}

	@RequestMapping(value = "/pieza/stock/movimiento/ingreso/manual", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> ingresoManual(@RequestBody PiezaMovimientoStockDTO dto) {
		this.service.registrarIngresoManual(dto.getIdPieza(), dto.getCantidad(), dto.getFecha(), dto.getObservacion());
		return OK(true);
	}

	@Resource(name = "piezaMovimientoStockEPService")
	public void setService(PiezaMovimientoStockEPService piezaMovimientoStockEPService) {
		super.service = piezaMovimientoStockEPService;
	}

}