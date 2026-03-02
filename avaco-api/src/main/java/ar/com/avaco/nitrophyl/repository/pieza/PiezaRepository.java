package ar.com.avaco.nitrophyl.repository.pieza;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;

public interface PiezaRepository extends NJRepository<Long, Pieza>, PiezaRepositoryCustom {

	Pieza findByCodigoAndVigente(String codigoInterno, boolean b);

	boolean existsByDetalleFormulaFormulaId(Long idFormula);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("UPDATE Pieza p SET p.faltantes = ?2 where p.id = ?1")
	void actualizarFaltantes(Long idPieza, String faltantes);
	
}
