package ar.com.avaco.nitrophyl.repository.molde;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.molde.MoldeBoca;

public interface MoldeBocaRepository extends NJRepository<Long, MoldeBoca>, MoldeBocaRepositoryCustom {

	List<MoldeBoca> findByIdMoldeOrderByNroBoca(Long idMolde);

	void deleteByIdMolde(Long idMolde);

	@Modifying
	@Query(value = "UPDATE molde_boca SET NROBOCA = NROBOCA - 1 WHERE id_molde = ?1 AND NROBOCA > ?2", nativeQuery = true)
	void reacomodarNumerosBoca(Long idMolde, Integer numeroBocaEliminado);

}
