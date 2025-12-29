package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.formula.Material;
import ar.com.avaco.nitrophyl.domain.entities.formula.RevisionParametros;
import ar.com.avaco.nitrophyl.service.formula.FormulaService;
import ar.com.avaco.nitrophyl.service.formula.MaterialService;
import ar.com.avaco.nitrophyl.service.formula.RevisionParametroService;
import ar.com.avaco.nitrophyl.service.lote.LoteService;
import ar.com.avaco.nitrophyl.service.maquina.ConfiguracionPruebaService;
import ar.com.avaco.nitrophyl.service.pieza.PiezaService;
import ar.com.avaco.nitrophyl.service.reporte.ReporteLoteConfiguracionClienteService;
import ar.com.avaco.nitrophyl.ws.dto.FormulaDTO;
import ar.com.avaco.nitrophyl.ws.dto.RevisionParametrosDTO;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Transactional
@Service("formulaEPService")
public class FormulaEPServiceImpl extends CRUDEPBaseService<Long, FormulaDTO, Formula, FormulaService>
		implements FormulaEPService {

	private MaterialService materialService;

	@Autowired
	private LoteService loteService;

	@Autowired
	private PiezaService piezaService;

	@Autowired
	private ReporteLoteConfiguracionClienteService reporteLoteConfiguracionClienteService;

	@Autowired
	private ConfiguracionPruebaService configuracionPruebaService;

	@Autowired
	private RevisionParametroService revisionParametroService;
	
	@Override
	protected Formula convertToEntity(FormulaDTO dto) {
		Formula formula;
		if (dto.getId() == null) {
			formula = new Formula();
			formula.setId(dto.getId());
			Material material = materialService.get(dto.getIdMaterial());
			formula.setMaterial(material);
			formula.setNombre(dto.getNombre());
		} else {
			formula = this.service.get(dto.getId());
		}
		formula.setNorma(dto.getNorma());
		formula.setObservaciones(dto.getObservaciones());
		formula.setDurezaMinima(dto.getDurezaMinima());
		formula.setDurezaMaxima(dto.getDurezaMaxima());
		formula.setUnidadDureza(dto.getUnidadDureza());
		return formula;
	}

	@Override
	protected FormulaDTO convertToDto(Formula entity) {
		FormulaDTO dto = new FormulaDTO();
		dto.setId(entity.getId());
		dto.setIdMaterial(entity.getMaterial().getId());
		dto.setMaterial(entity.getMaterial().getNombre());
		dto.setNombre(entity.getNombre());
		dto.setVersion(entity.getVersion());
		dto.setFecha(DateUtils.toStringFecha(entity.getFecha()));
		dto.setNorma(entity.getNorma());
		dto.setObservaciones(entity.getObservaciones());
		dto.setDurezaMinima(entity.getDurezaMinima());
		dto.setDurezaMaxima(entity.getDurezaMaxima());
		dto.setUnidadDureza(entity.getUnidadDureza());
		RevisionParametros revision = entity.getRevision();
		if (revision != null) {
			RevisionParametrosDTO rpdto = new RevisionParametrosDTO();
			rpdto.setFecha(DateUtils.toStringFecha(revision.getFecha()));
			rpdto.setFechaHasta(DateUtils.toStringFecha(revision.getFechaHasta()));
			rpdto.setRevision(revision.getRevision());
			rpdto.setId(revision.getId());
			dto.setRpdto(rpdto);
		}
		return dto;
	}

	@Override
	public FormulaDTO clone(FormulaDTO dto) throws BusinessException {
		Formula entity = convertToEntity(dto);
		Formula clone = this.service.clone(entity);
		return convertToDto(clone);
	}

	@Override
	public RevisionParametrosDTO marcarRevision(Long idFormula) {
		RevisionParametros rp = this.service.marcarRevision(idFormula);
		RevisionParametrosDTO rpdto = new RevisionParametrosDTO();
		rpdto.setFecha(DateUtils.toStringFecha(rp.getFecha()));
		rpdto.setFechaHasta(DateUtils.toStringFecha(rp.getFechaHasta()));
		rpdto.setId(rp.getId());
		rpdto.setRevision(rp.getRevision());
		return rpdto;
	}

	@Override
	public void remove(Long id) {
		
		// Valido que la formula no este asociada a ningun lote, pieza o configuracion de reporte.
		
		if (loteService.existsByFormula(id)) {
			throw new ErrorValidationException("No se puede borrar la formula. Tiene lotes asociados.");
		}

		if (piezaService.existsByFormula(id)) {
			throw new ErrorValidationException("No se puede borrar la formula. Tiene piezas asociadas.");
		}
		
		if (reporteLoteConfiguracionClienteService.existsByFormula(id)) {
			throw new ErrorValidationException("No se puede borrar la formula. Tiene configuraciones de reporte de calidad asociados.");
		}
		
		// Borro la configuracion de pruebas y revisiones
		
		// borro la configuracion de pruebas (en cascada su parametrizacion y condicion)
		this.configuracionPruebaService.deleteByFormulaId(id);
		
		this.revisionParametroService.deleteByFormula(id);
		
		super.remove(id);
	}
	
	@Override
	@Resource(name = "formulaService")
	protected void setService(FormulaService service) {
		this.service = service;
	}

	@Resource(name = "materialService")
	public void setMaterialService(MaterialService materialService) {
		this.materialService = materialService;
	}

}
