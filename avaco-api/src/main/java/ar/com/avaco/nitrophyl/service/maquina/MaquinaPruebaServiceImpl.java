package ar.com.avaco.nitrophyl.service.maquina;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.maquina.MaquinaPrueba;
import ar.com.avaco.nitrophyl.repository.maquina.MaquinaPruebaRepository;

@Transactional
@Service("maquinaPruebaService")
public class MaquinaPruebaServiceImpl extends NJBaseService<Long, MaquinaPrueba, MaquinaPruebaRepository>
		implements MaquinaPruebaService {

	@Override
	public List<MaquinaPrueba> listByMaquina(Long idMaquina) {
		return this.repository.findByIdMaquinaOrderByPosicion(idMaquina);
	}

	@Override
	public MaquinaPrueba save(MaquinaPrueba entity) {
		List<MaquinaPrueba> list = this.repository.findByIdMaquinaOrderByPosicion(entity.getIdMaquina());
		int pos = (list != null && list.size() > 0) ? list.size() + 1 : 1;
		entity.setPosicion(pos);
		return super.save(entity);
	}

	@Resource(name = "maquinaPruebaRepository")
	public void setMaquinaPruebaRepository(MaquinaPruebaRepository maquinaPruebaRepository) {
		this.repository = maquinaPruebaRepository;
	}

}
