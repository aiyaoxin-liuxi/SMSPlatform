package com.zhl.sms.gateway.ret;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.zhl.sms.gateway.RetAnalysis;
import com.zhl.sms.pojo.enums.ReturnCode;

/**
 * 易信通短信返回值解析
 * @author 刘熙
 *
 */
public class RetYiXinTong implements RetAnalysis  {
	
	private Logger logger = Logger.getLogger(RetYiXinTong.class);
	
	@Override
	public Map<String,Object> send(String smsId, String outSmsNo, String result){
		logger.info("开始解析：【易信通】服务，【服务编号】:"+smsId+"【返回参数】：" + result);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		JSONObject resJson = JSONObject.fromObject(result);
		if(resJson.has("result")){
			JSONObject resultJson = resJson.getJSONObject("result");
			if(resultJson.getBoolean("result")){
				
				returnMap.put("inSmsNo", resultJson.getString("others"));
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE111111.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE111111.getCnName());
			} else {
				returnMap.put("inSmsNo", resultJson.getString("others"));
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410032.getCode());
				returnMap.put("returnMsg", resultJson.getString("msg"));
			}
		} else if(resJson.has("error")){
			
			JSONObject resultJson = resJson.getJSONObject("error");
			if(resultJson.getBoolean("code")){
				
				returnMap.put("inSmsNo", resultJson.getString("others"));
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", resultJson.getString("code"));
				returnMap.put("returnMsg", resultJson.getString("msg"));
			} else {
				returnMap.put("inSmsNo", resultJson.getString("others"));
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410032.getCode());
				returnMap.put("returnMsg", resultJson.getString("msg"));
			}
			
		}
		logger.info("解析完成：【易信通】服务，【服务编号】:"+smsId+"【返回参数】：" + returnMap.toString());
		return returnMap;
	}

	@Override
	public Map<String, Object> batchSend(String smsId, String outSmsNo, String result) {
		return null;
	}

}
