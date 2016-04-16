package com.webapp.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginationDTO {

	private int size;
	private int page;
	private long totalRecords;
	private Map<String, String> filterParams;
	private Map<String, String> sortingParams;
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
	public Map<String, String> getFilterParams() {
		return filterParams;
	}
	public void setFilterParams(Map<String, String> filterParams) {
		this.filterParams = filterParams;
	}
	public Map<String, String> getSortingParams() {
		return sortingParams;
	}
	public void setSortingParams(Map<String, String> sortingParams) {
		this.sortingParams = sortingParams;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
