package ar.com.avaco.nitrophyl.ws.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ReporteEnsayoLotePorMaquinaFilterDTO extends SortPageDTO {

	public Long idMaquina;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date fechaDesde;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date fechaHasta;

	public Long idFormula;

	public String nroLote;

	public String estadoEnsayo;

	public String getEstadoEnsayo() {
		return estadoEnsayo;
	}

	public void setEstadoEnsayo(String estadoEnsayo) {
		this.estadoEnsayo = estadoEnsayo;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Long getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(Long idFormula) {
		this.idFormula = idFormula;
	}

	public String getNroLote() {
		return nroLote;
	}

	public void setNroLote(String nroLote) {
		this.nroLote = nroLote;
	}

	public Long getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(Long idMaquina) {
		this.idMaquina = idMaquina;
	}

}
