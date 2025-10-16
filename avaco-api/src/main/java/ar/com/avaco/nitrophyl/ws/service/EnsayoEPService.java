package ar.com.avaco.nitrophyl.ws.service;

import java.util.List;

import ar.com.avaco.nitrophyl.ws.dto.EnsayoDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface EnsayoEPService extends CRUDEPService<Long, EnsayoDTO> {

	List<EnsayoDTO> listByLote(Long idLote);

}
