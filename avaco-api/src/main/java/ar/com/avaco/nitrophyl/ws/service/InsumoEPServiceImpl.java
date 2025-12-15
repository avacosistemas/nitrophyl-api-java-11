package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.Insumo;
import ar.com.avaco.nitrophyl.domain.entities.pieza.InsumoMateriaPrima;
import ar.com.avaco.nitrophyl.domain.entities.pieza.MateriaPrima;
import ar.com.avaco.nitrophyl.service.pieza.InsumoService;
import ar.com.avaco.nitrophyl.service.pieza.TipoInsumoService;
import ar.com.avaco.nitrophyl.ws.dto.InsumoDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("insumoEPService")
public class InsumoEPServiceImpl extends CRUDAuditableEPBaseService<Long, InsumoDTO, Insumo, InsumoService>
		implements InsumoEPService {

	public InsumoEPServiceImpl() {
		super(Insumo.class, InsumoDTO.class);
	}

	private TipoInsumoService tipoInsumoService;

	@Override
	@Resource(name = "insumoService")
	protected void setService(InsumoService service) {
		this.service = service;
	}

	@Override
	protected Insumo convertToEntityForSave(InsumoDTO dto) {
		Insumo entity = this.convertToEntity(dto);
		entity.setCantidadStock(0D);
		entity.setUnidadMedidaStock(entity.getTipo().getTipoStock().getUnidadMedida());
		return entity;
	}
	
	@Override
	protected Insumo convertToEntity(InsumoDTO dto) {
		Insumo insumo = new Insumo();
		insumo.setId(dto.getId());
		insumo.setNombre(dto.getNombre());
		insumo.setTipo(tipoInsumoService.get(dto.getIdTipo()));
		insumo.setObservaciones(dto.getObservaciones());
		insumo.setOrigen(dto.getOrigen());
		
		// Si tiene asociada una materia prima
		if (dto.getIdMateriaPrima() != null) {
			InsumoMateriaPrima imp = new InsumoMateriaPrima();
			MateriaPrima mp = MateriaPrima.ofId(dto.getIdMateriaPrima());
			imp.setMateriaPrima(mp);
			imp.setCantidad(dto.getCantidadMateriaPrima());
			insumo.setInsumoMateriaPrima(imp);
		}
		return insumo;
	}
	
	@Override
	protected InsumoDTO convertToDto(Insumo entity) {
		InsumoDTO dto = super.convertToDto(entity);
		dto.setId(entity.getId());
		dto.setNombre(entity.getNombre());
		dto.setIdTipo(entity.getTipo().getId());
		dto.setTipoNombre(entity.getTipo().getNombreCompleto());
		dto.setObservaciones(entity.getObservaciones());
		dto.setOrigen(entity.getOrigen());
		
		if (entity.getInsumoMateriaPrima() != null) {
			InsumoMateriaPrima insumoMateriaPrima = entity.getInsumoMateriaPrima();
			dto.setCantidadMateriaPrima(insumoMateriaPrima.getCantidad());
			dto.setIdMateriaPrima(insumoMateriaPrima.getMateriaPrima().getId());
			dto.setMateriaPrimaNombre(insumoMateriaPrima.getMateriaPrima().getNombre());
		}
		return dto;
	}

	@Resource(name = "tipoInsumoService")
	public void setTipoInsumoService(TipoInsumoService tipoInsumoService) {
		this.tipoInsumoService = tipoInsumoService;
	}

}
