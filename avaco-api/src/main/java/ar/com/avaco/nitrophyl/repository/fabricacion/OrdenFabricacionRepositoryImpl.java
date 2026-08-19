package ar.com.avaco.nitrophyl.repository.fabricacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenFabricacion;
import ar.com.avaco.nitrophyl.ws.dto.ListadoOrdenFabricacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenFabricacionFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.utils.DateUtils;

@Repository("ordenFabricacionRepository")
public class OrdenFabricacionRepositoryImpl extends NJBaseRepository<Long, OrdenFabricacion>
		implements OrdenFabricacionRepositoryCustom {

	public OrdenFabricacionRepositoryImpl(EntityManager entityManager) {
		super(OrdenFabricacion.class, entityManager);
	}

	@Override
	public Long obtenerSiguienteNumero(Integer anio) {
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO numerador_ot (anio, ultimo_numero) ");
		sb.append(" VALUES (:anio, 1) ");
		sb.append(" ON CONFLICT (anio) ");
		sb.append(" DO UPDATE ");
		sb.append(" SET ultimo_numero = numerador_ot.ultimo_numero + 1 ");
		sb.append(" RETURNING ultimo_numero ");
		Number result = (Number) entityManager.createNativeQuery(sb.toString()).setParameter("anio", anio)
				.getSingleResult();
		return result.longValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public PageDTO<ListadoOrdenFabricacionDTO> listFilterCount(OrdenFabricacionFilterDTO filter) {

		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		sql.append("     v.id_orden_compra, ");
		sql.append("     v.estado_oc, ");
		sql.append("     v.fecha_oc, ");
		sql.append("     v.total_solicitado, ");
		sql.append("     v.fecha_entrega_solicitada, ");
		sql.append("     v.id_orden_fabricacion, ");
		sql.append("     v.fecha_of, ");
		sql.append("     v.numero, ");
		sql.append("     v.anio, ");
		sql.append("     v.estado_of, ");
		sql.append("     v.id_cliente, ");
		sql.append("     v.cliente_nombre, ");
		sql.append("     v.id_pieza, ");
		sql.append("     v.pieza_codigo, ");
		sql.append("     v.id_formula, ");
		sql.append("     v.formula_nombre, ");
		sql.append("     v.id_seccion_fabrica, ");
		sql.append("     v.seccion_nombre, ");
		sql.append("     v.id_maquina_fabrica, ");
		sql.append("     v.maquina_nombre, ");
		sql.append("     v.total_fabricado, ");
		sql.append("     v.saldo, ");
		sql.append("     COUNT(*) OVER() AS total_registros ");

		sql.append(" FROM vw_seguimiento_fabricacion v ");

		sql.append(" WHERE 1 = 1 ");

		Map<String, Object> params = new HashMap<>();

		if (filter.getIdCliente() != null) {
			sql.append(" AND v.id_cliente = :idCliente ");
			params.put("idCliente", filter.getIdCliente());
		}

		if (filter.getIdPieza() != null) {
			sql.append(" AND v.id_pieza = :idPieza ");
			params.put("idPieza", filter.getIdPieza());
		}

		if (filter.getIdSeccion() != null) {
			sql.append(" AND v.id_seccion_fabrica = :idSeccion ");
			params.put("idSeccion", filter.getIdSeccion());
		}

		if (filter.getIdMaquina() != null) {
			sql.append(" AND v.id_maquina_fabrica = :idMaquina ");
			params.put("idMaquina", filter.getIdMaquina());
		}

		if (filter.getFechaDesde() != null || filter.getFechaHasta() != null) {

			String tipoFecha = "";

			if (StringUtils.isNotBlank(filter.getTipoFecha())) {

				if (filter.getTipoFecha().equals("CREACION_OC")) {
					tipoFecha = "v.fecha_oc";

				} else if (filter.getTipoFecha().equals("CREACION_OF")) {
					tipoFecha = "v.fecha_of";

				} else {
					tipoFecha = "v.fecha_entrega_solicitada";
				}
			}

			if (filter.getFechaDesde() != null) {
				sql.append(" AND " + tipoFecha + " >= :fechaDesde ");
				params.put("fechaDesde", filter.getFechaDesde());
			}

			if (filter.getFechaHasta() != null) {
				sql.append(" AND " + tipoFecha + " <= :fechaHasta ");
				params.put("fechaHasta", filter.getFechaHasta());
			}
		}

		if (filter.getAnioOF() != null) {
			sql.append(" AND v.anio = :anio ");
			params.put("anio", filter.getAnioOF());
		}

		if (filter.getNumeroOF() != null) {
			sql.append(" AND v.numero = :numero ");
			params.put("numero", filter.getNumeroOF());
		}

		if (filter.getEstado() != null) {
			sql.append(" AND v.estado_of = :estadoOF ");
			params.put("estadoOF", filter.getEstado().name());
		}

		if (filter.getIdx() != null && filter.getAsc() != null) {

			sql.append(" ORDER BY ");
			sql.append(filter.getIdx());

			if (filter.getAsc()) {
				sql.append(" ASC ");
			} else {
				sql.append(" DESC ");
			}
		}

		Query query = entityManager.createNativeQuery(sql.toString());

		params.forEach(query::setParameter);

		query.setFirstResult(filter.getFirst());
		query.setMaxResults(filter.getRows());

		List<Object[]> rows = query.getResultList();

		List<ListadoOrdenFabricacionDTO> result = new ArrayList<>();

		for (Object[] r : rows) {

			ListadoOrdenFabricacionDTO dto = new ListadoOrdenFabricacionDTO();

			dto.setIdOrdenCompra(((Number) r[0]).longValue());

			dto.setEstadoOC((String) r[1]);

			dto.setFechaOC(DateUtils.toString((Date) r[2], DateUtils.dd_MM_yyyy));

			dto.setTotalSolicitado(((Number) r[3]).intValue());

			dto.setFechaEntregaSolicitada(DateUtils.toString((Date) r[4], DateUtils.dd_MM_yyyy));

			dto.setIdOrdenFabricacion(((Number) r[5]).longValue());

			dto.setFechaOF(DateUtils.toString((Date) r[6], DateUtils.dd_MM_yyyy));

			dto.setNumero(((Number) r[7]).intValue());

			dto.setAnio(((Number) r[8]).intValue());

			dto.setEstadoOF((String) r[9]);

			dto.setIdCliente(((Number) r[10]).longValue());

			dto.setClienteNombre((String) r[11]);

			dto.setIdPieza(((Number) r[12]).longValue());

			dto.setPiezaCodigo((String) r[13]);

			dto.setIdFormula(((Number) r[14]).longValue());

			dto.setFormulaNombre((String) r[15]);

			dto.setIdSeccion(r[16] != null ? ((Number) r[16]).longValue() : null);

			dto.setSeccionNombre((String) r[17]);

			dto.setIdMaquina(r[18] != null ? ((Number) r[18]).longValue() : null);

			dto.setMaquinaNombre((String) r[19]);

			dto.setTotalFabricado(((Number) r[20]).intValue());

			dto.setSaldo(((Number) r[21]).intValue());

			dto.setTotalRegistros(((Number) r[22]).longValue());

			result.add(dto);
		}

		long totalRegistros = 0;

		if (!result.isEmpty()) {
			totalRegistros = result.get(0).getTotalRegistros();
		}

		return new PageDTO<>(result, totalRegistros);
	}

}