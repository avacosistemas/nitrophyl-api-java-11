package ar.com.avaco.nitrophyl.ws.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.maquina.MaquinaPrueba;
import ar.com.avaco.nitrophyl.service.maquina.MaquinaPruebaService;
import ar.com.avaco.nitrophyl.ws.dto.MaquinaPruebaDTO;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("maquinaPruebaEPService")
public class MaquinaPruebaEPServiceImpl
		extends CRUDEPBaseService<Long, MaquinaPruebaDTO, MaquinaPrueba, MaquinaPruebaService>
		implements MaquinaPruebaEPService {

	@Override
	@Resource(name = "maquinaPruebaService")
	protected void setService(MaquinaPruebaService service) {
		this.service = service;
	}

	@Override
	protected MaquinaPrueba convertToEntity(MaquinaPruebaDTO dto) {
		MaquinaPrueba maquina = new MaquinaPrueba();
		maquina.setId(dto.getId());
		maquina.setNombre(dto.getNombre());
		maquina.setIdMaquina(dto.getIdMaquina());
		maquina.setPosicion(dto.getPosicion());
		return maquina;
	}

	@Override
	protected MaquinaPruebaDTO convertToDto(MaquinaPrueba entity) {
		MaquinaPruebaDTO dto = new MaquinaPruebaDTO();
		dto.setId(entity.getId());
		dto.setNombre(entity.getNombre());
		dto.setIdMaquina(entity.getIdMaquina());
		dto.setPosicion(entity.getPosicion());
		return dto;
	}

	@Override
	public List<MaquinaPruebaDTO> listPruebasByMaquina(Long idMaquina) {
		List<MaquinaPrueba> listByMaquina = this.service.listByMaquina(idMaquina);
		return convertToDtos(listByMaquina);
	}

}
