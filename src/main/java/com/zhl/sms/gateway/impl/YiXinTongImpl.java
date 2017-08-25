package com.zhl.sms.gateway.impl;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.zhl.sms.gateway.SmsPortal;
import com.zhl.sms.util.CheckUtil;
import com.zhl.sms.util.SmsHttpUtil;
/**
 * 易信通短信
 * 
 * @version 1.0
 * @since 2015-8-28
 */
public class YiXinTongImpl implements SmsPortal{
	
	private Logger logger = Logger.getLogger(YiXinTongImpl.class);

	@Override
	public String getSPName() {
		return "YiXinTong";
	}
	
	@Override
	public String getUserInfo(String keySn, String username, String password, String url) {
		return null;
	}

	@Override
	public String batchSend(String keySn, String username, String password, String url, String phone, String conten, String sendTo, String type, String notifyUrl, String userReplyUrl) {
		return null;
	}

	@Override
	public Map<String, Object> batchIdentitySend(String keySn, String username, String password, String url, String phone, String conten) {
		return null;
	}
	
	/**
	 * 返回地址、接收人姓名、业务类型
	 */
	@Override
	public String send(String keySn, String username, String password, String url, String phone, String content, String sendTo, String type, String notifyUrl, String userReplyUrl) {
		
		String resStr = "";
		int reqId = 1;
		// 认证接口
		resStr = authentication(keySn, username, password, url, reqId++);
		if(!"".equals(resStr)){
			JSONObject resJson = JSONObject.fromObject(resStr);
			if(resJson.has("result")){
				JSONObject resultJson = resJson.getJSONObject("result");
				if(resultJson.getBoolean("result")){
					//令牌
					String loginToken = resultJson.getString("others");
					resStr = send(keySn, loginToken, notifyUrl, url, sendTo, phone, content, type, reqId++);
				}
			}
		}
		return resStr;
	}
	
	
	/**
	 * 认证
	 * @param keySn
	 * @param username
	 * @param password
	 * @param url
	 * @param reqId
	 * @return
	 */
	public String authentication(String keySn, String username, String password, String url, int reqId) {
		String service = "power/authService";
		
		//获取登录令牌
		JSONObject reqJson = new JSONObject();
		reqJson.put("id", reqId);
		reqJson.put("jsonrpc", "2.0");
		reqJson.put("method", "genLoginToken");
		JSONArray params = new JSONArray();
		params.add(username);
		params.add(password);
		reqJson.put("params", params);
		
		logger.info("请求：【易信通_短信】认证开始(" + service + ")接口【服务编号】:"+keySn+"【请求参数】：" + reqJson);
		long startTime = System.currentTimeMillis();
//		String s = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"result\":true,\"msg\":null,\"others\":\"access-9c049173fad5f4f89c68231237df85b84f05f6d5ed980a9061ac20418ed8db1d\"}}";
//		String s = SmsHttpUtil.httpsPost(url + service, "", reqJson);
		String s = "";
		try {
			s = SmsHttpUtil.postJson(url + service, reqJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("耗时：【易信通_短信】认证完成(" + service + ")接口【服务编号】:"+keySn+"耗时:"+(System.currentTimeMillis()-startTime)+"毫秒");
		logger.info("响应：【易信通_短信】(" + service + ")接口【服务编号】:"+keySn+"【返回参数】：" + s);
		return s;
	}
	
	
	/**
	 * 发送短信
	 * @param keySn
	 * @param loginToken
	 * @param callBack
	 * @param url
	 * @param sendTo
	 * @param phone
	 * @param content
	 * @param businessType
	 * @param reqId
	 * @return
	 */
	public String send(String keySn, String loginToken, String callBack, String url, String sendTo, String phone, String content, String businessType, int reqId) {
		String service = "smsSave";
		
		//发送短信
		JSONObject reqJson = new JSONObject();
		reqJson.put("id", reqId);
		reqJson.put("jsonrpc", "2.0");
		reqJson.put("method", "save");
		
		JSONObject smsJson = new JSONObject();
		smsJson.put("businessType", businessType);		//业务类型，用于统计
		
//		StringBuilder sb = new StringBuilder().append("【中互联】").append(content);
		content = CheckUtil.checkContentTag(content);
		
		smsJson.put("sendText", content);		//短信内容
		smsJson.put("sendTo", sendTo);		//接收人姓名，可以多个，用半角逗号分隔
		smsJson.put("toDetail", phone);//接收号码，可以多个，用半角逗号分隔
		smsJson.put("sendFrom", "zhl");
		smsJson.put("callBack", callBack);
		
		JSONArray params = new JSONArray();
		params.add(smsJson);
		reqJson.put("params", params);
		
		JSONObject authJson = new JSONObject();
		authJson.put("loginToken", loginToken);
		reqJson.put("auth", authJson);
		
		logger.info("请求：【易信通_短信】发送短信(" + service + ")接口【服务编号】:"+keySn+"【请求参数】：" + reqJson);
		long startTime = System.currentTimeMillis();
//		String s = "{\"id\":2,\"jsonrpc\":\"2.0\",\"result\":{\"result\":true,\"msg\":\"\",\"others\":79643865}}";
//		String s = SmsHttpUtil.httpsPost(url + service, "", map);
		String s = "";
		try {
			s = SmsHttpUtil.postJson(url + service, reqJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("耗时：【易信通_短信】(" + service + ")接口【服务编号】:"+keySn+"耗时:"+(System.currentTimeMillis()-startTime)+"毫秒");
		logger.info("响应：【易信通_短信】(" + service + ")接口【服务编号】:"+keySn+"【返回参数】：" + s);
		return s;
	}
	

}
