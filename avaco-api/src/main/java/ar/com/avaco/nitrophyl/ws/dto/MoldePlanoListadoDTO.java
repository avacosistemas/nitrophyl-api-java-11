package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.nitrophyl.domain.entities.molde.MoldePlano;
import ar.com.avaco.utils.DateUtils;

public class MoldePlanoListadoDTO {

	private Long id;
	private Long idMolde;
	private String nombreArchivo;
	private Integer version;
	private String fecha;
	private String descripcion;
	private String clasificacion;

	public MoldePlanoListadoDTO() {
	}

	public MoldePlanoListadoDTO(MoldePlano mp) {
		super();
		this.id = mp.getId();
		this.idMolde = mp.getIdMolde();
		this.nombreArchivo = mp.getNombreArchivo();
		this.version = mp.getVersion();
		this.fecha = DateUtils.toString(mp.getFecha(), DateUtils.dd_MM_yy_HH_mm);
		this.descripcion = mp.getDescripcion();
		this.clasificacion = mp.getClasificacion() == null ? "NITROPHYL" : mp.getClasificacion().toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMolde() {
		return idMolde;
	}

	public void setIdMolde(Long idMolde) {
		this.idMolde = idMolde;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

}
