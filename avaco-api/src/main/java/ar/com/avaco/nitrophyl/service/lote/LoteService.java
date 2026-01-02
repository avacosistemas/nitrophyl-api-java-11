package ar.com.avaco.nitrophyl.service.lote;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.nitrophyl.ws.dto.RegistroEnsayoLotePorMaquinaDTO;
import ar.com.avaco.nitrophyl.ws.dto.ReporteEnsayoLotePorMaquinaFilterDTO;

public interface LoteService extends NJService<Long, Lote> {

	void aprobar(Long idLote, String estado, String observaciones);

	void rechazar(Long idLote, String observaciones);
	
	boolean hasEnsayos(Long idLote);

	Lote getLoteCompleto(Long idLote);

	void revisiones();

	List<RegistroEnsayoLotePorMaquinaDTO> getRegistrosEnsayosLotePorMaquina(ReporteEnsayoLotePorMaquinaFilterDTO filtro);

	boolean existsByFormula(Long idFormula);

}
