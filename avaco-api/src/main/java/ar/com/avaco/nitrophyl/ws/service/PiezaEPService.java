
package ar.com.avaco.nitrophyl.ws.service;

import java.util.List;

import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaComboDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaCreacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaEdicionDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaGrillaDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaPUTDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPService;

public interface PiezaEPService extends CRUDAuditableEPService<Long, PiezaDTO> {

	PiezaCreacionDTO create(PiezaCreacionDTO dto);

	void marcarVigente(Long piezaId);

	void nuevaRevision(Long piezaId);

	PageDTO<PiezaGrillaDTO> listGrilla(PiezaFilterDTO filter);

	PiezaEdicionDTO getByIdEdicion(Long idPieza);

	void update(Long idPieza, PiezaPUTDTO piezaFormula);

	List<PiezaComboDTO> listCombo(String nombre, Long idCliente);


}
