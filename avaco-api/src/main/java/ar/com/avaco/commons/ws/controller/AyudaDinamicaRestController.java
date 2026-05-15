package ar.com.avaco.commons.ws.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.ws.service.AyudaDinamicaEPService;
import ar.com.avaco.ws.rest.controller.AbstractAuditableDTORestController;
import ar.com.avaco.ws.rest.dto.AyudaDinamicaDTO;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class AyudaDinamicaRestController extends AbstractAuditableDTORestController<AyudaDinamicaDTO, Long, AyudaDinamicaEPService> {

	@RequestMapping(value = "/ayuda-dinamica", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> get(@RequestParam String path) {
		List<AyudaDinamicaDTO> list = this.service.listEq("path", path);
		AyudaDinamicaDTO ayuda = new AyudaDinamicaDTO();
		if (!list.isEmpty()) ayuda = list.get(0);
		return OK(ayuda);
	}

	@Override
	@RequestMapping(value = "/ayuda-dinamica", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> create(@RequestBody AyudaDinamicaDTO dto) throws BusinessException {
		return super.create(dto);
	}

	@Override
	@RequestMapping(value = "/ayuda-dinamica/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> update(@PathVariable Long id, @RequestBody AyudaDinamicaDTO dto)
			throws BusinessException {
		return super.update(id, dto);
	}


	@Resource(name = "ayudaDinamicaEPService")
	public void setService(AyudaDinamicaEPService ayudaDinamicaEPService) {
		super.service = ayudaDinamicaEPService;
	}

}