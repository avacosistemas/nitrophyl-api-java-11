package ar.com.avaco.nitrophyl.repository.fabricacion;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenFabricacion;
import ar.com.avaco.nitrophyl.ws.dto.DetalleMaquinaOrdenTrabajoDTO;
import ar.com.avaco.nitrophyl.ws.dto.ResumenMaquinaOrdenTrabajoDTO;

public interface OrdenFabricacionRepository
		extends NJRepository<Long, OrdenFabricacion>, OrdenFabricacionRepositoryCustom {

	@Query(value = "SELECT EXISTS ( " + "    SELECT 1 " + "    FROM orden_fabricacion of2 "
			+ "    INNER JOIN orden_compra_detalle_pedido ocdp2 "
			+ "        ON ocdp2.id_orden_compra_detalle_pedido = of2.id_oc_det_pedido "
			+ "    INNER JOIN orden_compra_detalle ocd2 "
			+ "        ON ocd2.id_orden_compra_detalle = ocdp2.id_orden_compra_detalle "
			+ "    WHERE ocd2.id_orden_compra = ( " + "        SELECT ocd.id_orden_compra "
			+ "        FROM orden_fabricacion of1 " + "        INNER JOIN orden_compra_detalle_pedido ocdp "
			+ "            ON ocdp.id_orden_compra_detalle_pedido = of1.id_oc_det_pedido "
			+ "        INNER JOIN orden_compra_detalle ocd "
			+ "            ON ocd.id_orden_compra_detalle = ocdp.id_orden_compra_detalle "
			+ "        WHERE of1.id_orden_fabricacion = :idOrdenFabricacion " + "    ) "
			+ "    AND of2.id_orden_fabricacion <> :idOrdenFabricacion "
			+ "    AND UPPER(COALESCE(of2.estado, '')) <> 'FINALIZADA' " + ")", nativeQuery = true)
	boolean existenOrdenesNoFinalizadasMismaOC(@Param("idOrdenFabricacion") Long idOrdenFabricacion);

	@Query("SELECT new ar.com.avaco.nitrophyl.ws.dto.ResumenMaquinaOrdenTrabajoDTO(" +
		       "m.id, " +
		       "s.id, " +
		       "m.nombre, " +
		       "s.nombre, " +
		       "m.tipo, " +
		       "COUNT(ofab)) " +
		       "FROM OrdenFabricacion ofab " +
		       "WHERE ofab.estado = 'EN_PROCESO' " +
		       "JOIN ofab.sector s " +
		       "LEFT JOIN ofab.maquina m " +
		       "GROUP BY m.id, s.id, m.nombre, s.nombre, m.tipo " +
		       "ORDER BY s.nombre, m.nombre")
	List<ResumenMaquinaOrdenTrabajoDTO> obtenerResumen();
	
	@Query("SELECT new ar.com.avaco.nitrophyl.ws.dto.DetalleMaquinaOrdenTrabajoDTO(" +
		       "ofab.id, " +
		       "m.id, " +
		       "ofab.numero, " +
		       "ofab.anio, " +
		       "ocd.ordenCompra.cliente.razonSocial, " +
		       "ocd.pieza.denominacion, " +
		       "ocd.pieza.detalleFormula.formula.nombre, " +
		       "ocdp.cantidad, " +
		       "ocdp.fechaEntregaSolicitada, " +
		       "ofab.posicion) " +
		       "FROM OrdenFabricacion ofab " +
		       "JOIN ofab.ordenCompraDetalle ocdp " +
		       "JOIN ocdp.ordenCompraDetalle ocd " +
		       "LEFT JOIN ofab.maquina m " +
		       "WHERE ofab.sector.id = :idSector " +
		       "AND ofab.estado = 'EN_PROCESO' " +
		       "AND (" +
		       "    (:idMaquina IS NULL AND ofab.maquina IS NULL) " +
		       "    OR " +
		       "    (:idMaquina IS NOT NULL AND ofab.maquina.id = :idMaquina)" +
		       ")")
		List<DetalleMaquinaOrdenTrabajoDTO> obtenerOrdenesTrabajo(
	        @Param("idSector") Long idSector,
	        @Param("idMaquina") Long idMaquina);


	List<OrdenFabricacion> findBySectorIdAndMaquinaIdOrderByPosicionAsc(
	        Long idSector,
	        Long idMaquina);

	List<OrdenFabricacion> findBySectorIdAndMaquinaIsNullOrderByPosicionAsc(
	        Long idSector);

	@Query("SELECT COALESCE(MAX(ofab.posicion), 0) " +
	       "FROM OrdenFabricacion ofab " +
	       "WHERE ofab.sector.id = :idSector " +
	       "AND ofab.maquina.id = :idMaquina")
	Integer obtenerUltimaPosicionConMaquina(
	        @Param("idSector") Long idSector,
	        @Param("idMaquina") Long idMaquina);

	@Query("SELECT COALESCE(MAX(ofab.posicion), 0) " +
	       "FROM OrdenFabricacion ofab " +
	       "WHERE ofab.sector.id = :idSector " +
	       "AND ofab.maquina IS NULL")
	Integer obtenerUltimaPosicionSinMaquina(
	        @Param("idSector") Long idSector);
}
