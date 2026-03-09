package ar.com.avaco.nitrophyl.service.molde;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.nitrophyl.domain.entities.molde.MoldeFoto;

public interface MoldeFotoService extends NJService<Long, MoldeFoto> {

	List<MoldeFoto> listByMoldeId(Long idMolde);

	MoldeFoto getUltimoRegistro(Long idMolde);

	MoldeFoto addMoldeFoto(MoldeFoto moldeFoto) throws ErrorValidationException, BusinessException;

}
