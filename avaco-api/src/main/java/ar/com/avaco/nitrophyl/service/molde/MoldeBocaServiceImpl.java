package ar.com.avaco.nitrophyl.service.molde;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.molde.MoldeBoca;
import ar.com.avaco.nitrophyl.repository.molde.MoldeBocaRepository;

@Transactional
@Service("moldeBocaService")
public class MoldeBocaServiceImpl extends NJBaseService<Long, MoldeBoca, MoldeBocaRepository>
		implements MoldeBocaService {

	@Resource(name = "moldeBocaRepository")
	void setClienteRepository(MoldeBocaRepository moldeBocaRepository) {
		this.repository = moldeBocaRepository;
	}

	@Override
	public List<MoldeBoca> getByMolde(Long idMolde) {
		return this.repository.findByIdMoldeOrderByNroBoca(idMolde);
	}

	@Override
	public void reacomodarNumerosBoca(Long idMolde, Integer numeroBocaEliminado) {
		this.repository.reacomodarNumerosBoca(idMolde, numeroBocaEliminado);
	}
	
}
