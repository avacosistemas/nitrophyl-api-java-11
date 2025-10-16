package ar.com.avaco.nitrophyl.service.molde;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldePlano;

public interface MoldePlanoService extends NJService<Long, MoldePlano> {

	List<MoldePlano> listByMoldeId(Long idMolde);

	MoldePlano getUltimoRegistro(Long idMolde);

	MoldePlano addMoldePlano(MoldePlano moldePlano) throws ErrorValidationException, BusinessException;


}
