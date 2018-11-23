package com.xiaoshu.backendframework.model;

public class Dict extends BaseEntity<Long> {

	private static final long serialVersionUID = 1356894294659068449L;

	private String type;
	private String k;
	private String val;

	public Dict() {
	}

	public Dict(String type, String k) {
		this.type = type;
		this.k = k;
	}

	public Dict(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

}
