
package ar.com.avaco.nitrophyl.ws.service;

import java.time.LocalDate;

import ar.com.avaco.nitrophyl.ws.dto.PiezaMovimientoStockDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPService;

public interface PiezaMovimientoStockEPService extends CRUDAuditableEPService<Long, PiezaMovimientoStockDTO> {

	void registrarIngresoManual(Long idProducto, Integer cantidad, LocalDate fecha, String observacion);

}
