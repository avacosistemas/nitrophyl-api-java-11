package ar.com.avaco.nitrophyl.service.pieza;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaCliente;

public interface PiezaClienteService extends NJService<Long, PiezaCliente> {

	PiezaCliente getByPiezaCliente(Long idCliente, Long idPieza);

}