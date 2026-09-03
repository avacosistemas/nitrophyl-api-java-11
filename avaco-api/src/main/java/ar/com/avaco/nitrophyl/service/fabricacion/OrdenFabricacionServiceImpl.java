package ar.com.avaco.nitrophyl.service.fabricacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.EstadoOrdenFabricacion;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompraDetalle;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompraDetallePedido;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenFabricacion;
import ar.com.avaco.nitrophyl.repository.fabricacion.OrdenFabricacionRepository;
import ar.com.avaco.nitrophyl.ws.dto.ListadoOrdenFabricacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenFabricacionFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;

@Transactional
@Service("ordenFabricacionService")
public class OrdenFabricacionServiceImpl extends NJBaseService<Long, OrdenFabricacion, OrdenFabricacionRepository>
		implements OrdenFabricacionService {

	@Resource(name = "ordenFabricacionRepository")
	void setRepository(OrdenFabricacionRepository ordenFabricacionRepository) {
		this.repository = ordenFabricacionRepository;
	}

	@Override
	public void generarOrdenes(Set<OrdenCompraDetalle> detalle) {
		List<OrdenFabricacion> ordenes = new ArrayList<OrdenFabricacion>();
		for (OrdenCompraDetalle det : detalle) {
			for (OrdenCompraDetallePedido pedido : det.getEntregasSolicitadas()) {
				OrdenFabricacion of = generarOrdenFabricacion(pedido);
				ordenes.add(of);
			}
		}
		this.save(ordenes);
	}

	private OrdenFabricacion generarOrdenFabricacion(OrdenCompraDetallePedido pedido) {
		OrdenFabricacion of = new OrdenFabricacion();
		of.setEstado(EstadoOrdenFabricacion.PENDIENTE);
		of.setFecha(LocalDate.now());
		of.setAnio(LocalDate.now().getYear());
		of.setNumero(this.repository.obtenerSiguienteNumero(of.getAnio()));
		of.setOrdenCompraDetalle(pedido);
		return of;
	}

	@Override
	public boolean hayPendientes(Long idOrdenFabricacion) {
		return this.repository.existenOrdenesNoFinalizadasMismaOC(idOrdenFabricacion);
	}

	@Override
	public PageDTO<ListadoOrdenFabricacionDTO> listFilterCountCustom(
			OrdenFabricacionFilterDTO ordenFabricacionFilterDTO) {
		return this.repository.listFilterCount(ordenFabricacionFilterDTO);
	}

	@Override
	public List<OrdenFabricacion> listByIds(List<Long> ids) {
		return this.repository.findAllById(ids);
	}

	@Override
	public void reordenar(Long idOrdenFabricacion, Integer nuevaPosicion) {
		OrdenFabricacion orden = this.repository.findById(idOrdenFabricacion).get();

		Integer posicionActual = orden.getPosicion();

		if (posicionActual.equals(nuevaPosicion)) {
			return;
		}

		Long idSector = orden.getSector().getId();
		Long idMaquina = orden.getMaquina() != null ? orden.getMaquina().getId() : null;

		List<OrdenFabricacion> ordenes;

		if (idMaquina != null) {
			ordenes = this.repository.findBySectorIdAndMaquinaIdOrderByPosicionAsc(idSector, idMaquina);
		} else {
			ordenes = this.repository.findBySectorIdAndMaquinaIsNullOrderByPosicionAsc(idSector);
		}

		// Sacamos la orden que estamos moviendo
		ordenes.removeIf(o -> o.getId().equals(idOrdenFabricacion));

		// Insertamos en la nueva posición.
		// Las posiciones son 1-based.
		int indice = Math.max(0, Math.min(nuevaPosicion - 1, ordenes.size()));

		ordenes.add(indice, orden);

		// Reasignamos todas las posiciones
		for (int i = 0; i < ordenes.size(); i++) {
			ordenes.get(i).setPosicion(i + 1);
		}

		this.repository.saveAll(ordenes);

	}

	@Override
	public Integer obtenerUltimaPosicion(Long idSector, Long idMaquina) {
		if (idMaquina != null) {
			return this.repository.obtenerUltimaPosicionConMaquina(idSector, idMaquina);
		}
		return this.repository.obtenerUltimaPosicionSinMaquina(idSector);
	}

	@Override
	public void reordenarGrupo(Long idSector, Long idMaquina, Long idOrdenFabricacion) {
		List<OrdenFabricacion> ordenes;
		if (idMaquina != null) {
			ordenes = this.repository.findBySectorIdAndMaquinaIdOrderByPosicionAsc(idSector, idMaquina);
		} else {
			ordenes = this.repository.findBySectorIdAndMaquinaIsNullOrderByPosicionAsc(idSector);
			ordenes.removeIf(orden -> orden.getId().equals(idOrdenFabricacion));
			for (int i = 0; i < ordenes.size(); i++) {
				ordenes.get(i).setPosicion(i + 1);
			}
			this.repository.saveAll(ordenes);
		}
	}

}
