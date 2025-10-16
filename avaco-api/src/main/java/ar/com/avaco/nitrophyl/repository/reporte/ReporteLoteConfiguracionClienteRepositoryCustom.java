package ar.com.avaco.nitrophyl.repository.reporte;

import java.util.List;

import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.maquina.Maquina;
import ar.com.avaco.nitrophyl.domain.entities.reporte.ReporteLoteConfiguracionCliente;

public interface ReporteLoteConfiguracionClienteRepositoryCustom {

	List<ReporteLoteConfiguracionCliente> findConfiguracionesByClienteIdFormulaId(Long idFormula, Long idCliente);

	ReporteLoteConfiguracionCliente findByFormulaClienteMaquina(Formula formula, Cliente cliente, Maquina maquina);

}
