package com.zhl.sms.pojo;

import java.io.Serializable;
import java.util.Date;

public class ChannelInfo implements Serializable{
	
	/**
	 * 通道id
	 */
	private String channelId;
	/**
	 * 通道名称
	 */
	private String channelName;
	/**
	 * 通道中文名称
	 */
	private String channelChName;
	/**
	 * 通道连接用户名
	 */
	private String userName;
	/**
	 * 通道连接密码
	 */
	private String passWord;
	/**
	 * 通道连接地址
	 */
	private String channelUrl;
	/**
	 * 通道类型
	 */
	private String type;
	/**
	 * 通道状态
	 */
	private String state;
	/**
	 * 通道返回中互联的通知地址
	 */
	private String notifyUrl;
	/**
	 * 通道返回中互联用户短信回复地址
	 */
	private String userReplyUrl;
	/**
	 * 创建时间
	 */
	private Date createdDate;
	/**
	 * 获取通道id channelId
	 */
	public String getChannelId() {
		return channelId;
	}
	/**
	 * 设置通道id channelId
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	/**
	 * 获取通道名称 channelName
	 */
	public String getChannelName() {
		return channelName;
	}
	/**
	 * 设置通道名称 channelName
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	/**
	 * 获取通道中文名称 channelChName
	 */
	public String getChannelChName() {
		return channelChName;
	}
	/**
	 * 设置通道中文名称 channelChName
	 */
	public void setChannelChName(String channelChName) {
		this.channelChName = channelChName;
	}
	/**
	 * 获取通道连接用户名 userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置通道连接用户名 userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取通道连接密码 passWord
	 */
	public String getPassWord() {
		return passWord;
	}
	/**
	 * 设置通道连接密码 passWord
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	/**
	 * 获取通道连接地址 channelUrl
	 */
	public String getChannelUrl() {
		return channelUrl;
	}
	/**
	 * 设置通道连接地址 channelUrl
	 */
	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}
	/**
	 * 获取通道类型 type
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置通道类型 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取通道状态 state
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置通道状态 state
	 */
	public void setState(String state) {
		this.state = state;
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
	 * 获取通道返回中互联的通知地址 notifyUrl
	 */
	public String getNotifyUrl() {
		return notifyUrl;
	}
	/**
	 * 设置通道返回中互联的通知地址 notifyUrl
	 */
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	/**
	 * 获取通道返回中互联用户短信回复地址 userReplyUrl
	 */
	public String getUserReplyUrl() {
		return userReplyUrl;
	}
	/**
	 * 设置通道返回中互联用户短信回复地址 userReplyUrl
	 */
	public void setUserReplyUrl(String userReplyUrl) {
		this.userReplyUrl = userReplyUrl;
	}

}
