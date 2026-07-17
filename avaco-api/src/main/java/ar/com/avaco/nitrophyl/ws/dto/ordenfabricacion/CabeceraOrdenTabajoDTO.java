package ar.com.avaco.nitrophyl.ws.dto.ordenfabricacion;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.avaco.nitrophyl.domain.entities.administracion.TipoDespacho;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CabeceraOrdenTabajoDTO {


    @JsonProperty("numero_ot")
    private String numeroOt;

    private String cliente;

    private String oc;

    private String prensa;

    private String sector;

    @JsonProperty("fecha_emision")
    private String fechaEmision;

    @JsonProperty("fecha_entrega")
    private String fechaEntrega;

    private String observaciones;
    
    private String telefonoCliente;
    
    private String emailCliente;
    
	private TipoDespacho tipoDespacho;

	private String empresaTransporte;

	private List<String> mediosEnvio;

	private String domicilioEnvio;

}
