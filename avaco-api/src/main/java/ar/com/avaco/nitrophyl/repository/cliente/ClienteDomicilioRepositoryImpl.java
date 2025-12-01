package ar.com.avaco.nitrophyl.repository.cliente;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.cliente.ClienteDomicilio;

@Repository("clienteDomicilioRepository")
public class ClienteDomicilioRepositoryImpl extends NJBaseRepository<Long, ClienteDomicilio> implements ClienteDomicilioRepositoryCustom {

	public ClienteDomicilioRepositoryImpl(EntityManager entityManager) {
		super(ClienteDomicilio.class, entityManager);
	}

}