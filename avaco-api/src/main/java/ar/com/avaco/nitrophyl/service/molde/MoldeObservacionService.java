package ar.com.avaco.nitrophyl.service.molde;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.molde.MoldeObservacion;

public interface MoldeObservacionService extends NJService<Long, MoldeObservacion> {

	List<MoldeObservacion> listByMoldeId(Long idMolde);

}
