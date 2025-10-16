package ar.com.avaco.nitrophyl.ws.service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPrueba;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPruebaCondicion;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPruebaParametro;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.maquina.Maquina;
import ar.com.avaco.nitrophyl.domain.entities.maquina.MaquinaPrueba;
import ar.com.avaco.nitrophyl.service.maquina.ConfiguracionPruebaService;
import ar.com.avaco.nitrophyl.ws.dto.ConfiguracionPruebaCondicionDTO;
import ar.com.avaco.nitrophyl.ws.dto.ConfiguracionPruebaDTO;
import ar.com.avaco.nitrophyl.ws.dto.ConfiguracionPruebaParametroDTO;
import ar.com.avaco.nitrophyl.ws.dto.MaquinaPruebaDTO;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("configuracionPruebaEPService")
public class ConfiguracionPruebaEPServiceImpl
		extends CRUDEPBaseService<Long, ConfiguracionPruebaDTO, ConfiguracionPrueba, ConfiguracionPruebaService>
		implements ConfiguracionPruebaEPService {

	@Override
	protected ConfiguracionPrueba convertToEntity(ConfiguracionPruebaDTO dto) {
		ConfiguracionPrueba cf = new ConfiguracionPrueba();

		cf.setId(dto.getId());

		Formula formula = new Formula();
		formula.setId(dto.getIdFormula());
		cf.setFormula(formula);

		Maquina maquina = new Maquina();
		maquina.setId(dto.getIdMaquina());
		cf.setMaquina(maquina);

		cf.setObservacionesReporte(dto.getObservacionesReporte());

		cf.setParametros(new HashSet<>(dto.getParametros().stream().map(x -> new ConfiguracionPruebaParametro(cf,
				new MaquinaPrueba(x.getMaquinaPrueba().getId()), x.getMinimo(), x.getMaximo(), x.getNorma(), x.getId()))
				.collect(Collectors.toList())));

		cf.setCondiciones(new HashSet<>(dto.getCondiciones().stream()
				.map(x -> new ConfiguracionPruebaCondicion(cf, x.getNombre(), x.getValor(), x.getId()))
				.collect(Collectors.toList())));

		return cf;
	}

	@Override
	protected ConfiguracionPruebaDTO convertToDto(ConfiguracionPrueba entity) {
		ConfiguracionPruebaDTO dto = convertToDtoSinCondicionParametro(entity);
		dto.setCondiciones(entity.getCondiciones().stream().map(x -> convertCondToDto(x)).collect(Collectors.toList()));
		dto.setParametros(entity.getParametros().stream().map(x -> convertParamToDto(x)).collect(Collectors.toList()));
		return dto;
	}

	protected ConfiguracionPruebaDTO convertToDtoSinCondicionParametro(ConfiguracionPrueba entity) {
		ConfiguracionPruebaDTO dto = new ConfiguracionPruebaDTO();
		dto.setId(entity.getId());
		dto.setIdFormula(entity.getFormula().getId());
		dto.setIdMaquina(entity.getMaquina().getId());
		dto.setMaquina(entity.getMaquina().getNombre());
		dto.setObservacionesReporte(entity.getObservacionesReporte());
		dto.setVersion(entity.getVersion());
		dto.setFecha(DateUtils.toStringFecha(entity.getFecha()));
		dto.setFechaHasta(DateUtils.toStringFecha(entity.getFechaHasta()));
		dto.setVigente(entity.getVigente());
		return dto;
	}

	private ConfiguracionPruebaParametroDTO convertParamToDto(ConfiguracionPruebaParametro cfp) {
		ConfiguracionPruebaParametroDTO dto = new ConfiguracionPruebaParametroDTO();
		dto.setId(cfp.getId());
		dto.setMaximo(cfp.getMaximo());
		dto.setMinimo(cfp.getMinimo());
		MaquinaPruebaDTO maquinaPruebaDTO = new MaquinaPruebaDTO();
		maquinaPruebaDTO.setId(cfp.getMaquinaPrueba().getId());
		maquinaPruebaDTO.setNombre(cfp.getMaquinaPrueba().getNombre());
		maquinaPruebaDTO.setPosicion(cfp.getMaquinaPrueba().getPosicion());
		dto.setMaquinaPrueba(maquinaPruebaDTO);
		dto.setNorma(cfp.getNorma());
		return dto;
	}

	private ConfiguracionPruebaCondicionDTO convertCondToDto(ConfiguracionPruebaCondicion cfp) {
		ConfiguracionPruebaCondicionDTO dto = new ConfiguracionPruebaCondicionDTO();
		dto.setId(cfp.getId());
		dto.setValor(cfp.getValor());
		dto.setNombre(cfp.getNombre());
		return dto;
	}

	@Override
	public List<ConfiguracionPruebaDTO> list(Long idFormula) {
		List<ConfiguracionPrueba> listByIdFormula = this.service.listByFormulaId(idFormula);
		List<ConfiguracionPruebaDTO> list = listByIdFormula.stream().map(x -> convertToDtoSinCondicionParametro(x))
				.collect(Collectors.toList());
		list.stream().forEach(cp -> cp.setIsPuedeIncorporarRevisionVigente(cp.getVersion() == 0L && !cp.getVigente()
				&& list.parallelStream().filter(x -> x.getMaquina().equals(cp.getMaquina())).count() == 1));
		return list;
	}

	@Override
	public ConfiguracionPruebaDTO get(Long id) {
		ConfiguracionPruebaDTO configuracionPruebaDTO = super.get(id);
		List<ConfiguracionPruebaParametroDTO> sorted = configuracionPruebaDTO.getParametros().stream()
				.sorted(Comparator.comparing(ConfiguracionPruebaParametroDTO::getOrden)).collect(Collectors.toList());
		configuracionPruebaDTO.getParametros().clear();
		configuracionPruebaDTO.getParametros().addAll(sorted);
		return configuracionPruebaDTO;
	}

	@Override
	public List<ConfiguracionPruebaDTO> listVigentesByLote(Long idLote) {
		List<ConfiguracionPrueba> listByLote = this.service.listByLote(idLote);
		return listByLote.stream().map(x -> convertToDtoSinCondicionParametro(x)).collect(Collectors.toList());
	}

	@Override
	public ConfiguracionPruebaDTO update(ConfiguracionPruebaDTO dto) throws BusinessException {
		ConfiguracionPrueba current = this.service.get(dto.getId());
		ConfiguracionPrueba update = convertToEntity(dto);
		update.setVersion(current.getVersion());
		update.setFecha(current.getFecha());
		update.setFechaHasta(current.getFechaHasta());
		update.setVigente(current.getVigente());
		update.setMaquina(current.getMaquina());
		update.setFormula(current.getFormula());
		return convertToDto(this.service.update(update));
	}

	/**
	 * Agrega una configuracin de pruebas asociadas a la formula y la asocia a la
	 * revisin vigente.
	 */
	@Override
	public void setarVigente(Long idConfiguracionPrueba) {
		this.service.setearVigente(idConfiguracionPrueba);
	}

	@Override
	@Resource(name = "configuracionPruebaService")
	protected void setService(ConfiguracionPruebaService service) {
		this.service = service;
	}

}
