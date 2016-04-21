package com.webapp.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginationDTO {

	private int size;
	private int page;
	private long totalRecords;
	private List<MapDTO> filterParams;
	private List<MapDTO> sortingParams;
	
	private Object data;
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<MapDTO> getFilterParams() {
		return filterParams;
	}
	public void setFilterParams(List<MapDTO> filterParams) {
		this.filterParams = filterParams;
	}
	public List<MapDTO> getSortingParams() {
		return sortingParams;
	}
	public void setSortingParams(List<MapDTO> sortingParams) {
		this.sortingParams = sortingParams;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
