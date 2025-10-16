package ar.com.avaco.nitrophyl.ws.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPrueba;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPruebaParametro;
import ar.com.avaco.nitrophyl.domain.entities.lote.Ensayo;
import ar.com.avaco.nitrophyl.domain.entities.lote.EnsayoResultado;
import ar.com.avaco.nitrophyl.domain.entities.lote.EstadoEnsayo;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.nitrophyl.service.lote.EnsayoService;
import ar.com.avaco.nitrophyl.service.maquina.ConfiguracionPruebaService;
import ar.com.avaco.nitrophyl.ws.dto.EnsayoDTO;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("ensayoEPService")
public class EnsayoEPServiceImpl extends CRUDEPBaseService<Long, EnsayoDTO, Ensayo, EnsayoService>
		implements EnsayoEPService {

	private ConfiguracionPruebaService configuracionPruebaService;

	@Override
	@Resource(name = "ensayoService")
	protected void setService(EnsayoService service) {
		this.service = service;
	}

	@Resource(name = "configuracionPruebaService")
	public void setConfiguracionPruebaService(ConfiguracionPruebaService configuracionPruebaService) {
		this.configuracionPruebaService = configuracionPruebaService;
	}

	@Override
	protected Ensayo convertToEntity(EnsayoDTO dto) {
		Ensayo ensayo = new Ensayo();
		ensayo.setId(dto.getId());
		Lote lote = new Lote();
		lote.setId(dto.getIdLote());
		ensayo.setLote(lote);
		ensayo.setObservaciones(dto.getObservaciones());
		Set<EnsayoResultado> resultados = new HashSet<>();
		dto.getResultados().forEach(res -> {
			EnsayoResultado er = new EnsayoResultado();
			er.setConfiguracionPruebaParametro(
					new ConfiguracionPruebaParametro(res.getIdConfiguracionPruebaParametro()));
			er.setEnsayo(ensayo);
			er.setRedondeo(res.getRedondeo());
			er.setResultado(res.getResultado());
			resultados.add(er);
		});
		ensayo.setResultados(resultados);
		return ensayo;
	}

	@Override
	protected EnsayoDTO convertToDto(Ensayo entity) {
		EnsayoDTO dto = new EnsayoDTO();
		dto.setFecha(DateUtils.toStringFecha(entity.getFecha()));
		dto.setId(entity.getId());
		dto.setIdLote(entity.getLote().getId());
		dto.setMaquina(entity.getConfiguracionPrueba().getMaquina().getNombre());
		dto.setObservaciones(entity.getObservaciones());
		dto.setEstado(entity.getEstado().toString());
		return dto;
	}

	@Override
	public List<EnsayoDTO> listByLote(Long idLote) {
		List<Ensayo> ensayos = this.service.listByLote(idLote);
		List<EnsayoDTO> dtos = new ArrayList<EnsayoDTO>();
		ensayos.forEach(x -> dtos.add(convertToDto(x)));
		return dtos;
	}

	@Override
	public EnsayoDTO save(EnsayoDTO dto) throws BusinessException {
		// Convierto el dto en entidad
		Ensayo ensayo = convertToEntity(dto);

		// Obtengo la configuracion de la prueba
		ConfiguracionPrueba configuracionPrueba = configuracionPruebaService.get(dto.getIdConfiguracionPrueba());

		// Limpio el ID
		ensayo.setId(null);

		// Seteo la configuracion
		ensayo.setConfiguracionPrueba(configuracionPrueba);

		// Seteo la fecha
		ensayo.setFecha(DateUtils.toDate(dto.getFecha(), DateUtils.dd_MM_yyyy));

		// Seteo la fecha
		ensayo.setEstado(EstadoEnsayo.valueOf(dto.getEstado()));

		Ensayo save = this.service.save(ensayo);
		dto.setId(save.getId());
		return dto;
	}

//	private String condicionesToString(Set<ConfiguracionPruebaCondicion> condiciones) {
//		Stream<String> map = condiciones.stream().map(x -> x.getNombre() + "=" + x.getValor());
//		List<String> collect = map.collect(Collectors.toList());
//		return String.join(";", collect);
//	}

}
