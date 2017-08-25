/**
 * 
 */
package com.zhl.sms.pojo.enums;


/**
 * 短信类型
 */
public enum SmsType {

	/** 验证码类型短信 */
	CODE01("01","验证码类型短信"),
	/** 通知类型短信 */
	CODE02("02","通知类型短信"),
	/** 营销类型短信 */
	CODE03("03","营销类型短信"),
	/** 批量通知类型短信 */
	CODE04("04","批量通知类型短信"),
	/** 批量营销类型短信 */
	CODE05("05","批量营销类型短信");
	
	
	
	private String code;
	
	private String cnName;

	/**
	 * @param code
	 * @param cnName
	 */
	private SmsType(String code, String cnName) {
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
		for (SmsType smsType : SmsType.values()) {
			if (code.equals(smsType.getCode())) {
				return smsType.cnName;
			}
		}
		return null;
	}
	
	public static String getCode(String Text) {
		for (SmsType smsType : SmsType.values()) {
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
