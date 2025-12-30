package ar.com.avaco.nitrophyl.ws.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.domain.entities.molde.Molde;
import ar.com.avaco.nitrophyl.domain.entities.molde.MoldeBoca;
import ar.com.avaco.nitrophyl.service.molde.MoldeBocaService;
import ar.com.avaco.nitrophyl.service.molde.MoldeService;
import ar.com.avaco.nitrophyl.ws.dto.MoldeBocaDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Transactional
@Service("moldeBocaEPService")
public class MoldeBocaEPServiceImpl extends CRUDAuditableEPBaseService<Long, MoldeBocaDTO, MoldeBoca, MoldeBocaService>
		implements MoldeBocaEPService {

	@Autowired
	private MoldeService moldeService;

	public MoldeBocaEPServiceImpl() {
		super(MoldeBoca.class, MoldeBocaDTO.class);
	}

	@Override
	public List<MoldeBocaDTO> listByMoldeId(Long idMolde) {
		List<MoldeBoca> listado = service.getByMolde(idMolde);
		if (listado != null) {
			List<MoldeBocaDTO> returnedListado = new ArrayList<MoldeBocaDTO>();
			for (MoldeBoca moldeBoca : listado) {
				returnedListado.add(convertToDto(moldeBoca));
			}
			return returnedListado;
		}
		return null;
	}

	@Override
	public MoldeBocaDTO save(MoldeBocaDTO dto) throws BusinessException {
		this.moldeService.incrementarBocas(dto.getIdMolde());
		Integer nuevaCantidad = this.moldeService.getCantidadBocas(dto.getIdMolde());
		dto.setNroBoca(nuevaCantidad);
		return super.save(dto);
	}

	@Override
	public void remove(Long id) {
		MoldeBoca moldeBoca = this.service.get(id);
		Molde molde = moldeBoca.getMolde();
		this.moldeService.disminuirBocas(id);
		super.remove(id);
		this.service.reacomodarNumerosBoca(molde.getId(), moldeBoca.getNroBoca());
	}

	@Override
	protected MoldeBoca convertToEntityForUpdate(MoldeBocaDTO dto) {
		MoldeBoca mb = this.service.get(dto.getId());
		mb.setDescripcion(dto.getDescripcion());
		mb.setEstado(dto.getEstado());
		this.service.updateUserDateModificacion(mb);
		MoldeBoca update = this.service.update(mb);
		return update;
	}
	
	@Override
	@Resource(name = "moldeBocaService")
	protected void setService(MoldeBocaService service) {
		this.service = service;
	}

}
