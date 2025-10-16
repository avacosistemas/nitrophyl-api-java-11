package ar.com.avaco.nitrophyl.service.lote;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.moldes.LoteGrafico;
import ar.com.avaco.nitrophyl.repository.lote.LoteGraficoRepository;
import ar.com.avaco.nitrophyl.ws.dto.ArchivoDTO;
import ar.com.avaco.nitrophyl.ws.dto.LoteGraficoSinArchivoDTO;

@Transactional
@Service("loteGraficoService")
public class LoteGraficoServiceImpl extends NJBaseService<Long, LoteGrafico, LoteGraficoRepository>
		implements LoteGraficoService {

	@Override
	public ArchivoDTO getGraficoByIdLote(Long idLote) {
		Optional<LoteGrafico> ret = this.repository.findFirstByIdLoteOrderByFecha(idLote);
		if (ret.isPresent()) {
			ArchivoDTO dto = new ArchivoDTO();
			dto.setArchivo(ret.get().getArchivo());
			return dto;
		}
		return null;
	}

	@Resource(name = "loteGraficoRepository")
	void setLoteGraficoRepository(LoteGraficoRepository loteGraficoRepository) {
		this.repository = loteGraficoRepository;
	}

	@Override
	public List<Long> filterIdsConGrafico(List<Long> loteIds) {
		return this.repository.findLoteIdLoteByIdLoteIn(loteIds);
	}

	@Override
	public List<LoteGraficoSinArchivoDTO> listGraficosByLote(Long idLote) {
		return this.repository.listGraficoSinArchivo(idLote);
	}

}
