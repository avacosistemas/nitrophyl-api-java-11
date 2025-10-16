package ar.com.avaco.nitrophyl.repository.pieza;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaCliente;

public interface PiezaClienteRepository extends NJRepository<Long, PiezaCliente>, PiezaClienteRepositoryCustom {

	PiezaCliente findByClienteIdAndPiezaId(Long idCliente, Long idPieza);

}