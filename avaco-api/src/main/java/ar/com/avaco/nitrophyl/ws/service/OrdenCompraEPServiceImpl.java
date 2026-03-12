package ar.com.avaco.nitrophyl.ws.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompra;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompraArchivo;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompraDetalle;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompraDetallePedido;
import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompraEstado;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.cliente.Cotizacion;
import ar.com.avaco.nitrophyl.domain.entities.pieza.cliente.PiezaCliente;
import ar.com.avaco.nitrophyl.service.fabricacion.OrdenCompraService;
import ar.com.avaco.nitrophyl.service.pieza.CotizacionService;
import ar.com.avaco.nitrophyl.service.pieza.PiezaClienteService;
import ar.com.avaco.nitrophyl.ws.dto.OrdenCompraDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenCompraDetalleDTO;
import ar.com.avaco.nitrophyl.ws.dto.OrdenCompraDetallePedidoDTO;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Transactional
@Service("ordenCompraEPService")
public class OrdenCompraEPServiceImpl extends CRUDAuditableEPBaseService<Long, OrdenCompraDTO, OrdenCompra, OrdenCompraService>
		implements OrdenCompraEPService {

	public OrdenCompraEPServiceImpl() {
		super(OrdenCompra.class, OrdenCompraDTO.class);
	}

	@Autowired
	private PiezaClienteService piezaClienteService;
	
	@Autowired
	private CotizacionService cotizacionService;
	
	@Override
	public OrdenCompraDTO save(OrdenCompraDTO dto) throws BusinessException {
		
		// Armo el cliente
		Cliente cliente = Cliente.ofId(dto.getIdCliente());
		
		// Armo el archivo adjunto
		OrdenCompraArchivo oca = new OrdenCompraArchivo();
		oca.setArchivo(dto.getArchivo().getArchivo());
		oca.setNombre(dto.getArchivo().getNombre());

		// Armo la orden de compra
		OrdenCompra ordenCompra = new OrdenCompra();
		ordenCompra.setCliente(cliente);
		ordenCompra.setComprobante(dto.getComprobante());
		ordenCompra.setEstado(OrdenCompraEstado.PENDIENTE);
		ordenCompra.setFecha(LocalDate.parse(dto.getFecha(), DateTimeFormatter.ofPattern(DateUtils.dd_MM_yyyy)));

		ordenCompra.setArchivo(oca);

		// Por cada pieza
		for (OrdenCompraDetalleDTO detalleDTO : dto.getDetalle()) {
			
			
			// Armo la pieza
			Pieza pieza = Pieza.ofId(detalleDTO.getIdPieza());
			
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
				PiezaCliente piezaCliente = piezaClienteService.getByPiezaCliente(dto.getIdCliente(), detalleDTO.getIdPieza());
				
				// Si no existe la asociación, la creo
				if (piezaCliente == null) {
					piezaCliente = new PiezaCliente();
					piezaCliente.setCliente(cliente);
					piezaCliente.setPieza(pieza);
				}
				
				// Armo la cotizacion y le seteo el valor, la fecha y la piezacliente (existente o no)
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
				pedido.setFechaEntregaSolicitada(LocalDate.parse(pedidoDTO.getFechaEntregaSolicitada(), DateTimeFormatter.ofPattern(DateUtils.dd_MM_yyyy)));
				pedido.setOrdenCompraDetalle(detalle);
				
				// Le agrego al detalle el pedido
				detalle.getEntregasSolicitadas().add(pedido);
			}
			
			// Le agrego a la orden de compra el detalle
			ordenCompra.getDetalle().add(detalle);
		}

		// Guardo la nueva orden de compra
		this.service.save(ordenCompra);
		
		return dto;
		
	}
	
	@Override
	@Resource(name = "ordenCompraService")
	protected void setService(OrdenCompraService service) {
		this.service = service;
	}

}