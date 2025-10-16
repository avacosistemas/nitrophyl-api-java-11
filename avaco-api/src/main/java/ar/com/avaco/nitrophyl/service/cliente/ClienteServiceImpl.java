package ar.com.avaco.nitrophyl.service.cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Contacto;
import ar.com.avaco.nitrophyl.domain.entities.cliente.TipoContacto;
import ar.com.avaco.nitrophyl.repository.cliente.ClienteRepository;
import ar.com.avaco.nitrophyl.repository.cliente.ContactoRepository;

@Transactional
@Service("clienteService")
public class ClienteServiceImpl extends NJBaseService<Long, Cliente, ClienteRepository> implements ClienteService {

	private Logger logger = LogManager.getLogger(getClass());

	private ContactoRepository contactoRepository;

	@Override
	public Cliente getCliente(Long id) {
		return this.repository.findById(id).orElse(null);
	}

	@Override
	public List<Contacto> getContactosByCliente(Long idCliente) {
		return this.contactoRepository.findAllByClienteId(idCliente);
	}

	@Override
	public Contacto getContacto(Long idContactoCliente) {
		return this.contactoRepository.findById(idContactoCliente).orElse(null);
	}

	@Override
	public Cliente addCliente(Cliente clienteToAdd) throws ErrorValidationException, BusinessException {
		validarCliente(clienteToAdd);
		clienteToAdd.setActivo(true);
		clienteToAdd = this.repository.save(clienteToAdd);
		return clienteToAdd;
	}

	@Override
	public Cliente updateCliente(Cliente clienteToUpdate) throws ErrorValidationException, BusinessException {
		validarCliente(clienteToUpdate);
		clienteToUpdate = this.repository.save(clienteToUpdate);
		return clienteToUpdate;
	}

	@Override
	public Contacto addContactoCliente(Long idCliente, Contacto contactoToAdd)
			throws ErrorValidationException, BusinessException {

		Cliente cliente = repository.findById(idCliente).orElse(null);

		if (cliente == null)
			throw new EntityNotFoundException("No se encontro el cliente con ID: " + idCliente);

		contactoToAdd.setCliente(cliente);
		validarContactoCliente(contactoToAdd, cliente.getContactos());

		contactoToAdd = this.contactoRepository.save(contactoToAdd);

		return contactoToAdd;
	}

	@Override
	public Contacto updateContactoCliente(Contacto contacto) throws ErrorValidationException, BusinessException {

		Contacto contactoToUpdate = contactoRepository.findById(contacto.getId()).orElse(null);

		validarContactoCliente(contactoToUpdate, contactoToUpdate.getCliente().getContactos());

		contactoToUpdate.setNombre(contacto.getNombre());
		contactoToUpdate.setEmail(contacto.getEmail());
		contactoToUpdate.setTelefono(contacto.getTelefono());
		contactoToUpdate.setTipo(contacto.getTipo());

		contactoToUpdate = this.contactoRepository.save(contactoToUpdate);
		return contactoToUpdate;
	}

	// Valida los campos para el cliente
	private void validarCliente(Cliente cliente) throws ErrorValidationException, BusinessException {
		Map<String, String> errores = new HashMap<>();

		if (cliente == null) {
			throw new BusinessException("Cliente vaco.");
		}

		if (StringUtils.isBlank(cliente.getRazonSocial())) {
			errores.put("razonSocial", "El campo Razon Social es requerido.");
		} else {

			Cliente cliByRazonSocial = getRepository().findByRazonSocialEqualsIgnoreCase(cliente.getRazonSocial());

			if (cliByRazonSocial != null && cliente.getId() == null || cliByRazonSocial != null
					&& cliente.getId() != null && !cliente.getId().equals(cliByRazonSocial.getId())) {
				errores.put("razonSocial", "La Razon Social no esta disponible. Intente otra diferente.");
			}
		}

		if (StringUtils.isBlank(cliente.getNombre())) {
			errores.put("nombre", "El campo Nombre es requerido.");
		} else {

			Cliente cliByNombre = getRepository().findByNombreEqualsIgnoreCase(cliente.getNombre());

			if (cliByNombre != null && cliente.getId() == null
					|| cliByNombre != null && cliente.getId() != null && !cliente.getId().equals(cliByNombre.getId())) {
				errores.put("nombre", "El nombre no esta disponible. Intente otro diferente.");
			}
		}

		if (!errores.isEmpty()) {
			logger.error("Se encontraron los siguientes errores");
			errores.values().forEach((x -> logger.error(x)));
			throw new ErrorValidationException("Se encontraron los siguientes errores", errores);
		}

	}

