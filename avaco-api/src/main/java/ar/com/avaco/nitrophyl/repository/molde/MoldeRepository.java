package ar.com.avaco.nitrophyl.repository.molde;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.molde.Molde;

public interface MoldeRepository extends NJRepository<Long, Molde>, MoldeRepositoryCustom {

	@Modifying
	@Query("update Molde m set m.cantidadBocas = (m.cantidadBocas + 1), m.usuarioActualizacion = ?2, m.fechaActualizacion = CURRENT_TIMESTAMP where m.id = ?1")
	void incrementarNroBocas(Long idMolde, String username);

	@Modifying
	@Query("UPDATE Molde m SET m.cantidadBocas = (m.cantidadBocas - 1),  m.usuarioActualizacion = ?2, m.fechaActualizacion = CURRENT_TIMESTAMP WHERE m.id = (SELECT mb.molde.id FROM MoldeBoca mb WHERE mb.id = ?1)")
	void disminuirNroBocas(Long idMoldeBoca, String username);

	@Query("SELECT m.cantidadBocas FROM Molde m WHERE m.id = ?1")
	Integer getCantidadBocas(Long idMolde);
}
