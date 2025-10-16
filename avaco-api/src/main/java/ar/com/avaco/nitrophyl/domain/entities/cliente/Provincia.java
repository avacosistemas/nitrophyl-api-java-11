package ar.com.avaco.nitrophyl.domain.entities.cliente;

/**
 * Listado de provincias de la Republica Argentina.
 * @author beto
 *
 */
public enum Provincia {
	
	BUENOS_AIRES("Buenos Aires"),
	CABA("CABA"),
	CATAMARCA("Catamarca"),
	CHACO("Chaco"),
	CHUBUT("Chubut"),
	CORDOBA("Crdoba"),
	CORRIENTES("Corrientes"),
	ENTRE_RIOS("Entre Ros"),
	FORMOSA("Formosa"),
	JUJUY("Jujuy"),
	LA_PAMPA("La Pampa"),
	LA_RIOJA("La Rioja"),
	MENDOZA("Mendoza"),
	MISIONES("Misiones"),
	NEUQUEN("Neuquen"),
	RIO_NEGRO("Ro Negro"),
	SALTA("Salta"),
	SAN_JUAN("San Juan"),
	SAN_LUIS("San Luis"),
	SANTA_CRUZ("Santa Cruz"),
	SANTA_FE("Santa Fe"),
	SANTIAGO_DEL_ESTERO("Santiago del Estero"),
	TIERRA_DEL_FUEGO("Tierra del Fuego"),
	TUCUMAN("Tucumn");
	
	private String nombre;
	
	Provincia(String elnombre) {
		this.nombre = elnombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
}
