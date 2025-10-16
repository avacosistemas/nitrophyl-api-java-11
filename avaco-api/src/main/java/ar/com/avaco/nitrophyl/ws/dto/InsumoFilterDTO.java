package ar.com.avaco.nitrophyl.ws.dto;

public class InsumoFilterDTO extends SortPageDTO {

	private String nombre;

	private Long idTipo;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

}
