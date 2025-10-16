package ar.com.avaco.nitrophyl.service.molde;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeBoca;

public interface MoldeBocaService extends NJService<Long, MoldeBoca> {

	List<MoldeBoca> getByMolde(Long idMolde);

	void removeByMolde(Long idMolde);



}
