package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.domain.entities.maquina.EstadoMaquina;
import ar.com.avaco.nitrophyl.domain.entities.maquina.Maquina;
import ar.com.avaco.nitrophyl.service.maquina.MaquinaService;
import ar.com.avaco.nitrophyl.ws.dto.MaquinaDTO;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("maquinaEPService")
public class MaquinaEPServiceImpl extends CRUDEPBaseService<Long, MaquinaDTO, Maquina, MaquinaService>
		implements MaquinaEPService {

	@Override
	@Resource(name = "maquinaService")
	protected void setService(MaquinaService service) {
		this.service = service;
	}

	@Override
	protected Maquina convertToEntity(MaquinaDTO dto) {
		Maquina maquina = new Maquina();
		maquina.setEstado(EstadoMaquina.valueOf(dto.getEstado()));
		maquina.setId(dto.getId());
		maquina.setNombre(dto.getNombre());
		maquina.setObservacionesReporte(dto.getObservacionesReporte());
		maquina.setPosicion(dto.getPosicion());
		maquina.setVersionable(dto.isVersionable());
		maquina.setNorma(dto.getNorma());
		return maquina;
	}

	@Override
	protected MaquinaDTO convertToDto(Maquina entity) {
		MaquinaDTO dto = new MaquinaDTO();
		dto.setEstado(entity.getEstado().name());
		dto.setId(entity.getId());
		dto.setNombre(entity.getNombre());
		dto.setObservacionesReporte(entity.getObservacionesReporte());
		dto.setPosicion(entity.getPosicion());
		dto.setVersionable(entity.isVersionable());
		dto.setNorma(entity.getNorma());
		return dto;
	}

	@Override
	public MaquinaDTO update(MaquinaDTO dto) throws BusinessException {
		Maquina maquina = this.service.get(dto.getId());
		Maquina convertToEntity = convertToEntity(dto);
		convertToEntity.getPruebas().addAll(maquina.getPruebas());
		return convertToDto(this.service.update(convertToEntity));
	}

}
