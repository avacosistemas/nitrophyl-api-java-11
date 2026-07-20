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
import ar.com.avaco.nitrophyl.ws.dto.SectorFabricaDTO;
import ar.com.avaco.nitrophyl.ws.service.SectorFabricaEPService;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class SectorFabricaRestController
		extends AbstractDTORestController<SectorFabricaDTO, Long, SectorFabricaEPService> {

	@Override
	@RequestMapping(value = "/sectorFabrica", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list() {
		return super.list();
	}

	@Override
	@RequestMapping(value = "/sectorFabrica", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> create(@RequestBody SectorFabricaDTO dto) throws BusinessException {
		return super.create(dto);
	}

	@Override
	@RequestMapping(value = "/sectorFabrica/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> update(@PathVariable Long id, @RequestBody SectorFabricaDTO dto)
			throws BusinessException {
		return super.update(id, dto);
	}

	@Override
	@RequestMapping(value = "/sectorFabrica/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> delete(@PathVariable Long id) throws BusinessException {
		return super.delete(id);
	}

	@RequestMapping(value = "/sectorFabrica/combo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listombo(@RequestParam(required = false) String nombre) {
		nombre = nombre != null ? nombre : ""; 
		List<SectorFabricaDTO> listPattern = this.service.listPattern("nombre", nombre);
		List<ComboDTO> combo = new ArrayList<ComboDTO>();
		listPattern.forEach(sector -> combo.add(new ComboDTO(sector.getNombre(), sector.getId().toString())));
		JSONResponse response = new JSONResponse();
		response.setData(combo);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@Resource(name = "sectorFabricaEPService")
	public void setService(SectorFabricaEPService sectorFabricaEPService) {
		super.service = sectorFabricaEPService;
	}

}