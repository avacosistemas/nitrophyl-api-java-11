package ar.com.avaco.nitrophyl.repository.pieza;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.ws.dto.PiezaComboDTO;

public interface PiezaRepository extends NJRepository<Long, Pieza>, PiezaRepositoryCustom {

	Pieza findByCodigoAndVigente(String codigoInterno, boolean b);

	boolean existsByDetalleFormulaFormulaId(Long idFormula);

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("UPDATE Pieza p SET p.faltantes = ?2 where p.id = ?1")
	void actualizarFaltantes(Long idPieza, String faltantes);

	@Query("SELECT DISTINCT new ar.com.avaco.nitrophyl.ws.dto.PiezaComboDTO(p.id, p.denominacion) "
			+ "FROM Pieza p "
			+ "LEFT JOIN p.clientes pc " 
			+ "WHERE p.vigente = true "
			+ "AND (:nombre IS NULL OR LOWER(p.denominacion) LIKE LOWER(CONCAT('%', :nombre, '%'))) "
			+ "AND (:idCliente IS NULL OR pc.cliente.id = :idCliente) order by p.denominacion")
	List<PiezaComboDTO> listPiezasCombo(@Param("nombre") String nombre,
	        @Param("idCliente") Long idCliente);

}
