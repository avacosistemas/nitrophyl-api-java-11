package ar.com.avaco.nitrophyl.ws.service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.ws.dto.FormulaDTO;
import ar.com.avaco.nitrophyl.ws.dto.RevisionParametrosDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface FormulaEPService extends CRUDEPService<Long, FormulaDTO> {

	FormulaDTO clone(FormulaDTO dto) throws BusinessException;

	RevisionParametrosDTO marcarRevision(Long idFormula);

}
