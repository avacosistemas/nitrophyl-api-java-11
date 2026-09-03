package ar.com.avaco.nitrophyl.service.fabricacion;

import java.util.List;
import java.util.Set;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompraDetalle;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenFabricacion;
import ar.com.avaco.nitrophyl.ws.dto.ListadoOrdenFabricacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenFabricacionFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;

public interface OrdenFabricacionService extends NJService<Long, OrdenFabricacion> {

	void generarOrdenes(Set<OrdenCompraDetalle> detalle);

	PageDTO<ListadoOrdenFabricacionDTO> listFilterCountCustom(OrdenFabricacionFilterDTO ordenFabricacionFilterDTO);

	boolean hayPendientes(Long idOrdenFabricacion);

	List<OrdenFabricacion> listByIds(List<Long> ids);

	void reordenar(Long idOrdenFabricacion, Integer nuevaPosicion);

	Integer obtenerUltimaPosicion(Long idSector, Long idMaquina);

	void reordenarGrupo(Long idSector, Long idMaquina, Long idOrdenFabricacion);

}
