package ar.com.avaco.commons.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.domain.AyudaDinamica;
import ar.com.avaco.commons.repository.AyudaDinamicaRepository;

@Transactional
@Service("ayudaDinamicaService")
public class AyudaDinamicaServiceImpl extends NJBaseService<Long, AyudaDinamica, AyudaDinamicaRepository> implements AyudaDinamicaService {

	@Resource(name = "ayudaDinamicaRepository")
	void setRepository(AyudaDinamicaRepository ayudaDinamicaRepository) {
		this.repository = ayudaDinamicaRepository;
	}

}
