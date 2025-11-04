package ar.com.avaco.nitrophyl.ws.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.service.MailSenderSMTPService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.cliente.EmpresaCliente;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.lote.EstadoLote;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.nitrophyl.domain.entities.moldes.LoteGrafico;
import ar.com.avaco.nitrophyl.domain.entities.reporte.ReporteLoteConfiguracionCliente;
import ar.com.avaco.nitrophyl.service.cliente.ClienteService;
import ar.com.avaco.nitrophyl.service.formula.FormulaService;
import ar.com.avaco.nitrophyl.service.lote.LoteGraficoService;
import ar.com.avaco.nitrophyl.service.lote.LoteService;
import ar.com.avaco.nitrophyl.service.reporte.ReporteLoteConfiguracionClienteService;
import ar.com.avaco.nitrophyl.ws.dto.ArchivoDTO;
import ar.com.avaco.nitrophyl.ws.dto.LoteDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.dto.RegistroEnsayoLotePorMaquinaDTO;
import ar.com.avaco.nitrophyl.ws.dto.ReporteEnsayoLotePorMaquinaDTO;
import ar.com.avaco.nitrophyl.ws.dto.ReporteEnsayoLotePorMaquinaFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.ReporteResultadoEnsayoDTO;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.informe.InformeBuilder;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("loteEPService")
public class LoteEPServiceImpl extends CRUDEPBaseService<Long, LoteDTO, Lote, LoteService> implements LoteEPService {

	private ReporteLoteConfiguracionClienteService reporteLoteConfigClienteService;

	private ClienteService clienteService;

	private FormulaService formulaService;

	private LoteGraficoService loteGraficoService;

	@Autowired
	private MailSenderSMTPService mailSenderSMTPService;

	private ReporteLoteConfiguracionClienteService reporteConfiguracionService;

	@Override
	public List<LoteDTO> listFilter(AbstractFilter abstractFilter) {
		List<LoteDTO> lotes = super.listFilter(abstractFilter);
		List<Long> loteIds = lotes.stream().map(x -> x.getId()).collect(Collectors.toList());
		if (loteIds != null && !loteIds.isEmpty()) {
			List<Long> loteConGraficoIds = loteGraficoService.filterIdsConGrafico(loteIds);
			lotes.forEach(x -> {
				x.setHasGrafico(loteConGraficoIds.contains(x.getId()));
			});
		}
		return lotes;
	}

	@Override
	public LoteDTO save(LoteDTO dto) throws BusinessException {

		List<Lote> listPattern = this.service.listPattern("nroLote", dto.getNroLote());
		if (!listPattern.isEmpty()) {
			throw new BusinessException("El nmero de lote ya existe");
		}
		dto.setEstado("PENDIENTE_APROBACION");
		return super.save(dto);
	}

	@Override
	public LoteDTO update(LoteDTO dto) throws BusinessException {
		Lote update = this.service.get(dto.getId());
		update.setFecha(DateUtils.toDate(dto.getFecha(), DateUtils.dd_MM_yyyy));
		if (dto.getIdFormula() != update.getFormula().getId()) {
			if (this.service.hasEnsayos(dto.getId())) {
				throw new BusinessException("No puede cambiarse la frmula del lote porque tiene ensayos asociados");
			} else {
				Formula formula = this.formulaService.get(dto.getIdFormula());
				update.setFormula(formula);
				update.setRevisionParametros(formula.getRevision());
			}
		}
		List<Lote> listPattern = this.service.listPattern("nroLote", dto.getNroLote());
		if (!listPattern.isEmpty() && listPattern.get(0).getId() != dto.getId()) {
			throw new BusinessException("No puede cambiarse el nmero de lote porque ya existe");
		}

		update.setNroLote(dto.getNroLote());
		update.setObservaciones(dto.getObservaciones());
		return convertToDto(this.service.update(update));
	}

