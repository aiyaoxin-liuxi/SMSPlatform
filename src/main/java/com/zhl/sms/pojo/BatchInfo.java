package com.zhl.sms.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 当为批量短信时，以组形式存储。在助通文档中号码个数在2-2000之间。
 * 为不影响查询速度，批量查询的手机号单存一表
 * @author 刘熙
 *
 */
public class BatchInfo implements Serializable{
	
	/**
	 * id
	 */
	private String batchId;
	/**
	 * 手机号，逗号分隔
	 */
	private String phone;
	/**
	 * 获取id batchId
	 */
	public String getBatchId() {
		return batchId;
	}
	/**
	 * 设置id batchId
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	/**
	 * 获取手机号，盗号分隔 phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置手机号，盗号分隔 phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	

}
