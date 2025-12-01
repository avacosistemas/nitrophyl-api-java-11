package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.cliente.ClienteDomicilio;
import ar.com.avaco.nitrophyl.service.cliente.ClienteDomicilioService;
import ar.com.avaco.nitrophyl.ws.dto.ClienteDomicilioDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("clienteDomicilioEPService")
public class ClienteDomicilioEPServiceImpl
		extends CRUDAuditableEPBaseService<Long, ClienteDomicilioDTO, ClienteDomicilio, ClienteDomicilioService>
		implements ClienteDomicilioEPService {

	@Override
	protected ClienteDomicilio convertToEntity(ClienteDomicilioDTO dto) {
		ClienteDomicilio cd = new ClienteDomicilio();
		cd.setDomicilio(dto.getDomicilio());
		cd.setCliente(Cliente.ofId(dto.getIdCliente()));
		cd.setTipo(dto.getTipo());
		cd.setId(dto.getId());
		return cd;
	}

	@Override
	protected ClienteDomicilioDTO convertToDto(ClienteDomicilio entity) {
		ClienteDomicilioDTO dto = new ClienteDomicilioDTO();
		dto.setDomicilio(entity.getDomicilio());
		dto.setTipo(entity.getTipo());
		dto.setId(entity.getId());
		dto.setIdCliente(entity.getCliente().getId());
		return dto;
	}

	public ClienteDomicilioEPServiceImpl() {
		super(ClienteDomicilio.class, ClienteDomicilioDTO.class);
	}

	@Override
	@Resource(name = "clienteDomicilioService")
	protected void setService(ClienteDomicilioService service) {
		this.service = service;
	}

}