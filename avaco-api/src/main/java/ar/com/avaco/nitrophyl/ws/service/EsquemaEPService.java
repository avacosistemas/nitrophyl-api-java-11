package ar.com.avaco.nitrophyl.ws.service;

import java.util.List;

import ar.com.avaco.nitrophyl.ws.dto.EsquemaDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPService;

public interface EsquemaEPService extends CRUDAuditableEPService<Long, EsquemaDTO> {

	void reordenar(Long idEsquema, Integer posicion);

	List<EsquemaDTO> listEsquemas(Long idProceso);

}