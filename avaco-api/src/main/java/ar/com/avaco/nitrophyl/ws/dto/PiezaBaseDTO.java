package ar.com.avaco.nitrophyl.ws.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class PiezaBaseDTO {

	private Long idPiezaOriginal;
	private String codigo;
	private String nombre;
	private Long idFormula;
	private String material;
	private String hojaProceso;
	private Long idCliente;
	
	private Double cotizacion;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate fechaCotizacion;
	private String observacionesCotizacion;
	
	private boolean espesoresPesoCrudo;
	private boolean moldes;
	private boolean insumos;
	private boolean moldeo;
	private boolean desmoldantePostcura;
	private boolean esquema;
	private boolean piezaTerminada;
	private boolean controles;
	private boolean planos;
	private boolean dimensiones;
	
	private Long revisionInicial;

}
