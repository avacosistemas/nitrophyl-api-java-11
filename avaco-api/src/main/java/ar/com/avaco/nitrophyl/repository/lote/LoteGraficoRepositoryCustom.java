package ar.com.avaco.nitrophyl.repository.lote;

import java.util.List;

import ar.com.avaco.nitrophyl.ws.dto.LoteGraficoSinArchivoDTO;

public interface LoteGraficoRepositoryCustom {

	List<LoteGraficoSinArchivoDTO> listGraficoSinArchivo(Long idLote);

	
}
