package ar.com.avaco.nitrophyl.ws.service;

import ar.com.avaco.nitrophyl.ws.dto.ArchivoDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaPlanoDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPService;

public interface PiezaPlanoEPService extends CRUDAuditableEPService<Long, PiezaPlanoDTO> {

	ArchivoDTO getPlanoArchivo(Long id);

}