	@Override
	protected Lote convertToEntity(LoteDTO dto) {
		Lote lote = new Lote();
		Formula formula = new Formula();
		formula.setId(dto.getIdFormula());
		lote.setId(dto.getId());
		lote.setFormula(formula);
		lote.setFecha(DateUtils.toDate(dto.getFecha(), DateUtils.dd_MM_yyyy));
		lote.setObservaciones(dto.getObservaciones());
		lote.setNroLote(dto.getNroLote());
		lote.setEstado(EstadoLote.valueOf(dto.getEstado()));
		if (StringUtils.isNotBlank(dto.getFechaEstado())) {
			lote.setFechaEstado(DateUtils.toDate(dto.getFechaEstado(), DateUtils.dd_MM_yyyy));
		}
		lote.setObservacionesEstado(dto.getObservacionesEstado());
		return lote;
	}

	@Override
	protected LoteDTO convertToDto(Lote entity) {
		LoteDTO dto = new LoteDTO();
		dto.setFecha(DateUtils.toStringFecha(entity.getFecha()));
		dto.setFormula(entity.getFormula().getNombre());
		dto.setId(entity.getId());
		dto.setIdFormula(entity.getFormula().getId());
		dto.setObservaciones(entity.getObservaciones());
		dto.setNroLote(entity.getNroLote());
		dto.setEstado(entity.getEstado().toString());
		dto.setFormulaSimple(entity.getFormula().getNombre());
		if (entity.getFechaEstado() != null) {
			dto.setFechaEstado(DateUtils.toStringFecha(entity.getFechaEstado()));
		}
		dto.setObservacionesEstado(entity.getObservacionesEstado());
		if (entity.getRevisionParametros() != null) {
			dto.setRevision(entity.getRevisionParametros().getRevision());
		}

		if (entity.getFormula().getMaterial() == null) {
			dto.setMaterial(formulaService.get(entity.getFormula().getId()).getMaterial().getNombre());
		} else {
			dto.setMaterial(entity.getFormula().getMaterial().getNombre());
		}
		return dto;
	}

	@Override
	public void aprobar(Long idLote, String estado, String observaciones) {
		this.service.aprobar(idLote, estado, observaciones);
	}

	@Override
	public void rechazar(Long idLote, String observaciones) {
		this.service.rechazar(idLote, observaciones);
	}

	@Override
	public ArchivoDTO generarReporteLoteCliente(Long idLote, Long idCliente, String observacionesInforme)
			throws BusinessException {
		Lote lote = this.service.getLoteCompleto(idLote);
		Cliente cliente = clienteService.getCliente(idCliente);
		InformeBuilder ib = new InformeBuilder();
		ArchivoDTO adto = new ArchivoDTO();
		try {
			adto = ib.generarReporte(lote, reporteLoteConfigClienteService, cliente, observacionesInforme);
		} catch (DocumentException | IOException | URISyntaxException e) {
			throw new BusinessException("No se pudo generar el informe", e);
		}
		return adto;

	}

	@Override
	public PageDTO<ReporteEnsayoLotePorMaquinaDTO> generarReporteEnsayoLotePorMaquina(
			ReporteEnsayoLotePorMaquinaFilterDTO filtro) {

		List<RegistroEnsayoLotePorMaquinaDTO> registrosEnsayosLotePorMaquina = this.service
				.getRegistrosEnsayosLotePorMaquina(filtro);

		Map<String, ReporteEnsayoLotePorMaquinaDTO> map = new HashMap<String, ReporteEnsayoLotePorMaquinaDTO>();

		for (RegistroEnsayoLotePorMaquinaDTO dto : registrosEnsayosLotePorMaquina) {
			ReporteEnsayoLotePorMaquinaDTO reporteEnsayoLotePorMaquinaDTO = map.get(dto.getNroLote());
			if (reporteEnsayoLotePorMaquinaDTO == null) {
				reporteEnsayoLotePorMaquinaDTO = crearReporteEnsayoLotePorMaquinaDTO(dto);
			} else {
				reporteEnsayoLotePorMaquinaDTO = agregarEnsayoReporteEnsayoLotePorMaquinaDTO(dto,
						reporteEnsayoLotePorMaquinaDTO);
			}
			map.put(dto.getNroLote(), reporteEnsayoLotePorMaquinaDTO);
		}

		PageDTO<ReporteEnsayoLotePorMaquinaDTO> page = new PageDTO<ReporteEnsayoLotePorMaquinaDTO>();
		List<ReporteEnsayoLotePorMaquinaDTO> values = new ArrayList<ReporteEnsayoLotePorMaquinaDTO>(map.values());

		values.sort(ReporteEnsayoLotePorMaquinaDTO.getComparator(filtro.getIdx(), filtro.getAsc()));

		page.setPage(new ArrayList<ReporteEnsayoLotePorMaquinaDTO>(values));
		page.setTotalReg(0);
		if (!registrosEnsayosLotePorMaquina.isEmpty())
			page.setTotalReg(registrosEnsayosLotePorMaquina.get(0).getRows());

		return page;

	}

