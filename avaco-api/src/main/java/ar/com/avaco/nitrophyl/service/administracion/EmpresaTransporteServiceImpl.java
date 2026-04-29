package ar.com.avaco.nitrophyl.service.administracion;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.administracion.EmpresaTransporte;
import ar.com.avaco.nitrophyl.repository.administracion.EmpresaTransporteRepository;

@Transactional
@Service("empresaTransporteService")
public class EmpresaTransporteServiceImpl extends NJBaseService<Long, EmpresaTransporte, EmpresaTransporteRepository> implements EmpresaTransporteService {

	@Resource(name = "empresaTransporteRepository")
	void setRepository(EmpresaTransporteRepository empresaTransporteRepository) {
		this.repository = empresaTransporteRepository;
	}

}
