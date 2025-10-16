package ar.com.avaco.nitrophyl.repository.molde;

import java.util.List;

import ar.com.avaco.nitrophyl.ws.dto.MoldeFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeListadoDTO;

public interface MoldeRepositoryCustom {

	List<MoldeListadoDTO> list(MoldeFilterDTO filtro);

}
