package ar.com.avaco.nitrophyl.ws.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.avaco.nitrophyl.domain.entities.administracion.TipoDespacho;
import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class OrdenCompraDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private Long idCliente;

	private String cliente;

	private String comprobante;

	private String fecha;

	private ArchivoDTO archivo;

	private List<OrdenCompraDetalleDTO> detalle = new ArrayList<OrdenCompraDetalleDTO>();

	private TipoDespacho tipoDespacho;

	private Long idEmpresaTransporte;

	private List<String> mediosEnvio;

	private Long idDomicilioEnvio;

	public TipoDespacho getTipoDespacho() {
		return tipoDespacho;
	}

	public void setTipoDespacho(TipoDespacho tipoDespacho) {
		this.tipoDespacho = tipoDespacho;
	}

	public Long getIdEmpresaTransporte() {
		return idEmpresaTransporte;
	}

	public void setIdEmpresaTransporte(Long idEmpresaTransporte) {
		this.idEmpresaTransporte = idEmpresaTransporte;
	}

	public List<String> getMediosEnvio() {
		return mediosEnvio;
	}

	public void setMediosEnvio(List<String> mediosEnvio) {
		this.mediosEnvio = mediosEnvio;
	}

	public Long getIdDomicilioEnvio() {
		return idDomicilioEnvio;
	}

	public void setIdDomicilioEnvio(Long idDomicilioEnvio) {
		this.idDomicilioEnvio = idDomicilioEnvio;
	}

	public void setArchivo(ArchivoDTO archivo) {
		this.archivo = archivo;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
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

	public List<OrdenCompraDetalleDTO> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<OrdenCompraDetalleDTO> detalle) {
		this.detalle = detalle;
	}

	public ArchivoDTO getArchivo() {
		return archivo;
	}

}
