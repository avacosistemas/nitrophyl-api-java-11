package ar.com.avaco.nitrophyl.service.administracion;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.administracion.Pais;
import ar.com.avaco.nitrophyl.repository.administracion.PaisRepository;

@Transactional
@Service("paisService")
public class PaisServiceImpl extends NJBaseService<Long, Pais, PaisRepository> implements PaisService {

	@Resource(name = "paisRepository")
	void setRepository(PaisRepository paisRepository) {
		this.repository = paisRepository;
	}

}
