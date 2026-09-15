package ar.com.avaco.nitrophyl.service.pieza;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaControl;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaDimension;
import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoControl;
import ar.com.avaco.nitrophyl.domain.entities.pieza.UnidadDureza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.insumo.InsumoTratado;
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
				.flatMap(i -> generarControlInsumo(i)).collect(Collectors.toList());

		List<PiezaControl> controlesDimensiones = pieza.getDimensiones().stream().filter(PiezaDimension::getControlar)
				.map(pd -> generarControlDimension(pd)).collect(Collectors.toList());

		Double durezaMaxima = pieza.getDetalleFormula().getFormula().getDurezaMaxima();
		Double durezaMinima = pieza.getDetalleFormula().getFormula().getDurezaMinima();
		UnidadDureza unidadDureza = pieza.getDetalleFormula().getFormula().getUnidadDureza();

		PiezaControl durezaControl = new PiezaControl();
		durezaControl.setTipo(TipoControl.DUREZA);
		durezaControl.setControl(durezaMinima + " - " + durezaMaxima + " (" + unidadDureza.name() + ")");

		List<PiezaControl> controles = new ArrayList<PiezaControl>();

		controles.addAll(controlesInsumos);
		controles.addAll(controlesDimensiones);
		controles.add(durezaControl);

		return controles;

	}

	private Stream<PiezaControl> generarControlInsumo(InsumoTratado i) {
		return i.getObservaciones().stream().filter(InsumoTratadoObservacionControl::getControlar).map(o -> {
			String control = i.getInsumo().getNombre();
			if (o.getObservacion() != null) {
				control = control + " - " + o.getObservacion();
			}
			return new PiezaControl(control, i.getPieza(), TipoControl.INSUMO);
		});
	}

	private PiezaControl generarControlDimension(PiezaDimension pd) {
		String control = pd.getTipo() + " - " + pd.getValor();
		if (pd.getMinimo() != null) {
			control = control + " (" + pd.getMinimo() + " / " + pd.getMaximo() + ")";
		}
		if (pd.getObservaciones() != null) {
			control = control + " - " + pd.getObservaciones();
		}
		return new PiezaControl(control, pd.getPieza(), TipoControl.MEDIDA);
	}

}
