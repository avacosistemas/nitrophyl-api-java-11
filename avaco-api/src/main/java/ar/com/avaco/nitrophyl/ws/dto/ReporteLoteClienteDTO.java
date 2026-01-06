package ar.com.avaco.nitrophyl.ws.dto;

public class ReporteLoteClienteDTO {

	private Long idCliente;
	private String idLote;
	private byte[] archivo;
	private String observaciones;
	private String nombreArchivo;
	private String observacionesInforme;

	public String getObservacionesInforme() {
		return observacionesInforme;
	}

	public void setObservacionesInforme(String observacionesInforme) {
		this.observacionesInforme = observacionesInforme;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdLote() {
		return idLote;
	}

	public void setIdLote(String idLote) {
		this.idLote = idLote;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

}
