package ar.com.avaco.nitrophyl.repository.fabricacion;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenFabricacion;

public interface OrdenFabricacionRepository extends NJRepository<Long, OrdenFabricacion>, OrdenFabricacionRepositoryCustom {

	@Query(
		    value =
		        "SELECT EXISTS ( " +
		        "    SELECT 1 " +
		        "    FROM orden_fabricacion of2 " +
		        "    INNER JOIN orden_compra_detalle_pedido ocdp2 " +
		        "        ON ocdp2.id_orden_compra_detalle_pedido = of2.id_oc_det_pedido " +
		        "    INNER JOIN orden_compra_detalle ocd2 " +
		        "        ON ocd2.id_orden_compra_detalle = ocdp2.id_orden_compra_detalle " +
		        "    WHERE ocd2.id_orden_compra = ( " +
		        "        SELECT ocd.id_orden_compra " +
		        "        FROM orden_fabricacion of1 " +
		        "        INNER JOIN orden_compra_detalle_pedido ocdp " +
		        "            ON ocdp.id_orden_compra_detalle_pedido = of1.id_oc_det_pedido " +
		        "        INNER JOIN orden_compra_detalle ocd " +
		        "            ON ocd.id_orden_compra_detalle = ocdp.id_orden_compra_detalle " +
		        "        WHERE of1.id_orden_fabricacion = :idOrdenFabricacion " +
		        "    ) " +
		        "    AND of2.id_orden_fabricacion <> :idOrdenFabricacion " +
		        "    AND UPPER(COALESCE(of2.estado, '')) <> 'FINALIZADA' " +
		        ")",
		    nativeQuery = true
		)
		boolean existenOrdenesNoFinalizadasMismaOC(
		        @Param("idOrdenFabricacion") Long idOrdenFabricacion);
	
}
