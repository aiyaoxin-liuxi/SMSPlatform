package com.zhl.sms.pojo;

import java.io.Serializable;
import java.util.Date;

public class SmsInfo implements Serializable{
	
	/**
	 * 短信id
	 */
	private String smsId;
	/**
	 * 下游消息编号
	 */
	private String outSmsNo;
	/**
	 * 上游消息编号
	 */
	private String inSmsNo;
	/**
	 * 应用id
	 */
	private String applyId;
	/**
	 * 发送短信使用的通道
	 */
	private String channelId;
	/**
	 * 短信类型
	 */
	private String smsType;
	/**
	 * 发送人
	 */
	private String smsPhone;
	/**
	 * 短信内容
	 */
	private String smsContent;
	/**
	 * 上游返回值
	 */
	private String smsReturn;
	/**
	 * 用户返回内容
	 */
	private String userReturnContent;
	/**
	 * 创建时间
	 */
	private Date createdDate;
	/**
	 * 返回码
	 */
	private String returnCode;
	/**
	 * 返回描述
	 */
	private String returnMsg;
	/**
	 * 获取短信id smsId
	 */
	public String getSmsId() {
		return smsId;
	}
	/**
	 * 设置短信id smsId
	 */
	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}
	/**
	 * 获取应用id applyId
	 */
	public String getApplyId() {
		return applyId;
	}
	/**
	 * 设置应用id applyId
	 */
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	/**
	 * 获取发送短信使用的通道 channelId
	 */
	public String getChannelId() {
		return channelId;
	}
	/**
	 * 设置发送短信使用的通道 channelId
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	/**
	 * 获取短信类型 smsType
	 */
	public String getSmsType() {
		return smsType;
	}
	/**
	 * 设置短信类型 smsType
	 */
	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}
	/**
	 * 获取发送人 smsPhone
	 */
	public String getSmsPhone() {
		return smsPhone;
	}
	/**
	 * 设置发送人 smsPhone
	 */
	public void setSmsPhone(String smsPhone) {
		this.smsPhone = smsPhone;
	}
	/**
	 * 获取短信内容 smsContent
	 */
	public String getSmsContent() {
		return smsContent;
	}
	/**
	 * 设置短信内容 smsContent
	 */
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	/**
	 * 获取下游消息编号 outSmsNo
	 */
	public String getOutSmsNo() {
		return outSmsNo;
	}
	/**
	 * 设置下游消息编号 outSmsNo
	 */
	public void setOutSmsNo(String outSmsNo) {
		this.outSmsNo = outSmsNo;
	}
	/**
	 * 获取上游消息编号 inSmsNo
	 */
	public String getInSmsNo() {
		return inSmsNo;
	}
	/**
	 * 设置上游消息编号 inSmsNo
	 */
	public void setInSmsNo(String inSmsNo) {
		this.inSmsNo = inSmsNo;
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
	/**
	 * 获取上游返回值 smsReturn
	 */
	public String getSmsReturn() {
		return smsReturn;
	}
	/**
	 * 设置上游返回值 smsReturn
	 */
	public void setSmsReturn(String smsReturn) {
		this.smsReturn = smsReturn;
	}
	/**
	 * 获取用户返回内容 userReturnContent
	 */
	public String getUserReturnContent() {
		return userReturnContent;
	}
	/**
	 * 设置用户返回内容 userReturnContent
	 */
	public void setUserReturnContent(String userReturnContent) {
		this.userReturnContent = userReturnContent;
	}
	/**
	 * 获取返回码 returnCode
	 */
	public String getReturnCode() {
		return returnCode;
	}
	/**
	 * 设置返回码 returnCode
	 */
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	/**
	 * 获取返回描述 returnMsg
	 */
	public String getReturnMsg() {
		return returnMsg;
	}
	/**
	 * 设置返回描述 returnMsg
	 */
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	
	
}
