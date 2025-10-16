package ar.com.avaco.nitrophyl.service.maquina;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.maquina.MaquinaPrueba;

public interface MaquinaPruebaService extends NJService<Long, MaquinaPrueba> {

	List<MaquinaPrueba> listByMaquina(Long idMaquina);

}
