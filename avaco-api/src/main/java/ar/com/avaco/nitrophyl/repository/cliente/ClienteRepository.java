package ar.com.avaco.nitrophyl.repository.cliente;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;

public interface ClienteRepository extends NJRepository<Long, Cliente>, ClienteRepositoryCustom {

	Cliente findByRazonSocialEqualsIgnoreCase(String razonSocial);

	Cliente findByNombreEqualsIgnoreCase(String nombre);

	List<Cliente> findByIdIn(List<Long> ids);
	
	//List<Contacto> findByIdMolde(Long idMolde);
	
	//Cliente findByUsernameEquals(String username);

	//Cliente findByUsernameEqualsIgnoreCase(String username);

	//Cliente findByEmailEqualsIgnoreCase(String email);

	//Cliente findByIdentificacionNumeroEqualsIgnoreCase(String numero);

	//Cliente findByIdentificacionNumeroLikeIgnoreCase(String numero);

	//TODO No existe cuenta bancaria, revisar
	//Cliente findByCuentaBancariaAliasEqualsIgnoreCase(String cbu);

	//Cliente findByCuentaBancariaCbuEqualsIgnoreCase(String cbu);

	//Cliente findByCuentaBancariaAliasEqualsIgnoreCaseAndIdNot(String cbu, Long id);
	
	//Cliente findByCuentaBancariaCbuEqualsIgnoreCaseAndIdNot(String cbu, Long id);

}