	private ReporteEnsayoLotePorMaquinaDTO crearReporteEnsayoLotePorMaquinaDTO(RegistroEnsayoLotePorMaquinaDTO dto) {
		ReporteEnsayoLotePorMaquinaDTO ret = new ReporteEnsayoLotePorMaquinaDTO();
		ret.setId(dto.getRow());
		ret.setFecha(DateUtils.toString(dto.getFecha(), DateUtils.dd_MM_yyyy));
		ret.setIdFormula(dto.getIdFormula());
		ret.setIdLote(dto.getIdLote());
		ret.setNombreFormula(dto.getNombreFormula());
		ret.setNroLote(dto.getNroLote());
		ret.setObservaciones(dto.getObservaciones());
		ret.setEstadoEnsayo(dto.getEstadoEnsayo());
		agregarEnsayoReporteEnsayoLotePorMaquinaDTO(dto, ret);
		return ret;
	}

	private ReporteEnsayoLotePorMaquinaDTO agregarEnsayoReporteEnsayoLotePorMaquinaDTO(
			RegistroEnsayoLotePorMaquinaDTO dto, ReporteEnsayoLotePorMaquinaDTO reporteEnsayoLotePorMaquinaDTO) {
		ReporteResultadoEnsayoDTO resultado = new ReporteResultadoEnsayoDTO();
		resultado.setIdMaquinaPrueba(dto.getIdMaquinaPrueba());
		resultado.setRedondeo(dto.getRedondeo() != null ? String.format("%.2f", dto.getRedondeo()) : "");
		resultado.setResultado(dto.getResultado() != null ? String.format("%.2f", dto.getResultado()) : "");
		reporteEnsayoLotePorMaquinaDTO.getResultados().add(resultado);
		return reporteEnsayoLotePorMaquinaDTO;
	}

	public void borrar(Long idLote) throws BusinessException {
		if (this.service.hasEnsayos(idLote))
			throw new BusinessException("No se puede borrar el lote porque tiene ensayos asociados");
		this.service.borrar(idLote);

	}

	@Override
	public void revisiones() {
		this.service.revisiones();
	}

