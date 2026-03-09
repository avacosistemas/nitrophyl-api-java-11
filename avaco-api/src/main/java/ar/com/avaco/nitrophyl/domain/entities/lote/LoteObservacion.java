package ar.com.avaco.nitrophyl.domain.entities.lote;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "LOTE_OBSERVACION")
public class LoteObservacion extends AuditableEntity<Long> {

	private static final long serialVersionUID = 2331117350980434560L;

	@Id
	@GeneratedValue(generator = "LOTE_OBSERVACION_SEQ")
	@GenericGenerator(name = "LOTE_OBSERVACION_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "LOTE_OBSERVACION_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_LOTE_OBSERVACION", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_LOTE")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Lote lote;

	@Column(name = "MOSTRAR_REPORTE")
	private Boolean mostrarReporte;

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Boolean getMostrarReporte() {
		return mostrarReporte;
	}

	public void setMostrarReporte(Boolean mostrarReporte) {
		this.mostrarReporte = mostrarReporte;
	}

}
