package ar.com.avaco.nitrophyl.ws.service;

import java.util.List;

import ar.com.avaco.nitrophyl.ws.dto.ConfiguracionPruebaDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface ConfiguracionPruebaEPService extends CRUDEPService<Long, ConfiguracionPruebaDTO> {

	List<ConfiguracionPruebaDTO> list(Long idFormula);

	List<ConfiguracionPruebaDTO> listVigentesByLote(Long idLote);

	void setarVigente(Long idConfiguracionPrueba);

}
