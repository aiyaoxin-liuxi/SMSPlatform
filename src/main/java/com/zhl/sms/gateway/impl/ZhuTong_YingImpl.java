package com.zhl.sms.gateway.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.zhl.sms.gateway.SmsPortal;
import com.zhl.sms.util.CheckUtil;
import com.zhl.sms.util.DateConverter;
import com.zhl.sms.util.SmsHttpUtil;

/**
 * 助通短信——营销类
 * 
 * @version 1.0
 * @since 2015-8-28
 */
public class ZhuTong_YingImpl implements SmsPortal{
	
	private Logger logger = Logger.getLogger(ZhuTong_YingImpl.class);

	@Override
	public String getSPName() {
		return "ZhuTong_Ying";
	}
	
	@Override
	public String send(String keySn, String username, String password, String url, String phone, String content, String sendTo, String type, String notifyUrl, String userReplyUrl) {
		String service = "sendSms.do";
		Map<String, Object> map = new HashMap<String, Object>();
		String tkey = DateConverter.getDateToStr();
		password = DigestUtils.md5Hex(DigestUtils.md5Hex(password).toLowerCase() + tkey).toLowerCase();
		
		// 助通需要增加公司签名才可发送成功
//		StringBuilder sb = new StringBuilder().append("【中互联】").append(content);
		content = CheckUtil.checkContentTag(content);
		
		map.put("username", username);
		map.put("tkey", tkey);
		map.put("password", password);
		map.put("mobile", phone);
		map.put("content", content);
		logger.info("请求：【助通_营销】发送短信(" + service + ")接口【服务编号】:"+keySn+"【请求参数】：" + map.toString());
		long startTime = System.currentTimeMillis();
		String s = SmsHttpUtil.httpsPost(url + service, "", map);
		logger.info("耗时：【助通_营销】(" + service + ")接口【服务编号】:"+keySn+"耗时:"+(System.currentTimeMillis()-startTime)+"毫秒");
		if (s.contains("1,")) {
			logger.info("响应：【助通_营销】(" + service + ")接口【服务编号】:"+keySn+"【返回参数】：" + s + ",发送短信成功");
		} else {
			logger.info("响应：【助通_营销】(" + service + ")接口【服务编号】:"+keySn+"【返回参数】：" + s);
		}
		return s;
	}
	
	@Override
	public String getUserInfo(String keySn, String username, String password, String url) {
		String service = "balance.do";
		Map<String, Object> map = new HashMap<String, Object>();
		String tkey = DateConverter.getDateToStr();
		password = DigestUtils.md5Hex(DigestUtils.md5Hex(password) + tkey);
		map.put("username", username);
		map.put("password", password);
		map.put("tkey", tkey);
		
		logger.info("请求：【助通_营销】余额查询(" + service + ")接口【服务编号】:"+keySn+"【请求参数】：" + map.toString());
		long startTime = System.currentTimeMillis();
		String result = SmsHttpUtil.httpsPost(url + service, "", map);
		logger.info("耗时：【助通_营销】(" + service + ")接口【服务编号】:"+keySn+"耗时:"+(System.currentTimeMillis()-startTime)+"毫秒");
		logger.info("响应：【助通_营销】(" + service + ")接口【服务编号】:"+keySn+"【返回参数】：" + result);
		
		return result;
	}
	
	@Override
	public String batchSend(String keySn, String username, String password, String url, String phone, String content, String sendTo, String type, String notifyUrl, String userReplyUrl) {
		String service = "sendSmsBatch.do";
		Map<String, Object> map = new HashMap<String, Object>();
		String tkey = DateConverter.getDateToStr();
		password = DigestUtils.md5Hex(DigestUtils.md5Hex(password).toLowerCase() + tkey).toLowerCase();
		
		map.put("username", username);
		map.put("tkey", tkey);
		map.put("password", password);
		map.put("mobile", phone);
		map.put("content", content);
		logger.info("请求：【助通_营销】批量发送短信(" + service + ")接口【服务编号】:"+keySn+"【请求参数】：" + map.toString());
		long startTime = System.currentTimeMillis();
		String s = SmsHttpUtil.httpsPost(url + service, "", map);
		logger.info("耗时：【助通_营销】(" + service + ")接口【服务编号】:"+keySn+"耗时:"+(System.currentTimeMillis()-startTime)+"毫秒");
		String result = "ok";
		if (s.contains("1,")) {
			logger.info("响应：【助通_营销】(" + service + ")接口【服务编号】:"+keySn+"【返回参数】：" + s + ",发送短信成功");
		} else {
			logger.info("响应：【助通_营销】(" + service + ")接口【服务编号】:"+keySn+"【返回参数】：" + s);
		}
		return s;
	}
	
	@Override
	public Map<String, Object> batchIdentitySend(String keySn, String username, String password, String url, String phone, String content) {
		String service = "sendSmsBatchIdentity.do";
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		String tkey = DateConverter.getDateToStr();
		password = DigestUtils.md5Hex(DigestUtils.md5Hex(password).toLowerCase() + tkey).toLowerCase();
		
		map.put("username", username);
		map.put("tkey", tkey);
		map.put("password", password);
		map.put("mobile", phone);
		map.put("content", content);
		logger.info("请求：【助通_营销】批量个性化短信(" + service + ")接口【服务编号】:"+keySn+"【请求参数】：" + map.toString());
		long startTime = System.currentTimeMillis();
		String s = SmsHttpUtil.httpsPost(url + service, "", map);
		logger.info("耗时：【助通_营销】(" + service + ")接口【服务编号】:"+keySn+"耗时:"+(System.currentTimeMillis()-startTime)+"毫秒");
		String result = "ok";
		if (s.contains("1,")) {
			logger.info("响应：【助通_营销】(" + service + ")接口【服务编号】:"+keySn+"【返回参数】：" + s + ",发送短信成功");
		} else {
			logger.info("响应：【助通_营销】(" + service + ")接口【服务编号】:"+keySn+"【返回参数】：" + s);
		}
		returnMap.put("result", s);
		return returnMap;
	}


	
}
