package ar.com.avaco.nitrophyl.ws.service.filter;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.nitrophyl.ws.dto.SortPageDTO;

public class SimplePager extends AbstractFilter {

	public SimplePager() {
	}

	public SimplePager(SortPageDTO sp) {
		super(sp.getRows(), sp.getFirst(), sp.getAsc(), sp.getIdx());
	}

}
