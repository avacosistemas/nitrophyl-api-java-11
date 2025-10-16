package ar.com.avaco.nitrophyl.service.lote;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.lote.Ensayo;
import ar.com.avaco.nitrophyl.repository.lote.EnsayoRepository;

@Transactional
@Service("ensayoService")
public class EnsayoServiceImpl extends NJBaseService<Long, Ensayo, EnsayoRepository> implements EnsayoService {


	@Resource(name = "ensayoRepository")
	public void setEnsayoRepository(EnsayoRepository ensayoRepository) {
		this.repository = ensayoRepository;
	}

	@Override
	public List<Ensayo> listByLote(Long idLote) {
		return this.repository.findByLoteId(idLote);
	}

}
