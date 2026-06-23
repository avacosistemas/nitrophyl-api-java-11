package ar.com.avaco.nitrophyl.repository.fabricacion;

import ar.com.avaco.nitrophyl.ws.dto.ListadoOrdenFabricacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenFabricacionFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;

public interface OrdenFabricacionRepositoryCustom {

	Long obtenerSiguienteNumero(Integer anio);

	PageDTO<ListadoOrdenFabricacionDTO> listFilterCount(OrdenFabricacionFilterDTO filter);

}
