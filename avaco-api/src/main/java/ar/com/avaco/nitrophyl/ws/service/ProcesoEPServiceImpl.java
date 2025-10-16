package ar.com.avaco.nitrophyl.ws.service;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.fabrica.Prensa;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Bombeo;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Precalentamiento;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Proceso;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Vulcanizacion;
import ar.com.avaco.nitrophyl.service.pieza.ProcesoService;
import ar.com.avaco.nitrophyl.ws.dto.DesmoldantepostcuraPUTDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeoPUTDTO;
import ar.com.avaco.nitrophyl.ws.dto.ProcesoDTO;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("procesoEPService")
public class ProcesoEPServiceImpl extends CRUDAuditableEPBaseService<Long, ProcesoDTO, Proceso, ProcesoService>
		implements ProcesoEPService {

	public ProcesoEPServiceImpl() {
		super(Proceso.class, ProcesoDTO.class);
	}

	@Override
	@Resource(name = "procesoService")
	protected void setService(ProcesoService service) {
		this.service = service;
	}

	@Override
	public void updateDesmoldantePostcura(Long id, DesmoldantepostcuraPUTDTO dto) {
		Proceso proceso = this.service.get(id);
		proceso.setDesmoldante(dto.getDesmoldante());
		proceso.setPostCura(dto.getPostCura());
		proceso.setFechaActualizacion(DateUtils.getFechaYHoraActual());
		proceso.setUsuarioActualizacion(SecurityContextHolder.getContext().getAuthentication().getName());
		this.service.update(proceso);
		
	}

	@Override
	public void updateMoldeo(Long id, MoldeoPUTDTO dto) {
		Proceso proceso = this.service.get(id);

		// Prensa
		proceso.getPrensas().clear();
		dto.getPrensas().forEach(idPrensa -> proceso.getPrensas().add(Prensa.ofId(idPrensa)));
		
		// Precalentamiento
		if (proceso.getPrecalentamiento() == null) proceso.setPrecalentamiento(new Precalentamiento());
		proceso.getPrecalentamiento().setUnidad(dto.getPrecalentamientoUnidad());
		proceso.getPrecalentamiento().setValor(dto.getPrecalentamientoValor());
		
		// Vulcanizacion
		if (proceso.getVulcanizacion() == null) proceso.setVulcanizacion(new Vulcanizacion());
		proceso.getVulcanizacion().setTemperaturaMax(dto.getVulcanizacionTemperaturaMax());
		proceso.getVulcanizacion().setTemperaturaMin(dto.getVulcanizacionTemperaturaMin());
		proceso.getVulcanizacion().setTiempo(dto.getVulcanizacionTiempo());
		
		// Bombeos
		proceso.getBombeos().clear();
		dto.getBombeos().forEach(b -> { 
			Bombeo bombeo = super.modelMapper.map(b, Bombeo.class);
			bombeo.setUsuarioCreacion(SecurityContextHolder.getContext().getAuthentication().getName());
			bombeo.setFechaCreacion(DateUtils.getFechaYHoraActual());
			bombeo.setUsuarioActualizacion(SecurityContextHolder.getContext().getAuthentication().getName());
			bombeo.setFechaActualizacion(DateUtils.getFechaYHoraActual());
			bombeo.setProceso(proceso);
			proceso.getBombeos().add(bombeo);
			} 
		);
		
		proceso.setUsuarioActualizacion(SecurityContextHolder.getContext().getAuthentication().getName());
		proceso.setFechaActualizacion(DateUtils.getFechaYHoraActual());
		
		this.service.update(proceso);
		
	}

}