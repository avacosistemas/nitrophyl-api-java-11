/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.nitrophyl.domain.entities.molde.MoldeRegistro;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class MoldeRegistroDTO extends DTOEntity<Long> {

	private Long id;
	private String fechaHora;
	private String tipo;
	private String comentarios;
	private Long idMolde;

	public MoldeRegistroDTO() {
		super();
	}

	public MoldeRegistroDTO(MoldeRegistro mr) {
		super();
		this.id = mr.getId();
		this.idMolde = mr.getIdMolde();
		this.fechaHora = DateUtils.toString(mr.getFecha(), DateUtils.dd_MM_yy_HH_mm);
		this.tipo = mr.getTipoRegistro().toString();
		this.comentarios = mr.getComentarios();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Long getIdMolde() {
		return idMolde;
	}

	public void setIdMolde(Long idMolde) {
		this.idMolde = idMolde;
	}

}
