package ar.com.avaco.nitrophyl.service.lote;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.lote.LoteObservacion;

public interface LoteObservacionService extends NJService<Long, LoteObservacion> {

	void updateCheck(Long idLoteObservacion);

}
