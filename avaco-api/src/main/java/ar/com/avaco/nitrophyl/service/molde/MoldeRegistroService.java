package ar.com.avaco.nitrophyl.service.molde;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.molde.MoldeRegistro;

public interface MoldeRegistroService extends NJService<Long, MoldeRegistro> {

	List<MoldeRegistro> listByMoldeId(Long idMolde);

	MoldeRegistro getUltimoRegistro(Long idMolde);

	MoldeRegistro registrarIngreso(String comentarios, Long idMolde);
	
	MoldeRegistro registrarEgreso(String comentarios, Long idMolde);

}
