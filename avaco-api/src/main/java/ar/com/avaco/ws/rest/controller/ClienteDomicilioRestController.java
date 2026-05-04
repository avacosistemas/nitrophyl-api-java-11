package ar.com.avaco.ws.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.domain.entities.cliente.TipoContacto;
import ar.com.avaco.nitrophyl.ws.dto.ClienteDomicilioDTO;
import ar.com.avaco.nitrophyl.ws.service.ClienteDomicilioEPService;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class ClienteDomicilioRestController
		extends AbstractAuditableDTORestController<ClienteDomicilioDTO, Long, ClienteDomicilioEPService> {

	@RequestMapping(value = "/clienteDomicilio", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(@RequestParam Long idCliente,
			@RequestParam(required = false) String tipoDomicilio) {
		List<ClienteDomicilioDTO> listEq = this.service.listEq("cliente.id", idCliente);
		if (StringUtils.isNotBlank(tipoDomicilio))
			listEq = listEq.stream().filter(x -> x.getTipo().equals(TipoContacto.valueOf(tipoDomicilio))).collect(Collectors.toList());
		return OK(listEq);
	}

	@Override
	@RequestMapping(value = "/clienteDomicilio", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> create(@RequestBody ClienteDomicilioDTO dto) throws BusinessException {
		return super.create(dto);
	}

	@Override
	@RequestMapping(value = "/clienteDomicilio/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> update(@PathVariable Long id, @RequestBody ClienteDomicilioDTO dto)
			throws BusinessException {
		dto.setId(id);
		return super.update(id, dto);
	}

	@Override
	@RequestMapping(value = "/clienteDomicilio/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> delete(@PathVariable Long id) throws BusinessException {
		return super.delete(id);
	}

	@Resource(name = "clienteDomicilioEPService")
	public void setService(ClienteDomicilioEPService clienteDomicilioEPService) {
		super.service = clienteDomicilioEPService;
	}

}