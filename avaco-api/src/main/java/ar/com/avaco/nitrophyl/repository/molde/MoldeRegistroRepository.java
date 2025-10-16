package ar.com.avaco.nitrophyl.repository.molde;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeRegistro;

public interface MoldeRegistroRepository extends NJRepository<Long, MoldeRegistro>, MoldeRegistroRepositoryCustom {

	List<MoldeRegistro> findAllByIdMoldeOrderByFechaDesc(Long idMolde);

	MoldeRegistro findFirstByIdMoldeOrderByFechaDesc(Long idMolde);

}
