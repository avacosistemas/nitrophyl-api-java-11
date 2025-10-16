package ar.com.avaco.nitrophyl.ws.service;

import ar.com.avaco.nitrophyl.ws.dto.DesmoldantepostcuraPUTDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeoPUTDTO;
import ar.com.avaco.nitrophyl.ws.dto.ProcesoDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPService;

public interface ProcesoEPService extends CRUDAuditableEPService<Long, ProcesoDTO> {

	void updateDesmoldantePostcura(Long id, DesmoldantepostcuraPUTDTO dto);

	void updateMoldeo(Long id, MoldeoPUTDTO dto);

}