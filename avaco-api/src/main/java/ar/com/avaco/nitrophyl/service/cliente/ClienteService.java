package ar.com.avaco.nitrophyl.service.cliente;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Contacto;

public interface ClienteService extends NJService<Long, Cliente> {

	Cliente getCliente(Long id);

	List<Contacto> getContactosByCliente(Long idCliente);

	Contacto getContacto(Long idContactoCliente);

	Contacto addContactoCliente(Long idCliente, Contacto contactoToAdd)
			throws ErrorValidationException, BusinessException;

	Contacto updateContactoCliente(Contacto contactoToUpdate) throws ErrorValidationException, BusinessException;

	Cliente addCliente(Cliente clienteToAdd) throws ErrorValidationException, BusinessException;

	Cliente updateCliente(Cliente clienteToUpdate) throws ErrorValidationException, BusinessException;

	List<Cliente> getByIds(List<Long> collect);

	String getCorreoInformes(Long idCliente);

	List<String> getCorreoInformesList(Long idCliente);

	void deleteContacto(Long idContactoCliente);

}
