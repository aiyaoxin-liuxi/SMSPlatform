package com.zhl.sms.pojo;

import java.io.Serializable;
import java.util.Date;

public class ApplyInfo implements Serializable{
	
	/**
	 * 商户id
	 */
	private String applyId;
	/**
	 * 商户中文名称
	 */
	private String applyChName;
	/**
	 * 商户key
	 */
	private String applyKey;
	/**
	 * 商户可发送权限
	 */
	private String applyPower;
	/**
	 * 验证码类型优先级
	 */
	private String smsCode;
	/**
	 * 通知类型优先级
	 */
	private String smsNotify;
	/**
	 * 营销类型优先级
	 */
	private String smsMarket;
	/**
	 * 批量通知类型优先级
	 */
	private String batchSmsNotify;
	/**
	 * 批量营销类型优先级
	 */
	private String batchSmsMarket;
	/**
	 * 商户类型
	 */
	private String type;
	/**
	 * 商户状态
	 */
	private String state;
	/**
	 * 通知地址(用户短信发送是否成功)
	 */
	private String notifyUrl;
	/**
	 * 用户短信回复地址
	 */
	private String userReplyUrl;
	/**
	 * 创建时间
	 */
	private Date createdDate;
	/**
	 * 获取商户id applyId
	 */
	public String getApplyId() {
		return applyId;
	}
	/**
	 * 设置商户id applyId
	 */
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	/**
	 * 获取商户中文名称 applyChName
	 */
	public String getApplyChName() {
		return applyChName;
	}
	/**
	 * 设置商户中文名称 applyChName
	 */
	public void setApplyChName(String applyChName) {
		this.applyChName = applyChName;
	}
	/**
	 * 获取商户key applyKey
	 */
	public String getApplyKey() {
		return applyKey;
	}
	/**
	 * 设置商户key applyKey
	 */
	public void setApplyKey(String applyKey) {
		this.applyKey = applyKey;
	}
	/**
	 * 获取商户可发送权限 applyPower
	 */
	public String getApplyPower() {
		return applyPower;
	}
	/**
	 * 设置商户可发送权限 applyPower
	 */
	public void setApplyPower(String applyPower) {
		this.applyPower = applyPower;
	}
	/**
	 * 获取验证码类型优先级 smsCode
	 */
	public String getSmsCode() {
		return smsCode;
	}
	/**
	 * 设置验证码类型优先级 smsCode
	 */
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	/**
	 * 获取通知类型优先级 smsNotify
	 */
	public String getSmsNotify() {
		return smsNotify;
	}
	/**
	 * 设置通知类型优先级 smsNotify
	 */
	public void setSmsNotify(String smsNotify) {
		this.smsNotify = smsNotify;
	}
	/**
	 * 获取营销类型优先级 smsMarket
	 */
	public String getSmsMarket() {
		return smsMarket;
	}
	/**
	 * 设置营销类型优先级 smsMarket
	 */
	public void setSmsMarket(String smsMarket) {
		this.smsMarket = smsMarket;
	}
	/**
	 * 获取批量通知类型优先级 batchSmsNotify
	 */
	public String getBatchSmsNotify() {
		return batchSmsNotify;
	}
	/**
	 * 设置批量通知类型优先级 batchSmsNotify
	 */
	public void setBatchSmsNotify(String batchSmsNotify) {
		this.batchSmsNotify = batchSmsNotify;
	}
	/**
	 * 获取批量营销类型优先级 batchSmsMarket
	 */
	public String getBatchSmsMarket() {
		return batchSmsMarket;
	}
	/**
	 * 设置批量营销类型优先级 batchSmsMarket
	 */
	public void setBatchSmsMarket(String batchSmsMarket) {
		this.batchSmsMarket = batchSmsMarket;
	}
	/**
	 * 获取商户类型 type
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置商户类型 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取商户状态 state
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置商户状态 state
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取通知地址(用户短信发送是否成功) notifyUrl
	 */
	public String getNotifyUrl() {
		return notifyUrl;
	}
	/**
	 * 设置通知地址(用户短信发送是否成功) notifyUrl
	 */
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	/**
	 * 获取用户短信回复地址 userReplyUrl
	 */
	public String getUserReplyUrl() {
		return userReplyUrl;
	}
	/**
	 * 设置用户短信回复地址 userReplyUrl
	 */
	public void setUserReplyUrl(String userReplyUrl) {
		this.userReplyUrl = userReplyUrl;
	}
	/**
	 * 获取创建时间 createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * 设置创建时间 createdDate
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
