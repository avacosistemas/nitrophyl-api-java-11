package ar.com.avaco.nitrophyl.repository.pieza;

import java.util.Optional;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Cotizacion;

public interface CotizacionRepository extends NJRepository<Long, Cotizacion>, CotizacionRepositoryCustom {

	Optional<Cotizacion> findFirstByPiezaClienteIdOrderByFechaDesc(Long clientePiezaId);

}