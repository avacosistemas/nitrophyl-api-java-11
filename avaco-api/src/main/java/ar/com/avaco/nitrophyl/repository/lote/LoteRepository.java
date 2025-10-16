package ar.com.avaco.nitrophyl.repository.lote;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.lote.EstadoLote;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;

public interface LoteRepository extends NJRepository<Long, Lote>, LoteRepositoryCustom {

	@Modifying
	@Query("update Lote l set l.estado = ?2, l.observacionesEstado = ?3, l.fechaEstado = ?4 where l.id = ?1")
	void updateEstadoLote(Long idLote, EstadoLote estado, String observaciones, Date fecha);

}
