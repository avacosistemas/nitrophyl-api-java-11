package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaTipo;
import ar.com.avaco.nitrophyl.repository.pieza.PiezaTipoRepository;

@Transactional
@Service("piezaTipoService")
public class PiezaTipoServiceImpl extends NJBaseService<Long, PiezaTipo, PiezaTipoRepository> implements PiezaTipoService {

	@Resource(name = "piezaTipoRepository")
	void setRepository(PiezaTipoRepository piezaTipoRepository) {
		this.repository = piezaTipoRepository;
	}

}
