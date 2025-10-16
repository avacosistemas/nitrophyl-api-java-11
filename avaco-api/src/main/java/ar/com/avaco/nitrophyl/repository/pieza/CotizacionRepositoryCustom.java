package ar.com.avaco.nitrophyl.repository.pieza;

import ar.com.avaco.nitrophyl.ws.dto.CotizacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.CotizacionFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;

public interface CotizacionRepositoryCustom {

	PageDTO<CotizacionDTO> list(CotizacionFilterDTO filter);

}