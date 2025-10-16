package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Cotizacion;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.repository.pieza.CotizacionRepository;
import ar.com.avaco.nitrophyl.repository.pieza.PiezaRepository;
import ar.com.avaco.nitrophyl.ws.dto.CotizacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.CotizacionFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;

@Transactional
@Service("cotizacionService")
public class CotizacionServiceImpl extends NJBaseService<Long, Cotizacion, CotizacionRepository>
		implements CotizacionService {

	private PiezaRepository piezaRepository;

	@Override
	public Cotizacion getCotizacionVigente(Long idPiezaCliente) {
		return this.repository.findFirstByPiezaClienteIdOrderByFechaDesc(idPiezaCliente).orElse(null);
	}

	@Override
	public PageDTO<CotizacionDTO> listCotizaciones(CotizacionFilterDTO filter) {
		
		if (filter.getIdPieza() != null) {
			Pieza one = piezaRepository.getOne(filter.getIdPieza());
			filter.setCodigo(one.getCodigo());
		}
		
		return this.repository.list(filter);
	}
	
	@Resource(name = "cotizacionRepository")
	void setRepository(CotizacionRepository cotizacionRepository) {
		this.repository = cotizacionRepository;
	}
	
	@Resource(name = "piezaRepository")
	public void setPiezaRepository(PiezaRepository piezaRepository) {
		this.piezaRepository = piezaRepository;
	}
	
	
}
