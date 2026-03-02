package ar.com.avaco.nitrophyl.ws.dto;

import java.util.ArrayList;
import java.util.List;

public class ReporteLoteClienteDTO {

	private Long idCliente;
	private String idLote;
	private String observaciones;
	private String observacionesInforme;

	private List<ArchivoAdjuntoReporteDTO> archivos = new ArrayList<ArchivoAdjuntoReporteDTO>();

	public List<ArchivoAdjuntoReporteDTO> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<ArchivoAdjuntoReporteDTO> archivos) {
		this.archivos = archivos;
	}

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

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
