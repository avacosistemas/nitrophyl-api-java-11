package ar.com.avaco.nitrophyl.service.cliente;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.cliente.ClienteDomicilio;
import ar.com.avaco.nitrophyl.repository.cliente.ClienteDomicilioRepository;

@Transactional
@Service("clienteDomicilioService")
public class ClienteDomicilioServiceImpl extends NJBaseService<Long, ClienteDomicilio, ClienteDomicilioRepository> implements ClienteDomicilioService {

	@Resource(name = "clienteDomicilioRepository")
	void setRepository(ClienteDomicilioRepository clienteDomicilioRepository) {
		this.repository = clienteDomicilioRepository;
	}

}
