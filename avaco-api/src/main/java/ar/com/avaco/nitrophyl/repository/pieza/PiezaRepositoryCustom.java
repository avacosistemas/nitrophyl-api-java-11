package ar.com.avaco.nitrophyl.repository.pieza;

import java.util.List;

import ar.com.avaco.nitrophyl.ws.dto.PiezaFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaGrillaDTO;

public interface PiezaRepositoryCustom {

	List<PiezaGrillaDTO> listGrilla(PiezaFilterDTO filtro);

	
}
