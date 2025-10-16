package ar.com.avaco.nitrophyl.service.maquina;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.maquina.Maquina;
import ar.com.avaco.nitrophyl.repository.maquina.MaquinaRepository;

@Transactional
@Service("maquinaService")
public class MaquinaServiceImpl extends NJBaseService<Long, Maquina, MaquinaRepository> implements MaquinaService {

	
	@Override
	public Maquina save(Maquina entity) {
		entity.setPosicion(this.list().size() + 1);
		return super.save(entity);
	}
	
	@Resource(name = "maquinaRepository")
	public void setMaquinaRepository(MaquinaRepository maquinaRepository) {
		this.repository = maquinaRepository;
	}

}
