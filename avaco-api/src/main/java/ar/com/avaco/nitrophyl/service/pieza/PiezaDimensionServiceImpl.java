package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaDimension;
import ar.com.avaco.nitrophyl.repository.pieza.PiezaDimensionRepository;

@Transactional
@Service("piezaDimensionService")
public class PiezaDimensionServiceImpl extends NJBaseService<Long, PiezaDimension, PiezaDimensionRepository> implements PiezaDimensionService {

	@Resource(name = "piezaDimensionRepository")
	void setRepository(PiezaDimensionRepository piezaDimensionRepository) {
		this.repository = piezaDimensionRepository;
	}

	@Autowired
	private PiezaService piezaService;
	
	@Override
	public PiezaDimension save(PiezaDimension entity) {
		PiezaDimension save = super.save(entity);
		this.piezaService.actualizarFaltantes(entity.getPieza().getId());
		return save;
	}
	
	@Override
	public void remove(Long id) {
		PiezaDimension piezaDimension = this.get(id);
		Long idPieza = piezaDimension.getPieza().getId();
		super.remove(id);
		this.piezaService.actualizarFaltantes(idPieza);
	}
}
