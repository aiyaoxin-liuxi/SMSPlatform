package com.zhl.sms.gateway;

import java.util.Map;

/**
 *  用于返回解析专用类
 */
public interface RetAnalysis {
	
	public Map<String,Object> send(String smsId, String outSmsNo, String result);

	public Map<String,Object> batchSend(String smsId, String outSmsNo, String result);
}
