package ar.com.avaco.nitrophyl.domain.entities.reporte;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;

@Entity
@Table(name = "REGISTRO_ENVIO_INFORME_CALIDAD")
public class RegistroEnvioInformeCalidad extends AuditableEntity<Long> {

	private static final long serialVersionUID = -8915059528176139461L;

	@Id
	@GeneratedValue(generator = "REGISTRO_ENVIO_INFORME_CALIDAD_SEQ")
	@GenericGenerator(name = "REGISTRO_ENVIO_INFORME_CALIDAD_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "REGISTRO_ENVIO_INFORME_CALIDAD_SEQ"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@Column(name = "ID_REGISTRO_ENVIO_INFORME_CALIDAD")
	private Long id;

	@JoinColumn(name = "ID_CLIENTE", nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	@JoinColumn(name = "ID_LOTE", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Lote lote;

	@Column(name = "EMAIL_ENVIADO")
	private String emailEnviado;

	@Column(name = "OBS_MAIL")
	private String observacionesMail;

	@Column(name = "OBS_INFORME")
	private String observacionesInforme;

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public String getEmailEnviado() {
		return emailEnviado;
	}

	public void setEmailEnviado(String emailEnviado) {
		this.emailEnviado = emailEnviado;
	}

	public String getObservacionesMail() {
		return observacionesMail;
	}

	public void setObservacionesMail(String observacionesMail) {
		this.observacionesMail = observacionesMail;
	}

	public String getObservacionesInforme() {
		return observacionesInforme;
	}

	public void setObservacionesInforme(String observacionesInforme) {
		this.observacionesInforme = observacionesInforme;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
