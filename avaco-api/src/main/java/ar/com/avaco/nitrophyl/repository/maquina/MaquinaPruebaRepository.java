package ar.com.avaco.nitrophyl.repository.maquina;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.maquina.MaquinaPrueba;

public interface MaquinaPruebaRepository extends NJRepository<Long, MaquinaPrueba>, MaquinaPruebaRepositoryCustom {

	List<MaquinaPrueba> findByIdMaquinaOrderByPosicion(Long idMaquina);

	void removeByIdMaquina(Long idMaquina);

}