	@Override
	public void enviarReporte(Long idLote, Long idCliente, byte[] adjuntoextra, String nombreAdjunto,
			String observaciones, String observacionesInforme) throws BusinessException {

		Cliente cliente = this.clienteService.getCliente(idCliente);
		Lote lote = this.service.get(idLote);

		List<String> correos = this.clienteService.getCorreoInformesList(idCliente);
		EmpresaCliente empresa = this.clienteService.getCliente(idCliente).getEmpresa();
		String subject = "CERTIFICADO DE CALIDAD";
		String msg = "Estimados, <br> <br> Adjuntamos el certificado de calidad del material entregado.<br><br>";
		if (StringUtils.isNotBlank(observaciones))
			msg += observaciones + "<br><br>";
		msg += "Saludos cordiales<br><br>";

		if (empresa.equals(EmpresaCliente.NITROPHYL))
			msg = msg + getFirmaNitrophyl();
		else
			msg = msg + getFirmaElasint();

		try {

			List<File> archivos = new ArrayList<>();

			// Archivo de reporte
			ArchivoDTO reporte = generarReporteLoteCliente(idLote, idCliente, observacionesInforme);
			String nombreReporte = "Informe Calidad - " + cliente.getNombre().replace(".", " - " + lote.getNroLote());
			File tempFileReporte = File.createTempFile(nombreReporte, ".pdf");
			FileOutputStream fosReporte = new FileOutputStream(tempFileReporte);
			fosReporte.write(reporte.getArchivo());
			archivos.add(tempFileReporte);

			FileOutputStream fosGrafico = null;

			List<ReporteLoteConfiguracionCliente> findConfiguracionesByClienteFormula = reporteConfiguracionService
					.findConfiguracionesByClienteFormula(lote.getFormula(), cliente);

			// Archivo de grafico
			List<LoteGrafico> graficos = loteGraficoService.listEqField("lote.id", lote.getId());

			for (LoteGrafico grafico : graficos) {
				ReporteLoteConfiguracionCliente conf = reporteConfiguracionService.buscarConfiguracion(idCliente,
						findConfiguracionesByClienteFormula, grafico.getMaquina().getId());
				if (conf != null && conf.getEnviarGrafico()) {
					String nombreGrafico = "Informe Calidad - Grafico - " + grafico.getMaquina() + " - "
							+ cliente.getNombre().replace(".", " - " + lote.getNroLote());
					File tempFileGrafico = File.createTempFile(nombreGrafico, ".pdf");
					fosGrafico = new FileOutputStream(tempFileGrafico);
					fosGrafico.write(grafico.getArchivo());
					archivos.add(tempFileGrafico);
				}
			}

			FileOutputStream fosAdjunto = null;
			if (adjuntoextra != null) {
				File tempFileAdjunto = File.createTempFile(nombreAdjunto, ".pdf");
				fosAdjunto = new FileOutputStream(tempFileAdjunto);
				fosAdjunto.write(adjuntoextra);
				archivos.add(tempFileAdjunto);
			}

			this.mailSenderSMTPService.sendMail("informes@nitrophyl.com.ar", correos.toArray(new String[0]), null, subject, msg, archivos);  
//			this.mailSenderSMTPService.sendMail("informes@nitrophyl.com.ar", correos.toArray(), subject, msg, archivos);

			fosReporte.close();

			if (fosGrafico != null)
				fosGrafico.close();

			if (fosAdjunto != null)
				fosAdjunto.close();

		} catch (BusinessException | IOException e) {
			throw new BusinessException(e);
		}

	}

	private String getFirmaElasint() {
		return "<strong><span style='color: green;'>Elasint S.R.L.</span> </strong> <br>" + "Dr. Rebizzo 5378<br>"
				+ "(1678) Caseros, Buenos Aires<br>" + "+54 11 4759-0592 / 4759-0954 / 4750-3052";
	}

	private String getFirmaNitrophyl() {
		return "<strong><span style='color: blue;'>Nitrophyl S.A.</span></strong> <br>" + "Dr. Rebizzo 5378<br>"
				+ "(1678) Caseros, Buenos Aires<br>" + "+54 11 4759-0592 / 4759-0954 / 4750-3052";
	}
	
	@Override
	public Boolean hasEnsayos(Long idLote) {
		return this.service.hasEnsayos(idLote);
	}

	@Override
	@Resource(name = "loteService")
	protected void setService(LoteService service) {
		this.service = service;
	}

	@Resource(name = "clienteService")
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@Resource(name = "formulaService")
	public void setFormulaService(FormulaService formulaService) {
		this.formulaService = formulaService;
	}

	@Resource(name = "reporteLoteConfiguracionClienteService")
	public void setReporteLoteConfigClienteService(
			ReporteLoteConfiguracionClienteService reporteLoteConfigClienteService) {
		this.reporteLoteConfigClienteService = reporteLoteConfigClienteService;
	}

	@Resource(name = "loteGraficoService")
	public void setLoteGraficoService(LoteGraficoService loteGraficoService) {
		this.loteGraficoService = loteGraficoService;
	}

	public void setMailSenderSMTPService(MailSenderSMTPService mailSenderSMTPService) {
		this.mailSenderSMTPService = mailSenderSMTPService;
	}

	@Resource(name = "reporteLoteConfiguracionClienteService")
	public void setReporteConfiguracionService(ReporteLoteConfiguracionClienteService reporteConfiguracionService) {
		this.reporteConfiguracionService = reporteConfiguracionService;
	}

}
