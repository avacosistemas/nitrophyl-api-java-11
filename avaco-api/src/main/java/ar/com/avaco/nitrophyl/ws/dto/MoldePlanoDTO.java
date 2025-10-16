package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldePlano;

public class MoldePlanoDTO {

	private Long idMolde;
	private String nombreArchivo;
	private byte[] archivo;
	private String descripcion;
	private String clasificacion;

	public MoldePlanoDTO() {
		super();
	}

	public MoldePlanoDTO(MoldePlano mp) {
		super();
		this.idMolde = mp.getIdMolde();
		this.nombreArchivo = mp.getNombreArchivo();
		this.archivo = mp.getArchivo();
		this.descripcion = mp.getDescripcion();
		this.clasificacion = mp.getClasificacion().toString();
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

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
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
