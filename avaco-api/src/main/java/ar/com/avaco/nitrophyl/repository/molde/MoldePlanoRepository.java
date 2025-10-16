package ar.com.avaco.nitrophyl.repository.molde;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldePlano;

public interface MoldePlanoRepository extends NJRepository<Long, MoldePlano>, MoldePlanoRepositoryCustom {

	List<MoldePlano> findAllByIdMoldeOrderByFechaDesc(Long idMolde);

	MoldePlano findFirstByIdMoldeOrderByFechaDesc(Long idMolde);

	MoldePlano findFirstByNombreArchivoOrderByVersionDesc(String nombreArchivo);
	
}
