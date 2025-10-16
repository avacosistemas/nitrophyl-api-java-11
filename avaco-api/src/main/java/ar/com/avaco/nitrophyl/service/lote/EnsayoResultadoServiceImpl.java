package ar.com.avaco.nitrophyl.service.lote;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.lote.EnsayoResultado;
import ar.com.avaco.nitrophyl.repository.lote.EnsayoResultadoRepository;

@Transactional
@Service("ensayoResultadoService")
public class EnsayoResultadoServiceImpl extends NJBaseService<Long, EnsayoResultado, EnsayoResultadoRepository>
		implements EnsayoResultadoService {

	@Resource(name = "ensayoResultadoRepository")
	public void setEnsayoResultadoRepository(EnsayoResultadoRepository ensayoResultadoRepository) {
		this.repository = ensayoResultadoRepository;
	}

	@Override
	public List<EnsayoResultado> listByEnsayo(Long idEnsayo) {
		return this.repository.findByEnsayoId(idEnsayo);
	}

}
