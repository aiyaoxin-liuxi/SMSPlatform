package com.zhl.sms.gateway;

import java.util.Map;

/**
 * 短信通道
 * 
 * @author xx
 * @version 2.0
 * @since 2014年3月19日
 */
public interface SmsPortal {

	/**
	 * 获取通道名称
	 * @return
	 */
	public String getSPName();
	
	/**
	 * 单条短信发送
	 * @param keySn		订单流水号
	 * @param username	用户名
	 * @param password	密码
	 * @param url		接口地址
	 * @param phone		接收短信手机号
	 * @param content	短信内容
	 * @param type		短信类型
	 * @param notifyUrl	异步返回通知地址
	 * @param userReplyUrl 异步返回营销地址
	 * @return
	 */
	public String send(String keySn, String username, String password, String url, String phone, String content, String sendTo, String type, String notifyUrl, String userReplyUrl);
	
	/**
	 * 获取剩余条数信息
	 * @param keySn
	 * @return
	 */
	public String getUserInfo(String keySn, String username, String password, String url);
	
	/**
	 * 批量发送
	 * 
	 * @param phone
	 * @param content
	 * @return
	 */
	public String batchSend(String keySn, String username, String password, String url, String phone, String content, String sendTo, String type, String notifyUrl, String userReplyUrl);

	/**
	 * 批量个性化短信发送
	 * 
	 * @param phone
	 * @param content
	 * @return
	 */
	public Map<String, Object> batchIdentitySend(String keySn, String username, String password, String url, String phone, String content);
	
}
