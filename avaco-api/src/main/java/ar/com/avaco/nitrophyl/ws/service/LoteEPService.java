package ar.com.avaco.nitrophyl.ws.service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.ws.dto.ArchivoDTO;
import ar.com.avaco.nitrophyl.ws.dto.LoteDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.dto.ReporteEnsayoLotePorMaquinaDTO;
import ar.com.avaco.nitrophyl.ws.dto.ReporteEnsayoLotePorMaquinaFilterDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface LoteEPService extends CRUDEPService<Long, LoteDTO> {

	void aprobar(Long idLote, String estado, String observaciones);

	void rechazar(Long idLote, String observaciones);

	ArchivoDTO generarReporteLoteCliente(Long idLote, Long idCliente, String string) throws BusinessException;

	void revisiones();

	PageDTO<ReporteEnsayoLotePorMaquinaDTO> generarReporteEnsayoLotePorMaquina(
			ReporteEnsayoLotePorMaquinaFilterDTO filtro);

	void enviarReporte(String idLotes, Long idCliente, byte[] adjuntoextra, String nombreAdjunto, String observaciones,
			String string) throws BusinessException;

	Boolean hasEnsayos(Long idLote);

}
