package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.MateriaPrima;
import ar.com.avaco.nitrophyl.repository.pieza.MateriaPrimaRepository;

@Transactional
@Service("materiaPrimaService")
public class MateriaPrimaServiceImpl extends NJBaseService<Long, MateriaPrima, MateriaPrimaRepository> implements MateriaPrimaService {

	@Resource(name = "materiaPrimaRepository")
	void setRepository(MateriaPrimaRepository materiaPrimaRepository) {
		this.repository = materiaPrimaRepository;
	}

}
