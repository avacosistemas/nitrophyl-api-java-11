package ar.com.avaco.ws.rest.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

@Transactional
public abstract class CRUDAuditableEPBaseService<ID extends Serializable, DTO extends DTOAuditableEntity<ID>, T extends AuditableEntity<ID>, S extends NJService<ID, T>>
		implements CRUDAuditableEPService<ID, DTO> {

	protected S service;

	private final Class<DTO> dtoClass;
	private final Class<T> entityClass;

	public CRUDAuditableEPBaseService(Class<T> theEntityClass, Class<DTO> theDtoClass) {
		dtoClass = theDtoClass;
		entityClass = theEntityClass;
	}

	protected final ModelMapper modelMapper = new ModelMapper();

	@Override
	public DTO save(DTO dto) throws BusinessException {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		validationSave(dto);
		T entity = convertToEntity(dto);
		entity.setUsuarioCreacion(username);
		entity.setFechaCreacion(DateUtils.getFechaYHoraActual());
		entity = service.save(entity);
		T saved = service.get(entity.getId());
		DTO convertToDto = convertToDto(saved);
		convertToDto.setUsuarioCreacion(saved.getUsuarioCreacion());
		convertToDto.setFechaCreacion(saved.getFechaCreacion());
		return convertToDto;
	}

	protected void validationSave(DTO dto) {
		// Implementar en los hijos
	}

	protected void validationUpdate(DTO dto) {
		// Implementar en los hijos
	}

	@Override
	public DTO update(DTO dto) throws BusinessException {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		T entity = convertToEntity(dto);
		entity.setUsuarioActualizacion(username);
		entity.setFechaActualizacion(DateUtils.getFechaYHoraActual());
		entity = service.update(entity);
		DTO convertToDto = convertToDto(entity);
		convertToDto.setUsuarioCreacion(entity.getUsuarioCreacion());
		convertToDto.setFechaCreacion(entity.getFechaCreacion());
		convertToDto.setUsuarioActualizacion(entity.getUsuarioActualizacion());
		convertToDto.setFechaActualizacion(entity.getFechaActualizacion());
		return convertToDto;
	}

	@Override
	public DTO get(ID id) {
		T t = service.get(id);
		DTO convertToDto = convertToDto(t);
		convertToDto.setUsuarioCreacion(t.getUsuarioCreacion());
		convertToDto.setFechaCreacion(t.getFechaCreacion());
		convertToDto.setUsuarioActualizacion(t.getUsuarioActualizacion());
		convertToDto.setFechaActualizacion(t.getFechaActualizacion());
		return convertToDto;
	}

	@Override
	public List<DTO> list() {
		List<T> entities = service.list();
		List<DTO> dtos = convertToDtos(entities);
		return dtos;
	}

	@Override
	public void remove(ID id) {
		service.remove(id);
	}

	@Override
	public int listCount(AbstractFilter abstractFilter) {
		return service.listCount(abstractFilter);
	}

	@Override
	public List<DTO> listFilter(AbstractFilter abstractFilter) {
		return convertToDtos(service.listFilter(abstractFilter));
	}

	@Override
	public List<DTO> listPattern(String field, String pattern) {
		return convertToDtos(this.service.listPattern(field, pattern));
	}

	@Override
	public List<DTO> listEq(String field, Object value) {
		return convertToDtos(this.service.listEqField(field, value));
	}

	protected T convertToEntity(DTO dto) {
		return modelMapper.map(dto, entityClass);
	}

	protected DTO convertToDto(T entity) {
		return modelMapper.map(entity, dtoClass);
	}

	public List<T> convertToEntities(Collection<DTO> dtos) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<T> entities = new ArrayList<T>();
		for (DTO dto : dtos) {
			T convertToEntity = convertToEntity(dto);
			convertToEntity.setUsuarioActualizacion(username);
			convertToEntity.setFechaActualizacion(DateUtils.getFechaYHoraActual());
			entities.add(convertToEntity);
		}
		return entities;
	}

	public List<DTO> convertToDtos(Collection<T> entities) {
		List<DTO> dtos = new ArrayList<DTO>();
		for (T entity : entities) {
			DTO convertToDto = convertToDto(entity);
			convertToDto.setUsuarioCreacion(entity.getUsuarioCreacion());
			convertToDto.setFechaCreacion(entity.getFechaCreacion());
			convertToDto.setUsuarioActualizacion(entity.getUsuarioActualizacion());
			convertToDto.setFechaActualizacion(entity.getFechaActualizacion());
			dtos.add(convertToDto);
		}
		return dtos;
	}

	protected final S getService() {
		return this.service;
	}

	protected abstract void setService(S service);

	@Override
	public PageDTO<DTO> listFilterCount(AbstractFilter abstractFilter) {
		PageDTO<DTO> page = new PageDTO<DTO>();
		List<DTO> listFilter = listFilter(abstractFilter);
		int listCount = listCount(abstractFilter);
		page.setPage(listFilter);
		page.setTotalReg(listCount);
		return page;
	}

}
