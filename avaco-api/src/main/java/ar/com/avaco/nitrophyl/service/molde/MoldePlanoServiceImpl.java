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
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldePlano;
import ar.com.avaco.nitrophyl.repository.molde.MoldePlanoRepository;

@Transactional
@Service("moldePlanoService")
public class MoldePlanoServiceImpl extends NJBaseService<Long, MoldePlano, MoldePlanoRepository>
		implements MoldePlanoService {

	private Logger logger = LogManager.getLogger(getClass());

	@Resource(name = "moldePlanoRepository")
	void setMoldePlanoRepository(MoldePlanoRepository moldePlanoRepository) {
		this.repository = moldePlanoRepository;
	}

	@Override
	public List<MoldePlano> listByMoldeId(Long idMolde) {
		return this.repository.findAllByIdMoldeOrderByFechaDesc(idMolde);
	}

	@Override
	public MoldePlano getUltimoRegistro(Long idMolde) {
		return this.repository.findFirstByIdMoldeOrderByFechaDesc(idMolde);
	}

	@Override
	public MoldePlano addMoldePlano(MoldePlano moldePlano) throws ErrorValidationException, BusinessException {
		validarMoldePlano(moldePlano);
		MoldePlano lastMoldePlano = this.repository
				.findFirstByNombreArchivoOrderByVersionDesc(moldePlano.getNombreArchivo());

		if (lastMoldePlano != null) {
			moldePlano.setVersion(lastMoldePlano.getVersion() + 1);
		}

		return this.repository.save(moldePlano);
	}

	// Valida los campos para el molde plano
	private void validarMoldePlano(MoldePlano moldePlano) throws ErrorValidationException, BusinessException {
		Map<String, String> errores = new HashMap<>();

		if (moldePlano == null) {
			throw new BusinessException("Molde Plano vaco.");
		}

		if (StringUtils.isBlank(moldePlano.getNombreArchivo())) {
			errores.put("nombreArchivo", "El campo Nombre Archivo es requerido.");
		}

		if (moldePlano.getArchivo() == null) {
			errores.put("archivo", "El campo Archivo es requerido.");
		}

		if (!errores.isEmpty()) {
			logger.error("Se encontraron los siguientes errores");
			errores.values().forEach((x -> logger.error(x)));
			throw new ErrorValidationException("Se encontraron los siguientes errores", errores);
		}

	}

}
