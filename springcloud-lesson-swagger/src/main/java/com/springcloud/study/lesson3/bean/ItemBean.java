package com.springcloud.study.lesson3.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "項目信息")
public class ItemBean {
	@ApiModelProperty(value = "項目ID")
	private long id;
	@ApiModelProperty(value = "項目名称")
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
