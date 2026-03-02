package ar.com.avaco.nitrophyl.ws.dto;

public class ArchivoAdjuntoReporteDTO {

	private byte[] base64;

	private String nombre;

	public byte[] getBase64() {
		return base64;
	}

	public void setBase64(byte[] base64) {
		this.base64 = base64;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
