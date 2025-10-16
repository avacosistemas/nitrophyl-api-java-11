package ar.com.avaco.nitrophyl.service.reporte;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.reporte.ReporteLoteConfiguracionCliente;
import ar.com.avaco.nitrophyl.repository.reporte.ReporteLoteConfiguracionClienteRepository;

@Transactional
@Service("reporteLoteConfiguracionClienteService")
public class ReporteLoteConfiguracionClienteServiceImpl
		extends NJBaseService<Long, ReporteLoteConfiguracionCliente, ReporteLoteConfiguracionClienteRepository>
		implements ReporteLoteConfiguracionClienteService {

	@Override
	public ReporteLoteConfiguracionCliente save(ReporteLoteConfiguracionCliente entity) {
		ReporteLoteConfiguracionCliente find = this.repository.findByFormulaClienteMaquina(entity.getFormula(),
				entity.getCliente(), entity.getMaquina());
		if (find != null) {
			throw new ErrorValidationException(
					"Ya existe un registro con esa combinacion de frmula, cliente y mquina con id " + find.getId(), null);
		}
		return super.save(entity);
	}

	@Override
	public ReporteLoteConfiguracionCliente update(ReporteLoteConfiguracionCliente entity) {
		ReporteLoteConfiguracionCliente find = this.repository.findByFormulaClienteMaquina(entity.getFormula(),
				entity.getCliente(), entity.getMaquina());
		if (find != null && !entity.getId().equals(find.getId())) {
			throw new ErrorValidationException(
					"Ya existe un registro con esa combinacion de frmula, cliente y mquina", null);
		}
		return super.update(entity);
	}

	@Override
	public List<ReporteLoteConfiguracionCliente> findConfiguracionesByClienteFormula(Formula formula, Cliente cliente) {
		return this.repository.findConfiguracionesByClienteIdFormulaId(formula.getId(), cliente.getId());
	}

	@Override
	public ReporteLoteConfiguracionCliente buscarConfiguracion(Long idCliente,
			List<ReporteLoteConfiguracionCliente> configuracion, Long idMaquina) {
		ReporteLoteConfiguracionCliente reporteLoteConfiguracionCliente = null;

		// Busco coincidencia maquina-cliente
		Optional<ReporteLoteConfiguracionCliente> findFirst = configuracion.stream()
				.filter(x -> x.getMaquina() != null && x.getMaquina().getId() == idMaquina && x.getCliente() != null
						&& x.getCliente().getId() == idCliente)
				.findFirst();

		if (findFirst.isPresent()) {
			reporteLoteConfiguracionCliente = findFirst.get();
		} else {
			// Busco coincidencia solo de maquina para todos los clientes
			findFirst = configuracion.stream().filter(
					x -> x.getMaquina() != null && x.getMaquina().getId() == idMaquina && x.getCliente() == null)
					.findFirst();
			if (findFirst.isPresent()) {
				reporteLoteConfiguracionCliente = findFirst.get();
			} else {
				// Busco coincidencia solo en cliente para todas las maquinas
				findFirst = configuracion.stream().filter(x -> x.getMaquina() == null && x.getCliente() != null
						&& x.getCliente().getId() == idCliente).findFirst();
				if (findFirst.isPresent()) {
					reporteLoteConfiguracionCliente = findFirst.get();
				} else {
					findFirst = configuracion.stream().filter(x -> x.getMaquina() == null && x.getCliente() == null)
							.findFirst();
					if (findFirst.isPresent())
						reporteLoteConfiguracionCliente = findFirst.get();
				}
			}
		}
		return reporteLoteConfiguracionCliente;
	}

	@Resource(name = "reporteLoteConfiguracionClienteRepository")
	public void setReporteLoteConfiguracionClienteRepository(
			ReporteLoteConfiguracionClienteRepository reporteLoteConfiguracionClienteRepository) {
		this.repository = reporteLoteConfiguracionClienteRepository;
	}

}
