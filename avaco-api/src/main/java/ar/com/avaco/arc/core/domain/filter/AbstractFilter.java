package ar.com.avaco.arc.core.domain.filter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFilter {

	protected List<List<FilterData>> orFilterData = new ArrayList<List<FilterData>>();

	protected List<FilterData> filterData = new ArrayList<FilterData>();

	protected Integer rows;

	protected Integer first;

	protected Boolean asc;

	protected String idx;

	protected Boolean distinctRootEntity;

	public AbstractFilter(Integer rows, Integer first, Boolean asc, String idx) {
		super();
		this.rows = rows;
		this.first = first;
		this.asc = asc;
		this.idx = idx;
	}

	public AbstractFilter() {
	}

	public List<FilterData> getFilterDatas() {
		return filterData;
	}

	public List<List<FilterData>> getOrFilterDatas() {
		return orFilterData;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getFirst() {
		return first;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}

	public Boolean isAsc() {
		return asc;
	}

	public String getSidx() {
		return idx;
	}

	public Boolean getAsc() {
		return asc;
	}

	public void setAsc(Boolean asc) {
		this.asc = asc;
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public Boolean getDistinctRootEntity() {
		return distinctRootEntity;
	}

	public void setDistinctRootEntity(Boolean distinctRootEntity) {
		this.distinctRootEntity = distinctRootEntity;
	}

}