package ar.com.avaco.nitrophyl.service.molde;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.molde.MoldeDimension;

public interface MoldeDimensionService extends NJService<Long, MoldeDimension> {

	List<MoldeDimension> getByMolde(Long idMolde);

	void removeByMolde(Long idMolde);



}
