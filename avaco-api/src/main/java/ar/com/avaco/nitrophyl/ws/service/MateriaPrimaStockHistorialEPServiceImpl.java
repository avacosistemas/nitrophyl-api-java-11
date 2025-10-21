package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.MateriaPrima;
import ar.com.avaco.nitrophyl.domain.entities.pieza.MateriaPrimaStockHistorial;
import ar.com.avaco.nitrophyl.service.pieza.MateriaPrimaStockHistorialService;
import ar.com.avaco.nitrophyl.ws.dto.MateriaPrimaStockHistorialDTO;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("materiaPrimaStockHistorialEPService")
public class MateriaPrimaStockHistorialEPServiceImpl extends CRUDAuditableEPBaseService<Long, MateriaPrimaStockHistorialDTO, MateriaPrimaStockHistorial, MateriaPrimaStockHistorialService>
		implements MateriaPrimaStockHistorialEPService {

	public MateriaPrimaStockHistorialEPServiceImpl() {
		super(MateriaPrimaStockHistorial.class, MateriaPrimaStockHistorialDTO.class);
	}

	@Override
	protected MateriaPrimaStockHistorial convertToEntityForSave(MateriaPrimaStockHistorialDTO dto) {
		MateriaPrimaStockHistorial entity = super.convertToEntityForSave(dto);
		entity.setFecha(DateUtils.getFechaYHoraActual());
		entity.setMateriaPrima(MateriaPrima.ofId(dto.getIdMateriaPrima()));
		return entity;
	}
	
	@Override
	@Resource(name = "materiaPrimaStockHistorialService")
	protected void setService(MateriaPrimaStockHistorialService service) {
		this.service = service;
	}

}