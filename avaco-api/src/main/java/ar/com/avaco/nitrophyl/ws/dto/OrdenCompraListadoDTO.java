package ar.com.avaco.nitrophyl.ws.dto;

import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import ar.com.avaco.nitrophyl.domain.entities.fabricacion.OrdenCompraEstado;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class OrdenCompraListadoDTO extends DTOEntity<Long> {

	private Long id;

	private Long idCliente;

	private String cliente;

	private String comprobante;

	private String fecha;

	private OrdenCompraEstado estado;

	@Override
	public ProjectionList getProjections() {
		return Projections.projectionList()
				.add(Projections.property("id"), "id")
				.add(Projections.property("cliente.id"), "idCliente")
				.add(Projections.property("cliente.nombre"), "cliente")
				.add(Projections.property("comprobante"), "comprobante")
				.add(Projections.sqlProjection("to_char(FECHA, 'DD/MM/YYYY') as fecha", new String[] { "fecha" },
						new Type[] { StandardBasicTypes.STRING }))
				.add(Projections.property("estado"), "estado");
	}

	public OrdenCompraEstado getEstado() {
		return estado;
	}

	public void setEstado(OrdenCompraEstado estado) {
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
