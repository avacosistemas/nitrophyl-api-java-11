package ar.com.avaco.ws.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.ws.dto.ComboDTO;
import ar.com.avaco.nitrophyl.ws.dto.MaquinaFabricaDTO;
import ar.com.avaco.nitrophyl.ws.service.MaquinaFabricaEPService;
import ar.com.avaco.nitrophyl.ws.service.filter.MaquinaFabricaFilter;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class MaquinaFabricaRestController extends AbstractDTORestController<MaquinaFabricaDTO, Long, MaquinaFabricaEPService> {

	@Override
	@RequestMapping(value = "/maquinaFabrica", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list() {
		return super.list();
	}

	@Override
	@RequestMapping(value = "/maquinaFabrica", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> create(@RequestBody MaquinaFabricaDTO dto) throws BusinessException {
		return super.create(dto);
	}

	@Override
	@RequestMapping(value = "/maquinaFabrica/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> update(@PathVariable Long id, @RequestBody MaquinaFabricaDTO dto)
			throws BusinessException {
		return super.update(id, dto);
	}

	@Override
	@RequestMapping(value = "/maquinaFabrica/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> delete(@PathVariable Long id) throws BusinessException {
		return super.delete(id);
	}

	@RequestMapping(value = "/maquinaFabrica/combo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listombo(@RequestParam(required = false) String nombre, @RequestParam(required = false) Long idSector) {
		
		MaquinaFabricaFilter filter = new MaquinaFabricaFilter();
		filter.setAsc(true);
		filter.setDistinctRootEntity(true);
		filter.setFirst(0);
		filter.setIdSector(idSector);
		filter.setIdx("nombre");
		filter.setNombre(nombre);
		filter.setRows(999);
		
		List<MaquinaFabricaDTO> listPattern = this.service.listFilter(filter);
		List<ComboDTO> combo = new ArrayList<ComboDTO>();
		listPattern.forEach(sector -> combo.add(new ComboDTO(sector.getNombre(), sector.getId().toString())));
		JSONResponse response = new JSONResponse();
		response.setData(combo);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@Resource(name = "maquinaFabricaEPService")
	public void setService(MaquinaFabricaEPService maquinaFabricaEPService) {
		super.service = maquinaFabricaEPService;
	}

}