package ar.com.avaco.nitrophyl.repository.cliente;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.cliente.ClienteDomicilio;

public interface ClienteDomicilioRepository extends NJRepository<Long, ClienteDomicilio>, ClienteDomicilioRepositoryCustom {

}