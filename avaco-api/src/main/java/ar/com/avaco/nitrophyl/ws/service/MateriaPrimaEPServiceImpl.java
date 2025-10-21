package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.MateriaPrima;
import ar.com.avaco.nitrophyl.domain.entities.pieza.UnidadMedidaMateriaPrima;
import ar.com.avaco.nitrophyl.service.pieza.MateriaPrimaService;
import ar.com.avaco.nitrophyl.ws.dto.MateriaPrimaDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("materiaPrimaEPService")
public class MateriaPrimaEPServiceImpl
		extends CRUDAuditableEPBaseService<Long, MateriaPrimaDTO, MateriaPrima, MateriaPrimaService>
		implements MateriaPrimaEPService {

	public MateriaPrimaEPServiceImpl() {
		super(MateriaPrima.class, MateriaPrimaDTO.class);
	}

	@Override
	protected MateriaPrima convertToEntityForUpdate(MateriaPrimaDTO dto) {
		MateriaPrima materiaPrima = this.service.get(dto.getId());
		materiaPrima.setNombre(dto.getNombre());
		return materiaPrima;
	}

	@Override
	protected MateriaPrima convertToEntityForSave(MateriaPrimaDTO dto) {
		MateriaPrima entity = super.convertToEntity(dto);
		entity.setCantidadStock(0D);
		entity.setUnidadMedidaStock(UnidadMedidaMateriaPrima.KG);
		return entity;
	}

	@Override
	@Resource(name = "materiaPrimaService")
	protected void setService(MateriaPrimaService service) {
		this.service = service;
	}

}