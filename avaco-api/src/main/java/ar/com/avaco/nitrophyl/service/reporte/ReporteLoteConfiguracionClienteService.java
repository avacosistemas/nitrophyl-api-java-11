package ar.com.avaco.nitrophyl.service.reporte;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.reporte.ReporteLoteConfiguracionCliente;

public interface ReporteLoteConfiguracionClienteService extends NJService<Long, ReporteLoteConfiguracionCliente> {

	List<ReporteLoteConfiguracionCliente> findConfiguracionesByClienteFormula(Formula formula, Cliente cliente);

	ReporteLoteConfiguracionCliente buscarConfiguracion(Long idCliente,
			List<ReporteLoteConfiguracionCliente> configuracion, Long idMaquina);

}
