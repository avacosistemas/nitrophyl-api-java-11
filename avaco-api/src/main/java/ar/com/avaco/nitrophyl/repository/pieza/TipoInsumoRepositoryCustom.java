package ar.com.avaco.nitrophyl.repository.pieza;

import java.util.List;

import ar.com.avaco.nitrophyl.domain.entities.pieza.insumo.TipoInsumo;

public interface TipoInsumoRepositoryCustom {

	List<TipoInsumo> listHijos();
	
}
