package ar.com.avaco.nitrophyl.service.lote;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.reporte.RegistroEnvioInformeCalidad;
import ar.com.avaco.nitrophyl.repository.lote.RegistroEnvioInformeCalidadRepository;

@Transactional
@Service("registroEnvioInformeCalidadService")
public class RegistroEnvioInformeCalidadServiceImpl
		extends NJBaseService<Long, RegistroEnvioInformeCalidad, RegistroEnvioInformeCalidadRepository>
		implements RegistroEnvioInformeCalidadService {

	@Resource(name = "registroEnvioInformeCalidadRepository")
	public void setRegistroEnvioInformeRepository(RegistroEnvioInformeCalidadRepository repository) {
		this.repository = repository;
	}
	
}