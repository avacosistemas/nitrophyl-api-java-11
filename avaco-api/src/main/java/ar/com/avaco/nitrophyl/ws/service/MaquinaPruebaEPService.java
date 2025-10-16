package ar.com.avaco.nitrophyl.ws.service;

import java.util.List;

import ar.com.avaco.nitrophyl.ws.dto.MaquinaPruebaDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface MaquinaPruebaEPService extends CRUDEPService<Long, MaquinaPruebaDTO> {

	List<MaquinaPruebaDTO> listPruebasByMaquina(Long idMaquina);

}
