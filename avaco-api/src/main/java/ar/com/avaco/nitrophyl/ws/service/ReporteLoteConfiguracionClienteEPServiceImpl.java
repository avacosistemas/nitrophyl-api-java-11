package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.maquina.Maquina;
import ar.com.avaco.nitrophyl.domain.entities.maquina.MaquinaPrueba;
import ar.com.avaco.nitrophyl.domain.entities.reporte.ReporteLoteConfiguracionCliente;
import ar.com.avaco.nitrophyl.service.reporte.ReporteLoteConfiguracionClienteService;
import ar.com.avaco.nitrophyl.ws.dto.ReporteLoteConfiguracionClienteDTO;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("reporteLoteConfiguracionClienteEPService")
public class ReporteLoteConfiguracionClienteEPServiceImpl extends
		CRUDEPBaseService<Long, ReporteLoteConfiguracionClienteDTO, ReporteLoteConfiguracionCliente, ReporteLoteConfiguracionClienteService>
		implements ReporteLoteConfiguracionClienteEPService {

	@Override
	protected ReporteLoteConfiguracionCliente convertToEntity(ReporteLoteConfiguracionClienteDTO dto) {
		ReporteLoteConfiguracionCliente entity = new ReporteLoteConfiguracionCliente();
		entity.setId(dto.getId());

		if (dto.getIdCliente() != null && dto.getIdCliente() != 0) {
			Cliente cliente = new Cliente();
			cliente.setId(dto.getIdCliente());
			entity.setCliente(cliente);
		}

		Formula formula = new Formula();
		formula.setId(dto.getIdFormula());
		entity.setFormula(formula);

		if (dto.getIdMaquina() != null && dto.getIdMaquina() != 0) {
			Maquina maquina = new Maquina();
			maquina.setId(dto.getIdMaquina());
			entity.setMaquina(maquina);
			
			for (Long idMaquinaPrueba : dto.getIdsPruebas()) {
				entity.getPruebas().add(new MaquinaPrueba(idMaquinaPrueba));
			}
		}

		entity.setMostrarObservacionesParametro(dto.getMostrarObservacionesParametro());
		entity.setMostrarCondiciones(dto.getMostrarCondiciones());
		entity.setMostrarParametros(dto.getMostrarParametros());
		entity.setMostrarResultados(dto.getMostrarResultados());
		entity.setEnviarGrafico(dto.getEnviarGrafico());

		return entity;
	}

	@Override
	protected ReporteLoteConfiguracionClienteDTO convertToDto(ReporteLoteConfiguracionCliente entity) {
		ReporteLoteConfiguracionClienteDTO dto = new ReporteLoteConfiguracionClienteDTO();

		dto.setId(entity.getId());

		if (entity.getCliente() != null) {
			dto.setCliente(entity.getCliente().getNombre());
			dto.setIdCliente(entity.getCliente().getId());
		}

		dto.setFormula(entity.getFormula().getNombre());
		dto.setIdFormula(entity.getFormula().getId());

		if (entity.getMaquina() != null) {
			dto.setIdMaquina(entity.getMaquina().getId());
			dto.setMaquina(entity.getMaquina().getNombre());
		}

		dto.setMostrarObservacionesParametro(entity.getMostrarObservacionesParametro());
		dto.setMostrarCondiciones(entity.getMostrarCondiciones());
		dto.setMostrarParametros(entity.getMostrarParametros());
		dto.setMostrarResultados(entity.getMostrarResultados());
		dto.setEnviarGrafico(entity.getEnviarGrafico());
		
		for (MaquinaPrueba mp : entity.getPruebas()) {
			dto.getIdsPruebas().add(mp.getId());
		}
		
		return dto;
	}

	@Override
	@Resource(name = "reporteLoteConfiguracionClienteService")
	protected void setService(ReporteLoteConfiguracionClienteService service) {
		this.service = service;
	}

}
