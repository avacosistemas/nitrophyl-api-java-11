package ar.com.avaco.nitrophyl.ws.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoInsumo;
import ar.com.avaco.nitrophyl.service.pieza.TipoInsumoService;
import ar.com.avaco.nitrophyl.ws.dto.ComboDTO;
import ar.com.avaco.nitrophyl.ws.dto.TipoInsumoDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("tipoInsumoEPService")
public class TipoInsumoEPServiceImpl extends
		CRUDAuditableEPBaseService<Long, TipoInsumoDTO, TipoInsumo, TipoInsumoService> implements TipoInsumoEPService {

	public TipoInsumoEPServiceImpl() {
		super(TipoInsumo.class, TipoInsumoDTO.class);
	}
	
	@Override
	protected TipoInsumoDTO convertToDto(TipoInsumo entity) {
		TipoInsumoDTO dto = super.convertToDto(entity);
		TipoInsumo padre = entity.getPadre();
		if (padre != null)
			dto.setIdPadre(padre.getId());
		return dto;
	}
	
	@Override
	protected TipoInsumo convertToEntity(TipoInsumoDTO dto) {
		TipoInsumo entity = super.convertToEntity(dto);
		if (dto.getIdPadre() != null) {
			entity.setPadre(TipoInsumo.ofId(dto.getIdPadre()));
		}
		return entity;
	}

	@Override
	@Resource(name = "tipoInsumoService")
	protected void setService(TipoInsumoService service) {
		this.service = service;
	}

	@Override
	public List<ComboDTO> listHijos() {
		List<TipoInsumo> tipos = this.service.listHijos();
		List<ComboDTO> combo = new ArrayList<ComboDTO>();
		tipos.forEach(tipo -> combo.add(new ComboDTO(tipo.getNombreCompleto(), tipo.getId().toString())));
		return combo;
	}

}
