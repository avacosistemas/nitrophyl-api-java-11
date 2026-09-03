package ar.com.avaco.nitrophyl.service.fabricacion;

import java.util.List;

import ar.com.avaco.nitrophyl.ws.dto.DetalleMaquinaOrdenTrabajoDTO;
import ar.com.avaco.nitrophyl.ws.dto.ResumenMaquinaOrdenTrabajoDTO;

public interface MonitorFabricaService {

	List<ResumenMaquinaOrdenTrabajoDTO> obtenerResumen();

	List<DetalleMaquinaOrdenTrabajoDTO> obtenerOrdenesTrabajo(Long idSector, Long idMaquina);

}