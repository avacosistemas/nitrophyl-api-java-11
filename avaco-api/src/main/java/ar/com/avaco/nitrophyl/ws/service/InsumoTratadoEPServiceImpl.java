package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.Adhesivo;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Insumo;
import ar.com.avaco.nitrophyl.domain.entities.pieza.InsumoTratado;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Tratamiento;
import ar.com.avaco.nitrophyl.service.pieza.InsumoService;
import ar.com.avaco.nitrophyl.service.pieza.InsumoTratadoService;
import ar.com.avaco.nitrophyl.service.pieza.PiezaService;
import ar.com.avaco.nitrophyl.service.pieza.TratamientoService;
import ar.com.avaco.nitrophyl.ws.dto.AdhesivoDTO;
import ar.com.avaco.nitrophyl.ws.dto.InsumoTratadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.TipoInsumoDTO;
import ar.com.avaco.nitrophyl.ws.dto.TratamientoDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("insumoTratadoEPService")
public class InsumoTratadoEPServiceImpl
		extends CRUDAuditableEPBaseService<Long, InsumoTratadoDTO, InsumoTratado, InsumoTratadoService>
		implements InsumoTratadoEPService {

	private InsumoService insumoService;
	
	private PiezaService piezaService;
	
	private TratamientoService tratamientoService;
	
	public InsumoTratadoEPServiceImpl() {
		super(InsumoTratado.class, InsumoTratadoDTO.class);
	}

	@Override
	protected InsumoTratadoDTO convertToDto(InsumoTratado entity) {
		InsumoTratadoDTO it = new InsumoTratadoDTO();
		entity.getAdhesivos().forEach(ad -> it.getAdhesivos().add(super.modelMapper.map(ad, AdhesivoDTO.class)));
		it.setId(entity.getId());
		it.setIdInsumo(entity.getInsumo().getId());
		it.setIdPieza(entity.getPieza().getId());
		entity.getTratamientos().forEach(tr -> it.getTratamientos().add(super.modelMapper.map(tr, TratamientoDTO.class)));
		it.setInsumo(entity.getInsumo().getNombre());
		it.setMedidaObservaciones(entity.getMedidaObservaciones());
		it.setMedidaValor(entity.getMedidaValor());
		it.setObservaciones(entity.getObservaciones());
		it.setTipo(super.modelMapper.map(entity.getInsumo().getTipo(), TipoInsumoDTO.class));
		return it;
	}
	
	@Override
	protected InsumoTratado convertToEntity(InsumoTratadoDTO dto) {
		InsumoTratado entity = new InsumoTratado();
		dto.getAdhesivos().forEach(ad -> entity.getAdhesivos().add(super.modelMapper.map(ad, Adhesivo.class)));
		entity.setId(dto.getId());
		Insumo insumo = insumoService.get(dto.getIdInsumo());
		entity.setInsumo(insumo);
		entity.setMedidaObservaciones(dto.getMedidaObservaciones());
		entity.setMedidaValor(dto.getMedidaValor());
		entity.setObservaciones(dto.getObservaciones());
		dto.getTratamientos().forEach(tr -> entity.getTratamientos().add(super.modelMapper.map(tr,Tratamiento.class)));
		Pieza pieza = piezaService.get(dto.getIdPieza()); 
		entity.setPieza(pieza);
		return entity;
	}
	
	@Override
	@Resource(name = "insumoTratadoService")
	protected void setService(InsumoTratadoService service) {
		this.service = service;
	}

	@Resource(name = "insumoService")
	public void setInsumoService(InsumoService insumoService) {
		this.insumoService = insumoService;
	}
	
	@Resource(name = "piezaService")
	public void setPiezaService(PiezaService piezaService) {
		this.piezaService = piezaService;
	}
	
	@Resource(name = "tratamientoService")
	public void setTratamientoService(TratamientoService tratamientoService) {
		this.tratamientoService = tratamientoService;
	}
	
}