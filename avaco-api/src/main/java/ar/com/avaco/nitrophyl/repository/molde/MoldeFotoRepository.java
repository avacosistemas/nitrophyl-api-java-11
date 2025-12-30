package ar.com.avaco.nitrophyl.repository.molde;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.molde.MoldeFoto;

public interface MoldeFotoRepository extends NJRepository<Long, MoldeFoto>, MoldeFotoRepositoryCustom {

	List<MoldeFoto> findAllByIdMoldeOrderByFechaDesc(Long idMolde);

	MoldeFoto findFirstByIdMoldeOrderByFechaDesc(Long idMolde);
	
	MoldeFoto findFirstByNombreArchivoOrderByVersionDesc(String nombreArchivo);

}
