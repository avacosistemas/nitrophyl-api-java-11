package ar.com.avaco.nitrophyl.service.pieza;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.repository.pieza.PiezaRepository;
import ar.com.avaco.nitrophyl.ws.dto.PiezaFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaGrillaDTO;

@Service("piezaService")
public class PiezaServiceImpl extends NJBaseService<Long, Pieza, PiezaRepository> implements PiezaService {

	@Resource(name = "piezaRepository")
	void setClienteRepository(PiezaRepository piezaRepository) {
		this.repository = piezaRepository;
	}

	@Override
	public List<PiezaGrillaDTO> listGrilla(PiezaFilterDTO pfdto) {
		return this.repository.listGrilla(pfdto);
	}

	@Override
	public Pieza getVigenteByCodigoInterno(String codigoInterno) {
		return this.repository.findByCodigoAndVigente(codigoInterno, true);
	}

}
