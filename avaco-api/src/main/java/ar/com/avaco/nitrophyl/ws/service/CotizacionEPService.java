package ar.com.avaco.nitrophyl.ws.service;

import ar.com.avaco.nitrophyl.ws.dto.CotizacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.CotizacionFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPService;

public interface CotizacionEPService extends CRUDAuditableEPService<Long, CotizacionDTO> {

	CotizacionDTO getCotizacionVigente(Long idPiezaCliente);

	PageDTO<CotizacionDTO> list(CotizacionFilterDTO filterDTO);
	
}