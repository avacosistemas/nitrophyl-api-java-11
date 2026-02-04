package ar.com.avaco.nitrophyl.service.molde;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.molde.Molde;
import ar.com.avaco.nitrophyl.repository.molde.MoldeFotoRepository;
import ar.com.avaco.nitrophyl.repository.molde.MoldePlanoRepository;
import ar.com.avaco.nitrophyl.repository.molde.MoldeRepository;
import ar.com.avaco.nitrophyl.ws.dto.MoldeFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeListadoDTO;

@Service("moldeService")
public class MoldeServiceImpl extends NJBaseService<Long, Molde, MoldeRepository> implements MoldeService {

	@Autowired
	private MoldePlanoRepository moldePlanoRepository;

	@Autowired
	private MoldeFotoRepository moldeFotoRepository;
	
	@Override
	public Molde save(Molde entity) {
		Molde save = super.save(entity);
		// Revisa si hay faltantes
		this.actualizarFaltantes(entity.getId());
		return save;
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
	
	@Override
	public void actualizarFaltantes(Long idMolde) {
		String faltantes = this.repository.getFaltantesById(idMolde);
		// Si tiene algo pendiente reviso
		if (StringUtils.isNotBlank(faltantes)) {
			StringBuilder sb = new StringBuilder();
			
			boolean plano = this.moldePlanoRepository.existsByMoldeIdAndMoldePropioTrue(idMolde);
			if (!plano) {
				sb.append("Falta cargar un plano");
			}
			
			boolean foto = this.moldeFotoRepository.existsByMoldeId(idMolde);
			if (!foto) {
				sb.append("Falta cargar una foto");
			}
			
			this.repository.actualizarFaltantes(idMolde, sb.toString());
		}
		
	}

	@Resource(name = "moldeRepository")
	void setMoldeRepository(MoldeRepository moldeRepository) {
		this.repository = moldeRepository;
	}
	
}
