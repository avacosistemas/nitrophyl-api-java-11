package ar.com.avaco.nitrophyl.service.lote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.formula.RevisionParametros;
import ar.com.avaco.nitrophyl.domain.entities.lote.EstadoLote;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.nitrophyl.repository.lote.EnsayoRepository;
import ar.com.avaco.nitrophyl.repository.lote.LoteRepository;
import ar.com.avaco.nitrophyl.service.formula.FormulaService;
import ar.com.avaco.nitrophyl.ws.dto.RegistroEnsayoLotePorMaquinaDTO;
import ar.com.avaco.nitrophyl.ws.dto.ReporteEnsayoLotePorMaquinaFilterDTO;
import ar.com.avaco.utils.DateUtils;

@Transactional
@Service("loteService")
public class LoteServiceImpl extends NJBaseService<Long, Lote, LoteRepository> implements LoteService {

	private EnsayoRepository ensayoRepository;
	private FormulaService formulaService;
	
	
	@Override
	public Lote save(Lote entity) {
		Formula formula = formulaService.get(entity.getFormula().getId());
		entity.setId(null);
		entity.setRevisionParametros(formula.getRevision());
		return super.save(entity);
	}

	@Override
	public void aprobar(Long idLote, String estado, String observaciones) {
		this.repository.updateEstadoLote(idLote, EstadoLote.valueOf(estado), observaciones,
				DateUtils.getFechaYHoraActual());
	}

	@Override
	public void rechazar(Long idLote, String observaciones) {
		this.repository.updateEstadoLote(idLote, EstadoLote.RECHAZADO, observaciones, DateUtils.getFechaYHoraActual());
	}

	@Override
	public void borrar(Long idLote) {
		this.repository.deleteById(idLote);

	}

	@Override
	public boolean hasEnsayos(Long idLote) {
		return this.ensayoRepository.countByLoteId(idLote) > 0;
	}

	public Lote getLoteCompleto(Long idLote) {
		return this.repository.findById(idLote).orElse(null);
	}
	
	@Override
	public List<RegistroEnsayoLotePorMaquinaDTO> getRegistrosEnsayosLotePorMaquina(ReporteEnsayoLotePorMaquinaFilterDTO filtro) {
		return this.repository.getEnsayosLotePorMaquina(filtro);
	}
	
	@Resource(name = "ensayoRepository")
	public void setEnsayoRepository(EnsayoRepository ensayoRepository) {
		this.ensayoRepository = ensayoRepository;
	}
	
	@Resource(name = "loteRepository")
	public void setLoteRepository(LoteRepository loteRepository) {
		this.repository = loteRepository;
	}

	@Resource(name = "formulaService")
	public void setFormulaService(FormulaService formulaService) {
		this.formulaService = formulaService;
	}

	@Override
	public void revisiones() {
		Map<Long, RevisionParametros> revisiones = new HashMap<>();
		List<Formula> formulas = this.formulaService.list();
		formulas.forEach(x -> {
			RevisionParametros revision;
			if (x.getRevision() == null) {
				revision = this.formulaService.marcarRevision(x.getId());
			} else {
				revision = x.getRevision();
			}
			revisiones.put(x.getId(), revision);
		});
		
		List<Lote> lotes = this.list();
		lotes.forEach(x -> {
			if (x.getRevisionParametros() == null) {
				RevisionParametros revisionParametros = revisiones.get(x.getFormula().getId());
				x.setRevisionParametros(revisionParametros);
				this.update(x);
			}
		});
	}
	

}