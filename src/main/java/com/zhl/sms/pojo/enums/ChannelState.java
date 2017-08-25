/**
 * 
 */
package com.zhl.sms.pojo.enums;


/**
 * 通道状态
 */
public enum ChannelState {

	/** 启用 */
	CODE00("00","启用"),
	/** 禁用 */
	CODE01("01","禁用");
	
	
	
	private String code;
	
	private String cnName;

	/**
	 * @param code
	 * @param cnName
	 */
	private ChannelState(String code, String cnName) {
		this.code = code;
		this.cnName = cnName;
	}

	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getCnName() {
		return cnName;
	}


	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	
	public static String getText(String code) {
		for (ChannelState smsType : ChannelState.values()) {
			if (code.equals(smsType.getCode())) {
				return smsType.cnName;
			}
		}
		return null;
	}
	
	public static String getCode(String Text) {
		for (ChannelState smsType : ChannelState.values()) {
			if (Text.equals(smsType.getCnName())) {
				return smsType.code;
			}
		}
		return null;
	}

	public String getText() {
		return cnName;
	}
}
