package ar.com.avaco.ws.rest.service;

import java.io.Serializable;
import java.util.List;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public interface CRUDAuditableEPService<ID extends Serializable, DTO extends DTOAuditableEntity<ID>> {

	DTO save(DTO entity) throws BusinessException;

	DTO update(DTO entity) throws BusinessException;

	DTO get(ID id);

	List<DTO> list();

	void remove(ID id) throws BusinessException;

	int listCount(AbstractFilter abstractFilter);

	List<DTO> listFilter(AbstractFilter abstractFilter);

	List<DTO> listPattern(String field, String pattern);

	PageDTO<DTO> listFilterCount(AbstractFilter abstractFilter);
	
	List<DTO> listEq(String field, Object value);

}
