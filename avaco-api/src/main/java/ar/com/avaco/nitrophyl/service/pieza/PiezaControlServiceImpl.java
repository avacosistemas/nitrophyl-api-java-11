package ar.com.avaco.nitrophyl.service.pieza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaControl;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaDimension;
import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoControl;
import ar.com.avaco.nitrophyl.domain.entities.pieza.insumo.InsumoTratadoObservacionControl;
import ar.com.avaco.nitrophyl.repository.pieza.PiezaControlRepository;

@Service("piezaControlService")
public class PiezaControlServiceImpl extends NJBaseService<Long, PiezaControl, PiezaControlRepository>
		implements PiezaControlService {

	@Resource(name = "piezaControlRepository")
	void setPiezaControlRepository(PiezaControlRepository piezaControlRepository) {
		this.repository = piezaControlRepository;
	}

	@Autowired
	private PiezaService piezaService;

	@Override
	public List<PiezaControl> listControlesConfigurados(Long idPieza) {
		Pieza pieza = this.piezaService.get(idPieza);

		List<PiezaControl> controlesInsumos = pieza.getInsumos().stream().filter(i -> i.getObservaciones() != null)
				.flatMap(i -> i.getObservaciones().stream().filter(InsumoTratadoObservacionControl::getControlar)
						.map(o -> new PiezaControl(i.getInsumo().getNombre() + " - " + o.getObservacion(), i.getPieza(),
								TipoControl.INSUMO)))
				.collect(Collectors.toList());

		List<PiezaControl> controlesDimensiones = pieza.getDimensiones().stream().filter(PiezaDimension::getControlar)
				.map(pd -> new PiezaControl(pd.getTipo() + " - " + pd.getObservaciones(), pd.getPieza(),
						TipoControl.MEDIDA))
				.collect(Collectors.toList());

		List<PiezaControl> controles = new ArrayList<PiezaControl>();
		controles.addAll(controlesInsumos);
		controles.addAll(controlesDimensiones);

		return controles;

	}

}
