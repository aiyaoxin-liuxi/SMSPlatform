package com.zhl.sms.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.pub.util.json.JsonUtil;
import org.pub.util.security.MessageSecurity;
import org.pub.util.uuid.KeySn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhl.sms.pojo.ApplyInfo;
import com.zhl.sms.pojo.ChannelInfo;
import com.zhl.sms.pojo.SmsInfo;
import com.zhl.sms.pojo.enums.ChannelState;
import com.zhl.sms.pojo.enums.ReturnCode;
import com.zhl.sms.pojo.enums.SmsType;
import com.zhl.sms.service.IApplyService;
import com.zhl.sms.service.IChannelService;
import com.zhl.sms.service.ISmsService;
import com.zhl.sms.util.CheckUtil;
import com.zhl.sms.util.Util;

/**
 * 此类包含发送单条短信，和查询发送条数信息两个基础通用接口
 * @author 刘熙
 */
@Controller
@RequestMapping(value = "/sms")
public class SmsController {
	
	private Logger logger = Logger.getLogger(SmsController.class);
	
	@Resource(name = "applyService")
	private IApplyService applyService;
	@Resource(name = "channelService")
	private IChannelService channelService;
	@Resource(name = "smsService")
	private ISmsService smsService;
	
	@RequestMapping(value = "/getUserInfo")
	@ResponseBody
	public Object getUserInfo(@RequestBody String jsonParam){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String keySn = KeySn.getKey();
		
		try {
			jsonParam = URLDecoder.decode(jsonParam, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = JsonUtil.getJsonToMap(jsonParam);
		String check = checkGetUserInfo(map);
		if(null == check){
			String applyNo = (String) map.get("applyNo");
			String type = (String) map.get("type");
			
			ApplyInfo applyInfo = applyService.queryByApplyId(applyNo);
			String key = applyInfo.getApplyKey();
			if(signCheck(map, key)){
				// 检查应用权限
				String power = applyInfo.getApplyPower();
				if(power.contains(type)){
					String sms = "";
					if(SmsType.CODE01.getCode().equals(type)){
						// 验证码类型短信
						sms = applyInfo.getSmsCode(); // 验证码类优先级列表
					} else if(SmsType.CODE02.getCode().equals(type)){
						// 通知类型短信
						sms = applyInfo.getSmsNotify(); // 通知类优先级列表
					} else if(SmsType.CODE03.getCode().equals(type)){
						// 营销类型短信
						sms = applyInfo.getSmsMarket(); // 营销类优先级列表
					} else {
						// 未传类型，使用默认？还是返回错误？
						
					}
					try {
						keySn = applyInfo.getApplyKey() + keySn;
						returnMap = getUserInfoChannel(keySn, sms, "getUserInfo");
					} catch (Exception e) {
						logger.info(e.getMessage());
						returnMap.put("keySn", keySn);
						returnMap.put("returnCode", ReturnCode.CODE999999.getCode());
						returnMap.put("returnName", ReturnCode.CODE999999.getCnName());
					}
				} else {
					// 该商户没有此短信权限
					returnMap.put("keySn", keySn);
					returnMap.put("returnCode", ReturnCode.CODE200004.getCode());
					returnMap.put("returnName", ReturnCode.CODE200004.getCnName());
				}
			} else {
				// 应用签名不正确 
				returnMap.put("smsNo", keySn);
				returnMap.put("returnCode", ReturnCode.CODE200009.getCode());
				returnMap.put("returnName", ReturnCode.CODE200009.getCnName());
			}
		} else {
			// 参数不全
			returnMap.put("keySn", keySn);
			returnMap.put("returnCode", ReturnCode.CODE200002.getCode());
			returnMap.put("returnName", ReturnCode.CODE200002.getCnName() + check);
		}
		
		return returnMap;
	}
	
	@RequestMapping(value = "/sendSms")
	@ResponseBody
	public Object sendSms(@RequestBody String jsonParam) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String keySn = KeySn.getKey();
		
		try {
			jsonParam = URLDecoder.decode(jsonParam, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = JsonUtil.getJsonToMap(jsonParam);
		String check = checkSendSms(map);
		if(null == check){
			String applyNo = (String) map.get("applyNo");	// 应用号
			String outSmsNo = (String) map.get("outSmsNo");		// 下游消息编号
			String mobile = (String) map.get("mobile");			// 手机号
			String content = (String) map.get("content");		// 内容
			String type = (String)map.get("type");				// 类型
			String sign = (String)map.get("sign");				// 签名
			String sendTo = mobile;// 接收人
//			if(map.containsKey("sendTo") && !"".equals((String)map.get("sendTo"))){
//				sendTo = (String)map.get("sendTo");
//			} else {
//				sendTo = mobile;
//			}
			// 获取商户号
			ApplyInfo applyInfo = applyService.queryByApplyId(applyNo);
			String key = applyInfo.getApplyKey();
			if(signCheck(map, key)){
				// 检查商户权限
				String power = applyInfo.getApplyPower(); // 应用短信权限
				if(power.contains(type)){
					String sms = "";
					if(SmsType.CODE01.getCode().equals(type)){
						// 验证码类型短信
						sms = applyInfo.getSmsCode(); // 验证码类优先级列表
					} else if(SmsType.CODE02.getCode().equals(type)){
						// 通知类型短信
						sms = applyInfo.getSmsNotify(); // 通知类优先级列表
					} else if(SmsType.CODE03.getCode().equals(type)){
						// 营销类型短信
						sms = applyInfo.getSmsMarket(); // 营销类优先级列表
					} else if(SmsType.CODE04.getCode().equals(type)){
						// 批量通知类型短信
						sms = applyInfo.getBatchSmsNotify(); // 批量通知类优先级列表
					} else if(SmsType.CODE05.getCode().equals(type)){
						// 批量营销类型短信
						sms = applyInfo.getBatchSmsMarket(); // 批量营销类优先级列表
					} else {
						// 未传类型，使用默认？还是返回错误？
						
					}
					try {
						keySn = applyInfo.getApplyKey() + keySn;
						SmsInfo smsInfo = new SmsInfo();
						smsInfo.setSmsId(keySn);
						smsInfo.setOutSmsNo(outSmsNo);
						smsInfo.setSmsType(type);
						smsInfo.setSmsPhone(mobile);
						if(content.length() > 1900){
							smsInfo.setSmsContent(content.substring(0, 1900));
						} else {
							smsInfo.setSmsContent(content);
						}
						smsInfo.setApplyId(applyInfo.getApplyId());
						
						if((SmsType.CODE05.getCode().equals(type) || SmsType.CODE04.getCode().equals(type)) 
								&& (mobile.contains(",") && mobile.length() > 11)){
							
							returnMap = getSendChannel(keySn, sms, "batchSend", mobile, content, sendTo, smsInfo);
							
						} else if((SmsType.CODE01.getCode().equals(type) || SmsType.CODE02.getCode().equals(type) 
								|| SmsType.CODE03.getCode().equals(type)) && (!mobile.contains(",") && mobile.length() == 11)){
							
							returnMap = getSendChannel(keySn, sms, "send", mobile, content, sendTo, smsInfo);
							
						} else {
							// 传入短信类型与不匹配
							returnMap.put("smsNo", keySn);
							returnMap.put("returnCode", ReturnCode.CODE200006.getCode());
							returnMap.put("returnName", ReturnCode.CODE200006.getCnName());
						}
						
//								returnMap = getSendChannel(keySn, sms, "send", mobile, content, sendTo, smsInfo, merInfo);
					} catch (Exception e) {
						logger.info(e.getMessage());
						returnMap.put("smsNo", keySn);
						returnMap.put("returnCode", ReturnCode.CODE999999.getCode());
						returnMap.put("returnName", ReturnCode.CODE999999.getCnName());
					}
				} else {
					// 该商户没有此短信权限
					returnMap.put("smsNo", keySn);
					returnMap.put("returnCode", ReturnCode.CODE200004.getCode());
					returnMap.put("returnName", ReturnCode.CODE200004.getCnName());
				}
			} else {
				// 应用签名不正确 
				returnMap.put("smsNo", keySn);
				returnMap.put("returnCode", ReturnCode.CODE200009.getCode());
				returnMap.put("returnName", ReturnCode.CODE200009.getCnName());
			}
		} else {
			// 参数不全
			returnMap.put("smsNo", keySn);
			returnMap.put("returnCode", ReturnCode.CODE200002.getCode());
			returnMap.put("returnName", ReturnCode.CODE200002.getCnName() + check);
		}
		logger.info("【短信结果返回】服务编号:"+keySn+"--返回参数：" + returnMap.toString());
		return returnMap;
	}
	
	/**
	 * 查询条数信息方法
	 * @param keySn
	 * @param sms
	 * @param mothod
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> getUserInfoChannel(String keySn, String sms, String mothod) throws Exception{
		String result = null;
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(null != sms && !"".equals(sms.trim())){
			String[] channels = StringUtils.split(sms, ",");
			for (String channelId : channels) {
				ChannelInfo channelInfo = channelService.queryByChannelId(channelId);
				if(null != channelInfo && ChannelState.CODE00.getCode().equals(channelInfo.getState())){
					// 此通道可用
					StringBuilder sb = new StringBuilder();
					sb.append("com.zhl.sms.gateway.impl.");
					sb.append(channelInfo.getChannelName());
					sb.append("Impl");
					Class<?> classc = Class.forName(sb.toString());
			        Method method1= classc.getDeclaredMethod(mothod, new Class[]{String.class,String.class,String.class,String.class}) ; 
			        result = (String) method1.invoke(classc.newInstance() ,
													        		 new Object[] {keySn, channelInfo.getUserName(), 
														        	 channelInfo.getPassWord(), channelInfo.getChannelUrl()});
//		        	retrunMap = new ZhuTong_YanImpl().getUseInfo(keySn, channelInfo.getUserName(), channelInfo.getPassWord(), channelInfo.getChannelUrl());
			        break;
				} else {
					// 未找到通道 或 通道禁用,开始循环下一个通道
					continue;
				}
			}
			if(null == result){
				returnMap.put("keySn", keySn);
				returnMap.put("returnCode", ReturnCode.CODE200005.getCode());
				returnMap.put("returnName", ReturnCode.CODE200005.getCnName());
			} else {
				returnMap.put("keySn", keySn);
				returnMap.put("result", result);
				returnMap.put("returnCode", ReturnCode.CODE111111.getCode());
				returnMap.put("returnName", ReturnCode.CODE111111.getCnName());
			}
		}
		return returnMap;
	}
	
	/**
	 * 发送短信方法
	 * @param keySn		订单号
	 * @param sms		短信通道优先级String，逗号分隔
	 * @param mothod	调用方法
	 * @param mobile	手机号
	 * @param content	内容
	 * @param smsInfo	短信记录实体
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> getSendChannel(String keySn, String sms, String mothod, String mobile, String content, String sendTo, SmsInfo smsInfo) throws Exception{
		String result = null;
		Map<String, Object> returnMap = null;
		if(null != sms && !"".equals(sms.trim())){
			String[] channels = StringUtils.split(sms, ",");
			for (String channelId : channels) {
				ChannelInfo channelInfo = channelService.queryByChannelId(channelId);
				if(null != channelInfo && ChannelState.CODE00.getCode().equals(channelInfo.getState())){
					// 此通道可用
					StringBuilder sb = new StringBuilder();
					sb.append("com.zhl.sms.gateway.impl.");
					sb.append(channelInfo.getChannelName());
					sb.append("Impl");
					Class<?> sendClass = Class.forName(sb.toString());
			        Method sendMethod= sendClass.getDeclaredMethod(mothod, new Class[]{String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class}) ; 
			        result = (String) sendMethod.invoke(sendClass.newInstance() ,
													        		 new Object[] {keySn, channelInfo.getUserName(), 
														        	 channelInfo.getPassWord(), channelInfo.getChannelUrl(), 
														        	 mobile, content, sendTo, smsInfo.getSmsType(), 
														        	 channelInfo.getNotifyUrl(), channelInfo.getUserReplyUrl()});
			        
			        if(null != result && !"".equals(result)){
			        	
			        	smsInfo.setChannelId(channelId);//retrunMap.get("result")
			        	if(result.length() > 1300){
			        		smsInfo.setSmsReturn(result.substring(0, 1300));
			        	} else {
			        		smsInfo.setSmsReturn(result);
			        	}
			        	
			        	// 根据通道选择解析返回值方法
			        	StringBuilder sb2 = new StringBuilder();
						sb2.append("com.zhl.sms.gateway.ret.");
						sb2.append("Ret");
						sb2.append(channelInfo.getChannelName());
						Class<?> retClass = Class.forName(sb2.toString());
				        Method retMethod= retClass.getDeclaredMethod(mothod, new Class[]{String.class, String.class, String.class}) ; 
				        returnMap = (Map<String, Object>) retMethod.invoke(
				        											retClass.newInstance(), 
				        											new Object[] {smsInfo.getSmsId(), smsInfo.getOutSmsNo(), result});
						
				        if(returnMap.containsKey("inSmsNo")){
				        	smsInfo.setInSmsNo((String)returnMap.get("inSmsNo"));
				        	returnMap.remove("inSmsNo"); // 上游订单号不返给下游
				        }
				        if(returnMap.containsKey("returnCode")){
				        	smsInfo.setReturnCode((String)returnMap.get("returnCode"));
				        }
				        if(returnMap.containsKey("returnMsg")){
				        	smsInfo.setReturnMsg((String)returnMap.get("returnMsg"));
				        }
				        smsInfo.setCreatedDate(new Date());
				        smsService.saveSmsInfo(smsInfo);
//			        	retrunMap = new ZhuTong_YanImpl().getUseInfo(keySn, channelInfo.getUserName(), channelInfo.getPassWord(), channelInfo.getChannelUrl());
			        } else {
			        	returnMap = new HashMap<String, Object>();
						returnMap.put("smsNo", keySn);
						returnMap.put("returnCode", ReturnCode.CODE410032.getCode());
						returnMap.put("returnName", ReturnCode.CODE410032.getCnName());
			        }
			        break;
				} else {
					// 未找到通道 或 通道禁用,开始循环下一个通道
					continue;
				}
			}
			if(null == returnMap){
				returnMap = new HashMap<String, Object>();
				returnMap.put("smsNo", keySn);
				returnMap.put("returnCode", ReturnCode.CODE200005.getCode());
				returnMap.put("returnName", ReturnCode.CODE200005.getCnName());
			}
		} else {
			returnMap = new HashMap<String, Object>();
			returnMap.put("smsNo", keySn);
			returnMap.put("returnCode", ReturnCode.CODE200007.getCode());
			returnMap.put("returnName", ReturnCode.CODE200007.getCnName());
		}
		return returnMap;
	}
	
	private String checkGetUserInfo(Map<String, Object> map){
		
		if(!CheckUtil.verifyParam((String)map.get("applyNo"))){
			return "applyNo为空";
		}
		if(!CheckUtil.verifyParam((String)map.get("type"))){
			return "type为空";
		}
		return null;
	}
	
	private String checkSendSms(Map<String, Object> map){
		
		if(!CheckUtil.verifyParam((String)map.get("applyNo"))){
			return "applyNo为空";
		}
		if(!CheckUtil.verifyParam((String)map.get("sign"))){
			return "sign为空";
		}
		if(!CheckUtil.verifyParam((String)map.get("mobile"))){
			return "mobile为空";
		}
		if(!CheckUtil.verifyParam((String)map.get("content"))){
			return "content为空";
		}
		if(!CheckUtil.verifyParam((String)map.get("type"))){
			return "type为空";
		}
		if(!CheckUtil.verifyParam((String)map.get("outSmsNo"))){
			return "outSmsNo为空";
		}
		return null;
	}
	
	private boolean signCheck(Map<String, Object> map, String key){
//		String inSign = (String)map.get("sign");
//		Map<String, Object> tMap = new HashMap<String, Object>();
//		tMap.put("applyNo", (String)map.get("applyNo"));
//		tMap.put("mobile", (String)map.get("mobile"));
//		tMap.put("content", (String)map.get("content"));
//		tMap.put("type", (String)map.get("type"));
//		tMap.put("outSmsNo", (String)map.get("outSmsNo"));
//		
//		tMap = Util.sortMapByKey(tMap);// 排序
//		String json = JsonUtil.getMapToJson(tMap);
//		String sign = MessageSecurity.getMessageSecurity("", json, key);
//		
//		if(inSign.equals(sign)){
//			return true;
//		}
		return true;
	}


}
