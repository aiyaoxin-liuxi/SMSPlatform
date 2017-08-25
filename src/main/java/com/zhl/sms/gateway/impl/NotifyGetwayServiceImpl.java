package com.zhl.sms.gateway.impl;

import java.util.HashMap;
import java.util.Map;

import org.pub.util.security.MessageSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zhl.sms.gateway.NotifyGetwayService;
import com.zhl.sms.util.JsonUtil;
import com.zhl.sms.util.Util;


@Service("notifyGetwayService")
public class NotifyGetwayServiceImpl implements NotifyGetwayService {
	
	private Logger logger = LoggerFactory.getLogger(NotifyGetwayServiceImpl.class);

	@Override
	public String msgReply(String keySn, String url, String key, String outSmsNo, String smsId, String mobile, String content) {
//		String service = "msgReply";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("outSmsNo", outSmsNo);
		map.put("smsId", smsId);
		map.put("mobile", mobile);
		map.put("content", content);
//		map = Util.sortMapByKey(map);// 排序
		String json = JsonUtil.getMapToJson(map);
//		String sign = MessageSecurity.getMessageSecurity("", json, key);
//		map.put("sign", sign);
		
		logger.info("发送给下游通知：用户回复接口【服务编号】:"+keySn+"【请求参数】：" + map.toString());
		long startTime = System.currentTimeMillis();
		try {
//			json = SmsHttpUtil.httpsPost(url, "", map);
		} catch (Exception e)  {
			e.printStackTrace();
		}
		logger.info("耗时：用户回复接口"+(System.currentTimeMillis()-startTime)+"毫秒");
		logger.info("响应：用户回复接口【服务编号】:"+keySn+"【返回参数】：" + json);
		logger.info("响应：用户回复接口【服务编号】:"+keySn);
		return "ok";
	}
	
}
