package com.zhl.sms.gateway;


/**
 * 发送给下游通知接口
 *
 */
public interface NotifyGetwayService {
	
	/**
	 * 发送下游用户回复通知
	 */
	public String msgReply(String keySn, String url, String key, String outSmsNo, String smsId, String mobile, String content);
	

	
}
