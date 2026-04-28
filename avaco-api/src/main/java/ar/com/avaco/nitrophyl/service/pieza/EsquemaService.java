package ar.com.avaco.nitrophyl.service.pieza;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.esquema.Esquema;

public interface EsquemaService extends NJService<Long, Esquema> {

	void reordenar(Long idEsquema, Integer posicion);

	List<Esquema> listEsquemas(Long idProceso);

}