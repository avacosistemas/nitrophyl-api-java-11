package ar.com.avaco.nitrophyl.service.lote;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.lote.LoteObservacion;
import ar.com.avaco.nitrophyl.repository.lote.LoteObservacionRepository;

@Transactional
@Service("loteObservacionService")
public class LoteObservacionServiceImpl extends NJBaseService<Long, LoteObservacion, LoteObservacionRepository>
		implements LoteObservacionService {

	@Resource(name = "loteObservacionRepository")
	public void setRepository(LoteObservacionRepository loteObservacionRepository) {
		this.repository = loteObservacionRepository;
	}

	@Override
	public void updateCheck(Long idLoteObservacion) {
		LoteObservacion loteObservacion = this.get(idLoteObservacion);
		loteObservacion.setMostrarReporte(!loteObservacion.getMostrarReporte());
	}

}