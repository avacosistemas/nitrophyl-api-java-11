package ar.com.avaco.nitrophyl.ws.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.domain.entities.administracion.EmpresaTransporte;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.cliente.ClienteDomicilio;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.EstadoOrdenCompra;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompra;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompraArchivo;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompraDetalle;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompraDetallePedido;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.cliente.Cotizacion;
import ar.com.avaco.nitrophyl.domain.entities.pieza.cliente.PiezaCliente;
import ar.com.avaco.nitrophyl.service.fabricacion.OrdenCompraService;
import ar.com.avaco.nitrophyl.service.fabricacion.OrdenFabricacionService;
import ar.com.avaco.nitrophyl.service.pieza.CotizacionService;
import ar.com.avaco.nitrophyl.service.pieza.PiezaClienteService;
import ar.com.avaco.nitrophyl.service.pieza.PiezaService;
import ar.com.avaco.nitrophyl.ws.dto.OrdenCompraDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenCompraDetalleDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenCompraDetallePedidoDTO;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Transactional
@Service("ordenCompraEPService")
public class OrdenCompraEPServiceImpl
		extends CRUDAuditableEPBaseService<Long, OrdenCompraDTO, OrdenCompra, OrdenCompraService>
		implements OrdenCompraEPService {

	public OrdenCompraEPServiceImpl() {
		super(OrdenCompra.class, OrdenCompraDTO.class);
	}

	@Autowired
	private PiezaClienteService piezaClienteService;

	@Autowired
	private CotizacionService cotizacionService;

	@Autowired
	private PiezaService piezaService;

	@Autowired
	private OrdenFabricacionService ordenFabricacionService;

	@Override
	public void generarOrdenFabrica(Long idOC) {
		OrdenCompra ordenCompra = this.service.get(idOC);
		ordenFabricacionService.generarOrdenes(ordenCompra.getDetalle());
		ordenCompra.setEstado(EstadoOrdenCompra.PENDIENTE);
		this.service.update(ordenCompra);
	}
	
	@Override
	public OrdenCompraDTO save(OrdenCompraDTO dto) throws BusinessException {

		// Armo el cliente
		Cliente cliente = Cliente.ofId(dto.getIdCliente());

		// Busco el domicilio seleccionado si es que lo hay
		ClienteDomicilio domicilio = dto.getIdDomicilioEnvio() != null
				? ClienteDomicilio.ofId(dto.getIdDomicilioEnvio())
				: null;

		// Busco la empres de transporte si es que la hay
		EmpresaTransporte transporte = dto.getIdEmpresaTransporte() != null
				? EmpresaTransporte.ofId(dto.getIdEmpresaTransporte())
				: null;

		String mediosEnvio = dto.getMediosEnvio() != null && !dto.getMediosEnvio().isEmpty()
				? String.join(",", dto.getMediosEnvio())
				: null;

		// Armo el archivo adjunto
		OrdenCompraArchivo oca = new OrdenCompraArchivo();
		oca.setArchivo(dto.getArchivo().getArchivo());
		oca.setNombre(dto.getArchivo().getNombre());

		// Armo la orden de compra
		OrdenCompra ordenCompra = new OrdenCompra();
		ordenCompra.setCliente(cliente);
		ordenCompra.setComprobante(dto.getComprobante());
		
		if (dto.getGenerarOrdenFabrica()) {
			ordenCompra.setEstado(EstadoOrdenCompra.PENDIENTE);
		} else {
			ordenCompra.setEstado(EstadoOrdenCompra.INGRESADA);
		}
		
		ordenCompra.setFecha(LocalDate.parse(dto.getFecha(), DateTimeFormatter.ofPattern(DateUtils.dd_MM_yyyy)));
		ordenCompra.setTipoDespacho(dto.getTipoDespacho());
		ordenCompra.setEmpresaTransporte(transporte);
		ordenCompra.setDomicilioEnvio(domicilio);
		ordenCompra.setMediosEnvio(mediosEnvio);
		
		ordenCompra.setObservaciones(dto.getObservaciones());

		ordenCompra.setArchivo(oca);

		// Por cada pieza
		for (OrdenCompraDetalleDTO detalleDTO : dto.getDetalle()) {

			// Armo la pieza
			Pieza pieza = piezaService.get(detalleDTO.getIdPieza());

			// Armo el detalle
			OrdenCompraDetalle detalle = new OrdenCompraDetalle();
			detalle.setOrdenCompra(ordenCompra);
			detalle.setPieza(pieza);

			// Si existe una cotizacion y se usa la vigente
			if (detalleDTO.getIdCotizacion() != null) {
				detalle.setCotizacion(cotizacionService.get(detalleDTO.getIdCotizacion()));
			} else {
				// Si no existe una cotizacion o no se usa la vigente
				// Busco si existe relacion entre la pieza y el cliente
				PiezaCliente piezaCliente = piezaClienteService.getByPiezaCliente(dto.getIdCliente(),
						detalleDTO.getIdPieza());

				// Si no existe la asociación, la creo
				if (piezaCliente == null) {
					piezaCliente = new PiezaCliente();
					piezaCliente.setCliente(cliente);
					piezaCliente.setPieza(pieza);
				}

				// Armo la cotizacion y le seteo el valor, la fecha y la piezacliente (existente
				// o no)
				Cotizacion cotizacion = new Cotizacion();
				cotizacion.setFecha(DateUtils.toDate(detalleDTO.getFechaCotizacion(), DateUtils.dd_MM_yyyy));
				cotizacion.setPiezaCliente(piezaCliente);
				cotizacion.setValor(detalleDTO.getValorCotizacion());

				// Seteo la cotizacion existente o nueva
				detalle.setCotizacion(cotizacion);

			}

			// Por cada una de los pedidos
			for (OrdenCompraDetallePedidoDTO pedidoDTO : detalleDTO.getEntregasSolicitadas()) {

				// Armo el pedido, seteo cantidad y fecha estimada de entrega
				OrdenCompraDetallePedido pedido = new OrdenCompraDetallePedido();
				pedido.setCantidad(pedidoDTO.getCantidad());
				pedido.setFechaEntregaSolicitada(LocalDate.parse(pedidoDTO.getFechaEntregaSolicitada(),
						DateTimeFormatter.ofPattern(DateUtils.dd_MM_yyyy)));
				pedido.setOrdenCompraDetalle(detalle);

				// Le agrego al detalle el pedido
				detalle.getEntregasSolicitadas().add(pedido);
			}

			// Le agrego a la orden de compra el detalle
			ordenCompra.getDetalle().add(detalle);
		}

		// Guardo la nueva orden de compra
		this.service.save(ordenCompra);

		if (dto.getGenerarOrdenFabrica()) {
			ordenFabricacionService.generarOrdenes(ordenCompra.getDetalle());
		}
		
		return dto;

	}

	@Override
	public OrdenCompraDTO update(OrdenCompraDTO dto) throws BusinessException {

		// Obtengo la OC
		OrdenCompra ordenCompra = this.service.get(dto.getId());

		// Armo el cliente
		Cliente cliente = Cliente.ofId(dto.getIdCliente());

		// Busco el domicilio seleccionado si es que lo hay
		ClienteDomicilio domicilio = dto.getIdDomicilioEnvio() != null
				? ClienteDomicilio.ofId(dto.getIdDomicilioEnvio())
				: null;

		// Busco la empres de transporte si es que la hay
		EmpresaTransporte transporte = dto.getIdEmpresaTransporte() != null
				? EmpresaTransporte.ofId(dto.getIdEmpresaTransporte())
				: null;

		String mediosEnvio = dto.getMediosEnvio() != null && !dto.getMediosEnvio().isEmpty()
				? String.join(",", dto.getMediosEnvio())
				: null;


		// Armo la orden de compra
		ordenCompra.setCliente(cliente);
		ordenCompra.setComprobante(dto.getComprobante());
		
		if (dto.getGenerarOrdenFabrica()) {
			ordenCompra.setEstado(EstadoOrdenCompra.PENDIENTE);
		} else {
			ordenCompra.setEstado(EstadoOrdenCompra.INGRESADA);
		}
		
		ordenCompra.setFecha(LocalDate.parse(dto.getFecha(), DateTimeFormatter.ofPattern(DateUtils.dd_MM_yyyy)));
		ordenCompra.setTipoDespacho(dto.getTipoDespacho());
		ordenCompra.setEmpresaTransporte(transporte);
		ordenCompra.setDomicilioEnvio(domicilio);
		ordenCompra.setMediosEnvio(mediosEnvio);

		// Si viene un archivo nuevo entonces lo piso, sino dejo el actual.
		if (dto.getArchivo() != null && dto.getArchivo().getArchivo() != null && dto.getArchivo().getNombre() != null) {
			// Armo el archivo adjunto
			OrdenCompraArchivo oca = ordenCompra.getArchivo() == null ? new OrdenCompraArchivo() : ordenCompra.getArchivo();
			oca.setArchivo(dto.getArchivo().getArchivo());
			oca.setNombre(dto.getArchivo().getNombre());
			ordenCompra.setArchivo(oca);
		}

		List<OrdenCompraDetalle> detalles = new ArrayList<OrdenCompraDetalle>();
		
		// Por cada pieza
		for (OrdenCompraDetalleDTO detalleDTO : dto.getDetalle()) {
			
			// Armo la pieza
			Pieza pieza = piezaService.get(detalleDTO.getIdPieza());

			// Armo el detalle
			OrdenCompraDetalle detalle = new OrdenCompraDetalle();
			
			// Seteo el id del detalle, sea null o tenga id.
			detalle.setId(detalleDTO.getId());
			detalle.setOrdenCompra(ordenCompra);
			detalle.setPieza(pieza);

			// Si existe una cotizacion y se usa la vigente
			if (detalleDTO.getIdCotizacion() != null) {
				detalle.setCotizacion(cotizacionService.get(detalleDTO.getIdCotizacion()));
			} else {
				// Si no existe una cotizacion o no se usa la vigente
				// Busco si existe relacion entre la pieza y el cliente
				PiezaCliente piezaCliente = piezaClienteService.getByPiezaCliente(dto.getIdCliente(),
						detalleDTO.getIdPieza());

				// Si no existe la asociación, la creo
				if (piezaCliente == null) {
					piezaCliente = new PiezaCliente();
					piezaCliente.setCliente(cliente);
					piezaCliente.setPieza(pieza);
				}

				// Armo la cotizacion y le seteo el valor, la fecha y la piezacliente (existente
				// o no)
				Cotizacion cotizacion = new Cotizacion();
				cotizacion.setFecha(DateUtils.toDate(detalleDTO.getFechaCotizacion(), DateUtils.dd_MM_yyyy));
				cotizacion.setPiezaCliente(piezaCliente);
				cotizacion.setValor(detalleDTO.getValorCotizacion());

				// Seteo la cotizacion existente o nueva
				detalle.setCotizacion(cotizacion);

			}

			// Por cada una de los pedidos
			for (OrdenCompraDetallePedidoDTO pedidoDTO : detalleDTO.getEntregasSolicitadas()) {

				// Armo el pedido, seteo cantidad y fecha estimada de entrega
				OrdenCompraDetallePedido pedido = new OrdenCompraDetallePedido();
				pedido.setCantidad(pedidoDTO.getCantidad());
				pedido.setFechaEntregaSolicitada(LocalDate.parse(pedidoDTO.getFechaEntregaSolicitada(),
						DateTimeFormatter.ofPattern(DateUtils.dd_MM_yyyy)));
				pedido.setOrdenCompraDetalle(detalle);

				// Le agrego al detalle el pedido
				detalle.getEntregasSolicitadas().add(pedido);
			}

			// Agrego el detalle a una lista aparte
			detalles.add(detalle);
			
		}

		// Limpio del detalle actual
		ordenCompra.getDetalle().clear();
		
		// Agrego todo el detalle complet
		ordenCompra.getDetalle().addAll(detalles);

		this.service.update(ordenCompra);
		
		if (dto.getGenerarOrdenFabrica()) {
			ordenFabricacionService.generarOrdenes(ordenCompra.getDetalle());
		}
		
		return dto;
	}

	@Override
	protected OrdenCompraDTO convertToDto(OrdenCompra entity) {
		OrdenCompraDTO dto = super.convertToDto(entity);

		dto.setCliente(entity.getCliente().getNombre());

		dto.setIdCliente(entity.getCliente() != null ? entity.getCliente().getId() : null);

		dto.setIdEmpresaTransporte(
				entity.getEmpresaTransporte() != null ? entity.getEmpresaTransporte().getId() : null);
		dto.setTipoDespacho(entity.getTipoDespacho());
		dto.setMediosEnvio(
				StringUtils.isNotBlank(entity.getMediosEnvio()) ? Arrays.asList(entity.getMediosEnvio().split(","))
						: new ArrayList<String>());
		dto.setIdDomicilioEnvio(entity.getDomicilioEnvio() != null ? entity.getDomicilioEnvio().getId() : null);

		dto.getDetalle().forEach(x -> {
			Pieza pieza = entity.getDetalle().stream().filter(p -> p.getPieza().getId().equals(x.getIdPieza()))
					.findAny().get().getPieza();
			x.setPieza(pieza.getDenominacion() + " (" + pieza.getDetalleFormula().getFormula().getNombre() + ")");
		});

		return dto;

	}

	@Override
	@Resource(name = "ordenCompraService")
	protected void setService(OrdenCompraService service) {
		this.service = service;
	}

	@Override
	public void cancelar(Long id, String motivo) {
		this.service.cancelar(id, motivo);
	}
}