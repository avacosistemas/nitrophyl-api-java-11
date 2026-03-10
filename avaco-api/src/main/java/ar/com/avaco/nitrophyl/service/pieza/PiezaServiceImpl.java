package ar.com.avaco.nitrophyl.service.pieza;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Proceso;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Terminacion;
import ar.com.avaco.nitrophyl.domain.entities.pieza.moldeo.Vulcanizacion;
import ar.com.avaco.nitrophyl.repository.pieza.PiezaRepository;
import ar.com.avaco.nitrophyl.ws.dto.PiezaFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaGrillaDTO;

@Service("piezaService")
public class PiezaServiceImpl extends NJBaseService<Long, Pieza, PiezaRepository> implements PiezaService {

	@Resource(name = "piezaRepository")
	void setClienteRepository(PiezaRepository piezaRepository) {
		this.repository = piezaRepository;
	}

	@Override
	public Pieza save(Pieza entity) {
		Pieza save = super.save(entity);
		actualizarFaltantes(save);
		return save;
	}

	@Override
	public Pieza update(Pieza entity) {
		Pieza update = super.update(entity);
		actualizarFaltantes(entity);
		return update;
	}
	
	@Override
	public void actualizarFaltantes(Pieza pieza) {
		StringBuilder sb = new StringBuilder();

		Proceso proceso = pieza.getProceso();
		if (proceso.getPrensas() == null || proceso.getPrensas().isEmpty()) {
			sb.append("Falta cargar una prensa,");
		}

		Vulcanizacion vulcanizacion = proceso.getVulcanizacion();
		if (vulcanizacion == null || vulcanizacion.getTemperaturaMax() == null
				|| vulcanizacion.getTemperaturaMin() == null || vulcanizacion.getTiempo() == null) {
			sb.append("Falta cargar la vulcanización,");
		}

		if (proceso.getBombeos() == null || proceso.getBombeos().isEmpty()) {
			sb.append("Falta cargar al menos un bombeo,");
		}

		if (StringUtils.isBlank(proceso.getDesmoldante())) {
			sb.append("Falta cargar el desmoldante,");
		}

		if (StringUtils.isBlank(proceso.getPostCura())) {
			sb.append("Falta cargar la postcura,");
		}

		if (proceso.getEsquema() == null || proceso.getEsquema().isEmpty()) {
			sb.append("Falta cargar al menos un esquema,");
		}

		if (pieza.getDimensiones() == null || pieza.getDimensiones().isEmpty()) {
			sb.append("Faltan cargar las dimensiones,");
		}

		Terminacion terminacion = proceso.getTerminacion();
		if (terminacion == null || StringUtils.isBlank(terminacion.getEmbalaje())
				|| StringUtils.isBlank(terminacion.getIdentificacion())
				|| StringUtils.isBlank(terminacion.getRefilado()) || terminacion.getImagenTerminada() == null) {
			sb.append("Falta completar la terminacion,");
		}
		
		Integer cantinsumos = pieza.getCantidadInsumos();
		if (pieza.getRequiereInsumos() != null && pieza.getRequiereInsumos().booleanValue() && (cantinsumos == null || cantinsumos == 0 || (!cantinsumos.equals(pieza.getInsumos().size())))) {
			sb.append("Faltan cargar insumos,");
		}

		if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ',') {
		    sb.setLength(sb.length() - 1);
		}
		
		this.repository.actualizarFaltantes(pieza.getId(), sb.toString());
	}

	@Override
	public List<PiezaGrillaDTO> listGrilla(PiezaFilterDTO pfdto) {
		return this.repository.listGrilla(pfdto);
	}

	@Override
	public Pieza getVigenteByCodigoInterno(String codigoInterno) {
		return this.repository.findByCodigoAndVigente(codigoInterno, true);
	}

	@Override
	public boolean existsByFormula(Long idFormula) {
		return this.repository.existsByDetalleFormulaFormulaId(idFormula);
	}

}
