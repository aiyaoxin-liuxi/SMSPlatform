package com.zhl.sms.service;

import com.zhl.sms.pojo.SmsInfo;


public interface ISmsService {
	
	/**
	 * 写入smsinfo
	 * @param smsInfo
	 */
	public void saveSmsInfo(SmsInfo smsInfo);
	
	/**
	 * 根据上游id和手机号查询信息表
	 * @param inSmsNo
	 * @param smsPhone
	 */
	public SmsInfo querySmsInfoByInSmsNoAndPhone(String inSmsNo, String smsPhone);
	
	/**
	 * 写入用户返回信息
	 * @param smsId
	 * @param userReturnContent
	 */
	public void updateSmsUserReturn(String smsId, String userReturnContent);
	

}
