package ar.com.avaco.nitrophyl.repository.pieza;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.esquema.Esquema;

public interface EsquemaRepository extends NJRepository<Long, Esquema>, EsquemaRepositoryCustom {

	@Modifying
	@Transactional
	@Query(value = "SELECT mover_esquema(:idEsquema, :nuevaPos)", nativeQuery = true)
	void moverEsquema(@Param("idEsquema") Long idEsquema,
	                  @Param("nuevaPos") Integer nuevaPos);
	
}