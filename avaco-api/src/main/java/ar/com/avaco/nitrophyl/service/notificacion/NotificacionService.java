package ar.com.avaco.nitrophyl.service.notificacion;

import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;

public interface NotificacionService {

	void notificarResetoPassword(Cliente cliente, String tmppass);

	void notificarRegistroClienteNuevoPassword(Cliente cliente, String tmpass);

	void notificarHabilitacionExitosa(Cliente cliente);
	
	void notificarInicioServer();

}
