package ar.com.avaco.ws.rest.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.dto.RegistroEnvioInformeCalidadDTO;
import ar.com.avaco.nitrophyl.ws.dto.RegistroEnvioInformeCalidadFilterDTO;
import ar.com.avaco.nitrophyl.ws.service.RegistroEnvioInformeCalidadEPService;
import ar.com.avaco.nitrophyl.ws.service.filter.RegistroEnvioInformeCalidadFilter;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class RegistroEnvioInformeCalidadController extends
		AbstractAuditableDTORestController<RegistroEnvioInformeCalidadDTO, Long, RegistroEnvioInformeCalidadEPService> {

	@RequestMapping(value = "/registroEnvioInformeCalidad", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(RegistroEnvioInformeCalidadFilterDTO filter) {
		PageDTO<RegistroEnvioInformeCalidadDTO> pageDTO = this.service
				.listFilterCount(new RegistroEnvioInformeCalidadFilter(filter));
		return OK(pageDTO);
	}

	@Override
	@Resource(name = "registroEnvioInformeCalidadEPService")
	public void setService(RegistroEnvioInformeCalidadEPService service) {
		this.service = service;
	}

}