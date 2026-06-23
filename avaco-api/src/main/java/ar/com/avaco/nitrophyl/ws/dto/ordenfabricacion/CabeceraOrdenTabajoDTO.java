package ar.com.avaco.nitrophyl.ws.dto.ordenfabricacion;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty("fecha_emision")
    private String fechaEmision;

    @JsonProperty("fecha_entrega")
    private String fechaEntrega;

    private String observaciones;

}
