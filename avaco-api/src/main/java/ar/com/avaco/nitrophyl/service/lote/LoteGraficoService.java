package ar.com.avaco.nitrophyl.service.lote;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.moldes.LoteGrafico;
import ar.com.avaco.nitrophyl.ws.dto.ArchivoDTO;
import ar.com.avaco.nitrophyl.ws.dto.LoteGraficoSinArchivoDTO;

public interface LoteGraficoService extends NJService<Long, LoteGrafico> {

	ArchivoDTO getGraficoByIdLote(Long idLote);

	List<Long> filterIdsConGrafico(List<Long> loteIds);

	List<LoteGraficoSinArchivoDTO> listGraficosByLote(Long idLote);

}
