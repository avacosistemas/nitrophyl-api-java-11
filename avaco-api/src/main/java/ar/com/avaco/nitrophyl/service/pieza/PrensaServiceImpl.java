package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.fabrica.Prensa;
import ar.com.avaco.nitrophyl.repository.pieza.PrensaRepository;

@Transactional
@Service("prensaService")
public class PrensaServiceImpl extends NJBaseService<Long, Prensa, PrensaRepository> implements PrensaService {

	@Resource(name = "prensaRepository")
	void setRepository(PrensaRepository prensaRepository) {
		this.repository = prensaRepository;
	}

}
