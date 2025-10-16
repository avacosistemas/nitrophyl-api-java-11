package ar.com.avaco.ws.rest.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.ws.dto.DesmoldantepostcuraPUTDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeoPUTDTO;
import ar.com.avaco.nitrophyl.ws.dto.ProcesoDTO;
import ar.com.avaco.nitrophyl.ws.service.ProcesoEPService;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class ProcesoRestController extends AbstractAuditableDTORestController<ProcesoDTO, Long, ProcesoEPService> {

	@Override
	@RequestMapping(value = "/proceso", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list() {
		return super.list();
	}

	@Override
	@RequestMapping(value = "/proceso", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> create(@RequestBody ProcesoDTO dto) throws BusinessException {
		return super.create(dto);
	}

	@Override
	@RequestMapping(value = "/proceso/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> update(@PathVariable Long id, @RequestBody ProcesoDTO dto)
			throws BusinessException {
		return super.update(id, dto);
	}

	@Override
	@RequestMapping(value = "/proceso/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> delete(@PathVariable Long id) throws BusinessException {
		return super.delete(id);
	}

	@RequestMapping(value = "/proceso/desmoldantepostcura/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> updateDesmoldantePostcura(@PathVariable Long id,
			@RequestBody DesmoldantepostcuraPUTDTO dto) throws BusinessException {
		this.service.updateDesmoldantePostcura(id, dto);
		return returnOK();
	}

	@RequestMapping(value = "/proceso/moldeo/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> updateMoldeo(@PathVariable Long id, @RequestBody MoldeoPUTDTO dto)
			throws BusinessException {
		this.service.updateMoldeo(id, dto);
		return returnOK();
	}

	@Resource(name = "procesoEPService")
	public void setService(ProcesoEPService procesoEPService) {
		super.service = procesoEPService;
	}

}