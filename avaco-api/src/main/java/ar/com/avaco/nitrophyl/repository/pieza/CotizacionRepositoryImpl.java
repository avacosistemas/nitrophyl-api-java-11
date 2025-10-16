package ar.com.avaco.nitrophyl.repository.pieza;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Cotizacion;
import ar.com.avaco.nitrophyl.ws.dto.CotizacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.CotizacionFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;

@Repository("cotizacionRepository")
public class CotizacionRepositoryImpl extends NJBaseRepository<Long, Cotizacion> implements CotizacionRepositoryCustom {

	public CotizacionRepositoryImpl(EntityManager entityManager) {
		super(Cotizacion.class, entityManager);
	}

	@Override
	public PageDTO<CotizacionDTO> list(CotizacionFilterDTO filter) {

		Long idCliente = filter.getIdCliente();
		Boolean soloVigentes = filter.getSoloVigentes();
		Long idPieza = filter.getIdPieza();
		String idx = filter.getIdx();
		Boolean asc = filter.getAsc();
		Integer first = filter.getFirst();
		Integer rows = filter.getRows();

		// --------- Query base ---------
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT new ar.com.avaco.nitrophyl.ws.dto.CotizacionDTO(")
				.append(" c.id, ")
				.append(" c.piezaCliente.cliente.nombre, ")
				.append(" c.piezaCliente.cliente.id, ")
				.append(" c.piezaCliente.pieza.denominacion, ")
				.append(" c.piezaCliente.pieza.id, ")
				.append(" c.piezaCliente.pieza.detalleFormula.formula.nombre, ").append(" c.valor, ")
				.append(" c.fecha, ")
				.append(" c.observaciones , ")
				.append(" c.piezaCliente.pieza.revision) ")
				.append(" FROM Cotizacion c ")
				.append(" WHERE 1=1 ");

		if (idCliente != null) {
			sb.append(" AND c.piezaCliente.cliente.id = :idCliente ");
		}
		if (idPieza != null) {
			sb.append(" AND c.piezaCliente.pieza.codigo = :codigo ");
		}

		if (Boolean.TRUE.equals(soloVigentes)) {
			sb.append(" AND c.fecha = ( ")
			.append("   SELECT MAX(c2.fecha) FROM Cotizacion c2 ")
			.append("   WHERE c2.piezaCliente.cliente.id = c.piezaCliente.cliente.id ")
			.append("   AND c2.piezaCliente.pieza.id = c.piezaCliente.pieza.id ").append(" ) ");
		}

		// --------- Orden ---------
		if (idx != null && !idx.isEmpty()) {
			sb.append(" ORDER BY c.").append(idx);
			if (Boolean.TRUE.equals(asc)) {
				sb.append(" ASC");
			} else {
				sb.append(" DESC");
			}
		}

		TypedQuery<CotizacionDTO> query = entityManager.createQuery(sb.toString(), CotizacionDTO.class);

		if (idCliente != null) {
			query.setParameter("idCliente", idCliente);
		}
		if (idPieza != null) {
			query.setParameter("codigo", filter.getCodigo());
		}

		if (first != null && first > 0) {
			query.setFirstResult(first - 1);
		}
		if (rows != null && rows > 0) {
			query.setMaxResults(rows);
		}

		List<CotizacionDTO> resultList = query.getResultList();

		// --------- Query de conteo ---------
		StringBuilder countSb = new StringBuilder();
		countSb.append("SELECT COUNT(c) FROM Cotizacion c WHERE 1=1 ");

		if (idCliente != null) {
			countSb.append(" AND c.piezaCliente.cliente.id = :idCliente ");
		}
		if (idPieza != null) {
			countSb.append(" AND c.piezaCliente.pieza.id = :idPieza ");
		}

		if (Boolean.TRUE.equals(soloVigentes)) {
			countSb.append(" AND c.fecha = ( ").append("   SELECT MAX(c2.fecha) FROM Cotizacion c2 ")
					.append("   WHERE c2.piezaCliente.cliente.id = c.piezaCliente.cliente.id ")
					.append("   AND c2.piezaCliente.pieza.id = c.piezaCliente.pieza.id ").append(" ) ");
		}

		TypedQuery<Long> countQuery = entityManager.createQuery(countSb.toString(), Long.class);

		if (idCliente != null) {
			countQuery.setParameter("idCliente", idCliente);
		}
		if (idPieza != null) {
			countQuery.setParameter("idPieza", idPieza);
		}

		Long totalReg = countQuery.getSingleResult();

		// --------- Armar DTO ---------
		PageDTO<CotizacionDTO> dto = new PageDTO<>();
		dto.setPage(resultList);
		dto.setTotalReg(totalReg);

		return dto;
	}

}