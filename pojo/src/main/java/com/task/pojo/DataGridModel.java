package com.task.pojo;

import java.util.Map;

/**
 * 分页时需要的参数对象
 * 
 * @author zhupeng
 * 
 */
public class DataGridModel {
	/*
	 * 每页显示条数
	 */
	private int rows;

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * 当前第几页
	 */
	private int page;

	/**
	 * 排序列
	 */
	private String sort;

	/**
	 * 排序规则
	 */
	private String order;
	
	/**
	 * 参数列表
	 */
	private Map<String,Object> queryParams;

	public Map<String, Object> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map<String, Object> queryParams) {
		this.queryParams = queryParams;
	}
}
