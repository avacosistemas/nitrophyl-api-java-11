package ar.com.avaco.nitrophyl.service.fabricacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.repository.fabricacion.OrdenFabricacionRepository;
import ar.com.avaco.nitrophyl.ws.dto.DetalleMaquinaOrdenTrabajoDTO;
import ar.com.avaco.nitrophyl.ws.dto.ResumenMaquinaOrdenTrabajoDTO;

@Service("monitorFabricaService")
public class MonitorFabricaServiceImpl implements MonitorFabricaService {

	@Autowired
	private OrdenFabricacionRepository ordenFabricacionRepository;

	@Override
	public List<ResumenMaquinaOrdenTrabajoDTO> obtenerResumen() {
		return this.ordenFabricacionRepository.obtenerResumen();
	}
	
	@Override
	public List<DetalleMaquinaOrdenTrabajoDTO> obtenerOrdenesTrabajo(Long idSector, Long idMaquina) {
		return this.ordenFabricacionRepository.obtenerOrdenesTrabajo(idSector, idMaquina);
	}

	
	
}
