package ar.com.avaco.nitrophyl.repository.pieza;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaCliente;

public interface PiezaClienteRepository extends NJRepository<Long, PiezaCliente>, PiezaClienteRepositoryCustom {

	@Query("SELECT pc FROM PiezaCliente pc WHERE pc.pieza.id = :piezaId AND pc.cliente.id = :clienteId")
	Optional<PiezaCliente> findByPiezaAndCliente(@Param("piezaId") Long piezaId, @Param("clienteId") Long clienteId);

}