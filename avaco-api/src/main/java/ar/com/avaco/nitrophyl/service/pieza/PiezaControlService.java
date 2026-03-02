package ar.com.avaco.nitrophyl.service.pieza;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaControl;

public interface PiezaControlService extends NJService<Long, PiezaControl> {

	List<PiezaControl> listControlesConfigurados(Long idPieza);


}
