package ar.com.avaco.nitrophyl.domain.entities.lote;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPruebaParametro;
import ar.com.avaco.nitrophyl.ws.dto.RegistroEnsayoLotePorMaquinaDTO;

@SqlResultSetMapping(name="RegistroEnsayoLotePorMaquinaDTOMapper",
classes = {
    @ConstructorResult(
            targetClass = RegistroEnsayoLotePorMaquinaDTO.class,
            columns = {
        		@ColumnResult(name = "row", type = Integer.class),
        		@ColumnResult(name = "rows", type = Integer.class),
                @ColumnResult(name = "idLote", type = Integer.class),
                @ColumnResult(name = "nroLote", type = String.class),
                @ColumnResult(name = "fecha", type = Date.class),
                @ColumnResult(name = "observaciones", type = String.class),
                @ColumnResult(name = "idFormula", type = Integer.class),
                @ColumnResult(name = "nombreFormula", type = String.class),
                @ColumnResult(name = "idMaquinaPrueba", type = Integer.class),
                @ColumnResult(name = "redondeo", type = Double.class),
                @ColumnResult(name = "resultado", type = Double.class),
                @ColumnResult(name = "estadoEnsayo", type = String.class)
            })
})

@Entity
@Table(name = "ENSAYO_RESULTADO")
@SequenceGenerator(name = "ENSAYO_RESULTADO_SEQ", sequenceName = "ENSAYO_RESULTADO_SEQ", allocationSize = 1)
public class EnsayoResultado extends AuditableEntity<Long> {

	private static final long serialVersionUID = 7613782310720480769L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENSAYO_RESULTADO_SEQ")
	@Column(name = "ID_ENSAYO_RESULTADO", unique = true, nullable = false)
	private Long id;

	@Column(name = "resultado")
	private Double resultado;

	@Column(name = "redondeo")
	private Double redondeo;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_ENSAYO")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Ensayo ensayo;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_CONF_PRUEBA_PARAM")
	private ConfiguracionPruebaParametro configuracionPruebaParametro;

	public EnsayoResultado() {
		// TODO Auto-generated constructor stub
	}

	public EnsayoResultado(ConfiguracionPruebaParametro configuracionPruebaParametro) {
		 this.configuracionPruebaParametro = configuracionPruebaParametro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getResultado() {
		return resultado;
	}

	public void setResultado(Double resultado) {
		this.resultado = resultado;
	}

	public Double getRedondeo() {
		return redondeo;
	}

	public void setRedondeo(Double redondeo) {
		this.redondeo = redondeo;
	}

	public Ensayo getEnsayo() {
		return ensayo;
	}

	public void setEnsayo(Ensayo ensayo) {
		this.ensayo = ensayo;
	}

	public ConfiguracionPruebaParametro getConfiguracionPruebaParametro() {
		return configuracionPruebaParametro;
	}

	public void setConfiguracionPruebaParametro(ConfiguracionPruebaParametro configuracionPruebaParametro) {
		this.configuracionPruebaParametro = configuracionPruebaParametro;
	}
	
	public int getPosicion() {
		return this.configuracionPruebaParametro.getMaquinaPrueba().getPosicion();
	}

}
