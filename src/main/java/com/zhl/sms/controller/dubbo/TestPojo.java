package com.zhl.sms.controller.dubbo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;


public class TestPojo implements Serializable{
	
	@JsonProperty("username")
	private String name;

	/**
	 * 获取name name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置name name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
