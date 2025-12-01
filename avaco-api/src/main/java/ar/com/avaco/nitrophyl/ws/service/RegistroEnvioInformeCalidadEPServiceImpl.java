package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.reporte.RegistroEnvioInformeCalidad;
import ar.com.avaco.nitrophyl.service.lote.RegistroEnvioInformeCalidadService;
import ar.com.avaco.nitrophyl.ws.dto.RegistroEnvioInformeCalidadDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("registroEnvioInformeCalidadEPService")
public class RegistroEnvioInformeCalidadEPServiceImpl extends CRUDAuditableEPBaseService<Long, RegistroEnvioInformeCalidadDTO, RegistroEnvioInformeCalidad, RegistroEnvioInformeCalidadService>
		implements RegistroEnvioInformeCalidadEPService {

	public RegistroEnvioInformeCalidadEPServiceImpl() {
		super(RegistroEnvioInformeCalidad.class, RegistroEnvioInformeCalidadDTO.class);
	}

	@Override
	protected RegistroEnvioInformeCalidadDTO convertToDto(RegistroEnvioInformeCalidad entity) {
		RegistroEnvioInformeCalidadDTO dto = new RegistroEnvioInformeCalidadDTO();
		dto.setCliente(entity.getCliente().getNombre());
		dto.setEmailEnviado(entity.getEmailEnviado());
		dto.setFormula(entity.getLote().getFormula().getNombre());
		dto.setLote(entity.getLote().getNroLote());
		dto.setObservacionesInforme(entity.getObservacionesInforme());
		dto.setObservacionesMail(entity.getObservacionesMail());
		return dto;
	}
	
	@Override
	@Resource(name = "registroEnvioInformeCalidadService")
	protected void setService(RegistroEnvioInformeCalidadService service) {
		this.service = service;
	}

}
