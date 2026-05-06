package ar.com.avaco.nitrophyl.service.pieza;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.nitrophyl.domain.entities.pieza.cliente.PiezaCliente;
import ar.com.avaco.nitrophyl.repository.pieza.PiezaClienteRepository;

@Transactional
@Service("piezaClienteService")
public class PiezaClienteServiceImpl extends NJBaseService<Long, PiezaCliente, PiezaClienteRepository>
		implements PiezaClienteService {

	@Autowired
	private CotizacionService cotizacionService;
	
	@Resource(name = "piezaClienteRepository")
	void setRepository(PiezaClienteRepository piezaClienteRepository) {
		this.repository = piezaClienteRepository;
	}

	@Override
	public PiezaCliente getByPiezaCliente(Long idCliente, Long idPieza) {
		Optional<PiezaCliente> byPiezaAndCliente = repository.findByPiezaAndCliente(idPieza, idCliente);
		if (byPiezaAndCliente.isPresent())
			return byPiezaAndCliente.get();
		return null;
	}
	
	@Override
	public void remove(Long id) {
		if (cotizacionService.getCotizacionVigente(id) != null) {
			throw new ErrorValidationException("No se puede desasociar el cliente porque tiene una o varias cotizaciones asociadas");
		}
		super.remove(id);
	}

}
