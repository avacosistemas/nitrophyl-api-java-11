package ar.com.avaco.nitrophyl.domain.entities.administracion;

public enum TipoDespacho {

	RETIRO_CLIENTE("Retira el cliente"), RETIRA_EMPRESA("Retira empresa de transporte"), ENVIO("Se envia");

	private String label;

	TipoDespacho(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
