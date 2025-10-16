package ar.com.avaco.nitrophyl.repository.cliente;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Contacto;

public interface ContactoRepository extends NJRepository<Long, Contacto>, ContactoRepositoryCustom {
	
	List<Contacto> findAllByClienteId(Long idCliente);

}
