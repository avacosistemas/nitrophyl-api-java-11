package ar.com.avaco.nitrophyl.service.molde;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.molde.Molde;
import ar.com.avaco.nitrophyl.repository.molde.MoldeRepository;
import ar.com.avaco.nitrophyl.ws.dto.MoldeFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeListadoDTO;

@Service("moldeService")
public class MoldeServiceImpl extends NJBaseService<Long, Molde, MoldeRepository> implements MoldeService {

	@Resource(name = "moldeRepository")
	void setMoldeRepository(MoldeRepository moldeRepository) {
		this.repository = moldeRepository;
	}

	public List<MoldeListadoDTO> list(MoldeFilterDTO filter) {
		return this.repository.list(filter);
	}

	@Override
	public void incrementarBocas(Long idMolde) {
		this.repository.incrementarNroBocas(idMolde, SecurityContextHolder.getContext().getAuthentication().getName());	
	}

	@Override
	public void disminuirBocas(Long idMolde) {
		this.repository.disminuirNroBocas(idMolde, SecurityContextHolder.getContext().getAuthentication().getName());	
	}

	@Override
	public Integer getCantidadBocas(Long idMolde) {
		return this.repository.getCantidadBocas(idMolde);
	}

}
