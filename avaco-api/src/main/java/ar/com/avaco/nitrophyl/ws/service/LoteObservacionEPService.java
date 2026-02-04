package ar.com.avaco.nitrophyl.ws.service;

import ar.com.avaco.nitrophyl.ws.dto.LoteObservacionDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPService;

public interface LoteObservacionEPService extends CRUDAuditableEPService<Long, LoteObservacionDTO> {

	void updateCheck(Long idLoteObservacion);

}
