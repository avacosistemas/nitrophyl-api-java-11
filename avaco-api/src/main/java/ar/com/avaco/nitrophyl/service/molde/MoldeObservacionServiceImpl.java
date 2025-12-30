package ar.com.avaco.nitrophyl.service.molde;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.molde.MoldeObservacion;
import ar.com.avaco.nitrophyl.repository.molde.MoldeObservacionRepository;

@Transactional
@Service("moldeObservacionService")
public class MoldeObservacionServiceImpl extends NJBaseService<Long, MoldeObservacion, MoldeObservacionRepository>
		implements MoldeObservacionService {

	@Resource(name = "moldeObservacionRepository")
	void setMoldeFotoRepository(MoldeObservacionRepository moldeObservacionRepository) {
		this.repository = moldeObservacionRepository;
	}

	@Override
	public List<MoldeObservacion> listByMoldeId(Long idMolde) {
		return this.repository.findByIdMoldeOrderByFechaCreacionDesc(idMolde);
	}

}
