
package ar.com.avaco.nitrophyl.ws.service;

import java.util.List;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.nitrophyl.ws.dto.MoldeBocaDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeBocaListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeClienteDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeDimensionListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeFotoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeFotoListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeObservacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldePlanoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldePlanoListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeRegistroDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPService;

public interface MoldeBocaEPService extends CRUDAuditableEPService<Long, MoldeBocaDTO> {

	List<MoldeBocaDTO> listByMoldeId(Long idMolde);

}
