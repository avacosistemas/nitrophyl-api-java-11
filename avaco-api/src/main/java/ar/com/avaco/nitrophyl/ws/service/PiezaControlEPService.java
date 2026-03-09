
package ar.com.avaco.nitrophyl.ws.service;

import java.util.List;

import ar.com.avaco.nitrophyl.ws.dto.PiezaControlDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPService;

public interface PiezaControlEPService extends CRUDAuditableEPService<Long, PiezaControlDTO> {

	List<PiezaControlDTO> listControlesConfigurados(Long idPieza);

}
