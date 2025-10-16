package ar.com.avaco.nitrophyl.ws.service;

import java.util.List;

import ar.com.avaco.nitrophyl.ws.dto.ComboDTO;
import ar.com.avaco.nitrophyl.ws.dto.TipoInsumoDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPService;

public interface TipoInsumoEPService extends CRUDAuditableEPService<Long, TipoInsumoDTO> {

	List<ComboDTO> listHijos();

}
