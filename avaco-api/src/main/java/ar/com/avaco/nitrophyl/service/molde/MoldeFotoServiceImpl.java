package ar.com.avaco.nitrophyl.service.molde;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeFoto;
import ar.com.avaco.nitrophyl.repository.molde.MoldeFotoRepository;

@Transactional
@Service("moldeFotoService")
public class MoldeFotoServiceImpl extends NJBaseService<Long, MoldeFoto, MoldeFotoRepository>
		implements MoldeFotoService {

	private Logger logger = LogManager.getLogger(getClass());

	@Resource(name = "moldeFotoRepository")
	void setMoldeFotoRepository(MoldeFotoRepository moldeFotoRepository) {
		this.repository = moldeFotoRepository;
	}

	@Override
	public List<MoldeFoto> listByMoldeId(Long idMolde) {
		return this.repository.findAllByIdMoldeOrderByFechaDesc(idMolde);
	}

	@Override
	public MoldeFoto getUltimoRegistro(Long idMolde) {
		return this.repository.findFirstByIdMoldeOrderByFechaDesc(idMolde);
	}

	@Override
	public MoldeFoto addMoldeFoto(MoldeFoto moldeFoto) throws ErrorValidationException, BusinessException {
		validarMoldeFoto(moldeFoto);
		MoldeFoto lastMoldeFoto = this.repository
				.findFirstByNombreArchivoOrderByVersionDesc(moldeFoto.getNombreArchivo());

		if (lastMoldeFoto != null) {
			moldeFoto.setVersion(lastMoldeFoto.getVersion() + 1);
		}

		return this.repository.save(moldeFoto);
	}

	// Valida los campos para el molde foto
	private void validarMoldeFoto(MoldeFoto moldeFoto) throws ErrorValidationException, BusinessException {
		Map<String, String> errores = new HashMap<>();

		if (moldeFoto == null) {
			throw new BusinessException("Molde Foto vaco.");
		}

		if (StringUtils.isBlank(moldeFoto.getNombreArchivo())) {
			errores.put("nombreArchivo", "El campo Nombre Archivo es requerido.");
		}

		if (moldeFoto.getArchivo() == null) {
			errores.put("archivo", "El campo Archivo es requerido.");
		}

		if (!errores.isEmpty()) {
			logger.error("Se encontraron los siguientes errores");
			errores.values().forEach((x -> logger.error(x)));
			throw new ErrorValidationException("Se encontraron los siguientes errores", errores);
		}

	}

}
