package ar.com.avaco.nitrophyl.service.pieza;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.ws.dto.PiezaFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaGrillaDTO;

public interface PiezaService extends NJService<Long, Pieza> {

	Pieza getVigenteByCodigoInterno(String codigoInterno);

	List<PiezaGrillaDTO> listGrilla(PiezaFilterDTO pfdto);

}
