package ar.com.avaco.nitrophyl.service.pieza;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Cotizacion;
import ar.com.avaco.nitrophyl.ws.dto.CotizacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.CotizacionFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;

public interface CotizacionService extends NJService<Long, Cotizacion> {

	Cotizacion getCotizacionVigente(Long idPiezaCliente);

	PageDTO<CotizacionDTO> listCotizaciones(CotizacionFilterDTO filter);
	
}