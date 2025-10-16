package ar.com.avaco.nitrophyl.ws.service;

import java.util.List;

import ar.com.avaco.nitrophyl.ws.dto.EnsayoResultadoDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface EnsayoResultadoEPService extends CRUDEPService<Long, EnsayoResultadoDTO> {

	List<EnsayoResultadoDTO> listByEnsayo(Long idEnsayo);

}
