package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class ListadoOrdenFabricacionDTO extends DTOEntity<Long> {

	private Long idOrdenCompra;

	private String estadoOC;

	private String fechaOC;

	private Integer totalSolicitado;

	private String fechaEntregaSolicitada;

	private Long idOrdenFabricacion;

	private String fechaOF;

	private Integer numero;

	private Integer anio;

	private String estadoOF;

	private Integer totalFabricado;

	private Integer saldo;

	private Long idCliente;

	private String clienteNombre;

	private Long idPieza;

	private String piezaCodigo;

	private Long idFormula;

	private String formulaNombre;

	
	
	/**
	 * Se utiliza para la paginación cuando se consulta con COUNT(*) OVER().
	 */
	private Long totalRegistros;

	public Long getIdOrdenCompra() {
		return idOrdenCompra;
	}

	public void setIdOrdenCompra(Long idOrdenCompra) {
		this.idOrdenCompra = idOrdenCompra;
	}

	public String getEstadoOC() {
		return estadoOC;
	}

	public void setEstadoOC(String estadoOC) {
		this.estadoOC = estadoOC;
	}

	public Integer getTotalSolicitado() {
		return totalSolicitado;
	}

	public void setTotalSolicitado(Integer totalSolicitado) {
		this.totalSolicitado = totalSolicitado;
	}

	public Long getIdOrdenFabricacion() {
		return idOrdenFabricacion;
	}

	public void setIdOrdenFabricacion(Long idOrdenFabricacion) {
		this.idOrdenFabricacion = idOrdenFabricacion;
	}

	public String getFechaOC() {
		return fechaOC;
	}

	public void setFechaOC(String fechaOC) {
		this.fechaOC = fechaOC;
	}

	public String getFechaEntregaSolicitada() {
		return fechaEntregaSolicitada;
	}

	public void setFechaEntregaSolicitada(String fechaEntregaSolicitada) {
		this.fechaEntregaSolicitada = fechaEntregaSolicitada;
	}

	public String getFechaOF() {
		return fechaOF;
	}

	public void setFechaOF(String fechaOF) {
		this.fechaOF = fechaOF;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public String getEstadoOF() {
		return estadoOF;
	}

	public void setEstadoOF(String estadoOF) {
		this.estadoOF = estadoOF;
	}

	public Integer getTotalFabricado() {
		return totalFabricado;
	}

	public void setTotalFabricado(Integer totalFabricado) {
		this.totalFabricado = totalFabricado;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

	public Long getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(Long totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getClienteNombre() {
		return clienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	public Long getIdPieza() {
		return idPieza;
	}

	public void setIdPieza(Long idPieza) {
		this.idPieza = idPieza;
	}

	public String getPiezaCodigo() {
		return piezaCodigo;
	}

	public void setPiezaCodigo(String piezaCodigo) {
		this.piezaCodigo = piezaCodigo;
	}

	public Long getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(Long idFormula) {
		this.idFormula = idFormula;
	}

	public String getFormulaNombre() {
		return formulaNombre;
	}

	public void setFormulaNombre(String formulaNombre) {
		this.formulaNombre = formulaNombre;
	}

	@Override
	public void setId(Long id) {
		this.idOrdenFabricacion = id;
	}

	@Override
	public Long getId() {
		return idOrdenFabricacion;
	}

}
