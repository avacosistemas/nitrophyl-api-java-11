
package ar.com.avaco.nitrophyl.ws.service;

import java.util.List;

import ar.com.avaco.nitrophyl.ws.dto.MoldeBocaDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPService;

public interface MoldeBocaEPService extends CRUDAuditableEPService<Long, MoldeBocaDTO> {

	List<MoldeBocaDTO> listByMoldeId(Long idMolde);

}
