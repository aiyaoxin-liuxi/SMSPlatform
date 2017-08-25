package com.zhl.sms.util;

import java.io.ByteArrayOutputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * 易信通demo
 * @author 刘熙
 *
 */
public class SendSms_demo {

	/**
	 * 直接组装json串发送短信
	 */
	public static void main(String[] args) {
		String serviceUrl = "http://yxt.bbn.com.cn/eums/rpc/";
		String account = "test123";	//账号名
		String passwd = "888888";	//密码
		
		try {
			int reqId = 1;
			//获取登录令牌
			JSONObject reqJson = new JSONObject();
			reqJson.put("id", reqId++);
			reqJson.put("jsonrpc", "2.0");
			reqJson.put("method", "genLoginToken");
			JSONArray params = new JSONArray();
			params.add(account);
			params.add(passwd);
			reqJson.put("params", params);
			
			String resStr = postJson(serviceUrl + "power/authService", reqJson.toString());
			System.out.println(resStr);
			JSONObject resJson = JSONObject.fromObject(resStr);
			if(resJson.has("result")){
				JSONObject resultJson = resJson.getJSONObject("result");
				if(resultJson.getBoolean("result")){
					//令牌
					String loginToken = resultJson.getString("others");
					//发送短信
					reqJson = new JSONObject();
					reqJson.put("id", reqId++);
					reqJson.put("jsonrpc", "2.0");
					reqJson.put("method", "save");
					
					JSONObject smsJson = new JSONObject();
					smsJson.put("businessType", "短信发送");		//业务类型，用于统计
					smsJson.put("sendText", "1314 微信验证码（6位数字，10分钟内有效）");		//短信内容
					smsJson.put("sendTo", "api测试");		//接收人姓名，可以多个，用半角逗号分隔
					if(args != null && args.length > 0)smsJson.put("toDetail", args[0]);		//接收号码，可以多个，用半角逗号分隔
					else smsJson.put("toDetail", "13810193325");
					smsJson.put("sendFrom", "api");
					
					params = new JSONArray();
					params.add(smsJson);
					reqJson.put("params", params);
					
					JSONObject authJson = new JSONObject();
					authJson.put("loginToken", loginToken);
					reqJson.put("auth", authJson);
					
					resStr = postJson(serviceUrl + "smsSave", reqJson.toString());
					System.out.println(resStr);
					resJson = JSONObject.fromObject(resStr);
					resultJson = resJson.getJSONObject("result");
					if(resultJson.getBoolean("result")){
						System.out.println("send success");
					}
					else {
						System.out.println("send fail: " + resultJson.getString("msg"));
					}
				}
				else {
					System.out.println("get loginToken fail:" + resultJson.getString("msg"));
				}
			}
			else {
				System.out.println("get loginToken fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("send fail");
		}	
	}
	
	public static String postJson(String url, String jsonStr) throws Exception{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		StringEntity entity = new StringEntity(jsonStr, "utf-8");
		post.setEntity(entity);
		HttpResponse response = client.execute(post);
		int statusCode = response.getStatusLine().getStatusCode();
		HttpEntity  httpEntity = response.getEntity();
		//if (httpEntity != null) {
		//post.abort();
	  //}
		if(statusCode == 200){
			ByteArrayOutputStream resHolder = new ByteArrayOutputStream();
			httpEntity.writeTo(resHolder);
			resHolder.flush();
	    	return resHolder.toString("utf-8");
		}
		else {
			throw new Exception("服务异常");
		}
	}

}
