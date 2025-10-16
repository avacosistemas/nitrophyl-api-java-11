package ar.com.avaco.nitrophyl.service.lote;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.lote.Ensayo;

public interface EnsayoService extends NJService<Long, Ensayo> {

	List<Ensayo> listByLote(Long idLote);

}