	// Valida los campos para el contacto del cliente
	private void validarContactoCliente(Contacto contacto, Set<Contacto> contactos)
			throws ErrorValidationException, BusinessException {
		Map<String, String> errores = new HashMap<>();

		if (contacto == null) {
			throw new BusinessException("Contacto vaco.");
		}

		Contacto existContacto = contactos.stream()
				.filter(c -> (contacto.getId() == null || (contacto.getId() != null && contacto.getId() != c.getId()))
						&& c.getCliente().getId().equals(contacto.getCliente().getId())
						&& c.getNombre().equals(contacto.getNombre()) && c.getTipo().equals(contacto.getTipo()))
				.findFirst().orElse(null);

		if (existContacto != null) {
			throw new BusinessException("Ya existe un contacto con el mismo nombre y tipo para este cliente.");
		}

		if (StringUtils.isBlank(contacto.getNombre())) {
			errores.put("nombreContacto", "El campo Nombre es requerido.");
		} else if (contacto.getNombre().length() > 30) {
			errores.put("nombreContacto", "El campo Nombre no debe superar los 30 caracteres");
		}

		if (contacto.getTipo() == null) {
			errores.put("tipoContacto", "El campo Tipo es requerido.");
		}

		if (!errores.isEmpty()) {
			logger.error("Se encontraron los siguientes errores");
			errores.values().forEach((x -> logger.error(x)));
			throw new ErrorValidationException("Se encontraron los siguientes errores", errores);
		}

	}

	@Override
	public List<Cliente> getByIds(List<Long> ids) {
		return this.repository.findByIdIn(ids);
	}

	@Override
	public String getCorreoInformes(Long idCliente) {
		List<Contacto> contactos = this.getContactosByCliente(idCliente).stream()
				.filter(x -> x.getTipo().equals(TipoContacto.INFORMES)).collect(Collectors.toList());
		if (contactos != null && !contactos.isEmpty()) {
			if (contactos.size() == 1) {
				return contactos.get(0).getEmail();
			} else {
				List<String> emails = contactos.stream().map(Contacto::getEmail).collect(Collectors.toList());
				return StringUtils.join(emails, " - ");
			}
		}
		throw new ErrorValidationException(
				"El cliente seleccionado no tiene un correo electrnico de informes definido.", null);
	}

	@Override
	public List<String> getCorreoInformesList(Long idCliente) {
		List<Contacto> contactos = this.getContactosByCliente(idCliente).stream().filter(x->x.getTipo().equals(TipoContacto.INFORMES)).collect(Collectors.toList());
		if (contactos != null && !contactos.isEmpty()) {
			return contactos.stream().map(Contacto::getEmail).collect(Collectors.toList());
		}
		throw new ErrorValidationException("El cliente seleccionado no tiene un correo electrnico de informes definido.", null);
	}

	@Resource(name = "contactoRepository")
	void setContactoRepository(ContactoRepository contactoRepository) {
		this.contactoRepository = contactoRepository;
	}

	@Resource(name = "clienteRepository")
	void setClienteRepository(ClienteRepository clienteRepository) {
		this.repository = clienteRepository;
	}

}