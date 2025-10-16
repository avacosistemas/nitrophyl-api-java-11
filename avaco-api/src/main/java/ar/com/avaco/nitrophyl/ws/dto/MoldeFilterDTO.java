package ar.com.avaco.nitrophyl.ws.dto;

public class MoldeFilterDTO extends SortPageDTO {

	private String codigo;
	private String nombre;
	private String estado;
	private Long altomin;
	private Long altomax;
	private Long anchomin;
	private Long anchomax;
	private Long profumin;
	private Long profumax;
	private Long diametromin;
	private Long diametromax;
	private String idTipoPieza;
	private Long idCliente;

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdTipoPieza() {
		return idTipoPieza;
	}

	public void setIdTipoPieza(String idTipoPieza) {
		this.idTipoPieza = idTipoPieza;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getAltomin() {
		return altomin;
	}

	public void setAltomin(Long altomin) {
		this.altomin = altomin;
	}

	public Long getAltomax() {
		return altomax;
	}

	public void setAltomax(Long altomax) {
		this.altomax = altomax;
	}

	public Long getAnchomin() {
		return anchomin;
	}

	public void setAnchomin(Long anchomin) {
		this.anchomin = anchomin;
	}

	public Long getAnchomax() {
		return anchomax;
	}

	public void setAnchomax(Long anchomax) {
		this.anchomax = anchomax;
	}

	public Long getProfumin() {
		return profumin;
	}

	public void setProfumin(Long profumin) {
		this.profumin = profumin;
	}

	public Long getProfumax() {
		return profumax;
	}

	public void setProfumax(Long profumax) {
		this.profumax = profumax;
	}

	public Long getDiametromin() {
		return diametromin;
	}

	public void setDiametromin(Long diametromin) {
		this.diametromin = diametromin;
	}

	public Long getDiametromax() {
		return diametromax;
	}

	public void setDiametromax(Long diametromax) {
		this.diametromax = diametromax;
	}

}
