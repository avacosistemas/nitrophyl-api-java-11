package ar.com.avaco.nitrophyl.ws.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaControl;
import ar.com.avaco.nitrophyl.service.pieza.PiezaControlService;
import ar.com.avaco.nitrophyl.ws.dto.PiezaControlDTO;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Transactional
@Service("piezaControlEPService")
public class PiezaControlEPServiceImpl
		extends CRUDAuditableEPBaseService<Long, PiezaControlDTO, PiezaControl, PiezaControlService>
		implements PiezaControlEPService {

	public PiezaControlEPServiceImpl() {
		super(PiezaControl.class, PiezaControlDTO.class);
	}

	@Override
	@Resource(name = "piezaControlService")
	protected void setService(PiezaControlService service) {
		this.service = service;
	}

	@Override
	public List<PiezaControlDTO> listControlesConfigurados(Long idPieza) {
		return this.convertToDtos(service.listControlesConfigurados(idPieza));
	}

}
