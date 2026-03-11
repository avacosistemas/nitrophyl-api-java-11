package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.insumo.InsumoTratado;
import ar.com.avaco.nitrophyl.repository.pieza.InsumoTratadoRepository;

@Transactional
@Service("insumoTratadoService")
public class InsumoTratadoServiceImpl extends NJBaseService<Long, InsumoTratado, InsumoTratadoRepository> implements InsumoTratadoService {

	@Resource(name = "insumoTratadoRepository")
	void setRepository(InsumoTratadoRepository insumoTratadoRepository) {
		this.repository = insumoTratadoRepository;
	}

	@Autowired
	private PiezaService piezaService;
	
	@Override
	public InsumoTratado save(InsumoTratado entity) {
		InsumoTratado save = super.save(entity);
		piezaService.actualizarFaltantes(entity.getPieza());
		return save;
	}
	
	@Override
	public void remove(Long id) {
		InsumoTratado insumoTratado = this.get(id);
		insumoTratado.getPieza().getInsumos().remove(insumoTratado);
		this.piezaService.update(insumoTratado.getPieza());
	}
	
}
