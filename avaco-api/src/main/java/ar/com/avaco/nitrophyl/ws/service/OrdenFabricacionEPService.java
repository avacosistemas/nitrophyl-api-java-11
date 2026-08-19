package ar.com.avaco.nitrophyl.ws.service;

import java.util.List;
import java.util.Map;

import ar.com.avaco.nitrophyl.ws.dto.ListadoOrdenFabricacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenFabricacionAsignacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenFabricacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenFabricacionEntregaDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenFabricacionFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.dto.ordenfabricacion.OrdenTrabajoResponseDTO;
import ar.com.avaco.nitrophyl.ws.dto.ordenfabricacion.OrdenTrabajoResumenDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPService;

public interface OrdenFabricacionEPService extends CRUDAuditableEPService<Long, OrdenFabricacionDTO> {

	PageDTO<ListadoOrdenFabricacionDTO> listFilterCount(OrdenFabricacionFilterDTO ordenFabricacionFilterDTO);

	void create(Long idOrdenCompra);

	void asignar(Long idOrdenFabricacion, OrdenFabricacionAsignacionDTO asignacion);

	void registrarEntrega(Long idOrdenFabricacion, OrdenFabricacionEntregaDTO entrega);

	OrdenTrabajoResponseDTO generarOrdenTrabajo(Long idOrdenFabricacion);

	Map<String, List<OrdenTrabajoResumenDTO>> generarResumen(List<Long> ids);

}