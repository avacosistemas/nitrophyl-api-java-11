package ar.com.avaco.nitrophyl.service.lote;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.lote.EnsayoResultado;

public interface EnsayoResultadoService extends NJService<Long, EnsayoResultado> {

	List<EnsayoResultado> listByEnsayo(Long idEnsayo);

}
