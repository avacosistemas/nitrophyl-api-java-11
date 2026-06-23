package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.nitrophyl.domain.entities.fabricacion.SectorFabrica;
import ar.com.avaco.nitrophyl.service.produccion.SectorFabricaService;
import ar.com.avaco.nitrophyl.ws.dto.SectorFabricaDTO;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Transactional
@Service("sectorFabricaEPService")
public class SectorFabricaEPServiceImpl extends CRUDEPBaseService<Long, SectorFabricaDTO, SectorFabrica, SectorFabricaService>
		implements SectorFabricaEPService {


	@Override
	@Resource(name = "sectorFabricaService")
	protected void setService(SectorFabricaService service) {
		this.service = service;
	}

	@Override
	protected SectorFabrica convertToEntity(SectorFabricaDTO dto) {
		SectorFabrica sf = new SectorFabrica();
		sf.setId(dto.getId());
		sf.setNombre(dto.getNombre());
		return sf;
	}

	@Override
	protected SectorFabricaDTO convertToDto(SectorFabrica entity) {
		SectorFabricaDTO dto = new SectorFabricaDTO();
		dto.setId(entity.getId());
		dto.setNombre(entity.getNombre());
		return dto;
	}

}