package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.esquema.Esquema;
import ar.com.avaco.nitrophyl.repository.pieza.EsquemaRepository;

@Transactional
@Service("esquemaService")
public class EsquemaServiceImpl extends NJBaseService<Long, Esquema, EsquemaRepository> implements EsquemaService {

	@Resource(name = "esquemaRepository")
	void setRepository(EsquemaRepository esquemaRepository) {
		this.repository = esquemaRepository;
	}

	@Autowired
	private PiezaService piezaService;

	@Override
	public Esquema save(Esquema entity) {
		Esquema save = super.save(entity);
		this.piezaService.actualizarFaltantes(entity.getProceso().getId());
		return save;
	}
	
	@Override
	public void remove(Long id) {
		Esquema esquema = this.get(id);
		Long idProceso = esquema.getProceso().getId();
		super.remove(id);
		piezaService.actualizarFaltantes(idProceso);
	}

}
