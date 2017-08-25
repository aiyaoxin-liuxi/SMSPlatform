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
import org.pub.util.uuid.KeySn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhl.sms.pojo.ApplyInfo;
import com.zhl.sms.pojo.BatchInfo;
import com.zhl.sms.pojo.ChannelInfo;
import com.zhl.sms.pojo.SmsInfo;
import com.zhl.sms.pojo.enums.ChannelState;
import com.zhl.sms.pojo.enums.ReturnCode;
import com.zhl.sms.pojo.enums.SmsType;
import com.zhl.sms.service.IApplyService;
import com.zhl.sms.service.IBatchService;
import com.zhl.sms.service.IChannelService;
import com.zhl.sms.service.ISmsService;
import com.zhl.sms.util.CheckUtil;

/**
 * 此类包含发送批量短信相关接口
 * @author 刘熙
 */
@Controller
@RequestMapping(value = "/sms")
public class BatchSmsController {
	
	private Logger logger = Logger.getLogger(BatchSmsController.class);

	@Resource(name = "applyService")
	private IApplyService applyService;
	@Resource(name = "channelService")
	private IChannelService channelService;
	@Resource(name = "smsService")
	private ISmsService smsService;
	@Resource(name = "batchService")
	private IBatchService batchService;
	
	@RequestMapping(value = "/batchSendSms")
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
			String applyNo = (String) map.get("applyNo");	// 商户号
			String outSmsNo = (String) map.get("outSmsNo");		// 下游消息编号
			String mobile = (String) map.get("mobile");			// 手机号
			String content = (String) map.get("content");		// 内容
			String type = (String) map.get("type");				// 类型
			String sendTo = "";// 接收人（非必填）
			if(map.containsKey("sendTo") && !"".equals((String)map.get("sendTo"))){
				sendTo = (String)map.get("sendTo");
			} else {
				sendTo = mobile;
			}
			
			// 获取商户号
			ApplyInfo applyInfo = applyService.queryByApplyId(applyNo);
			// 检查商户权限
			String power = applyInfo.getApplyPower(); // 商户短信权限
			if(power.contains(type)){
				String sms = "";
				if(SmsType.CODE04.getCode().equals(type)){
					// 批量通知类型短信
					sms = applyInfo.getBatchSmsNotify(); // 批量通知类优先级列表
				} else if(SmsType.CODE05.getCode().equals(type)){
					// 批量营销类型短信
					sms = applyInfo.getBatchSmsMarket(); // 批量营销类优先级列表
				} else {
					// 未传类型，使用默认？还是返回错误？
					
				}
				try {
					SmsInfo smsInfo = new SmsInfo();
					smsInfo.setSmsId(keySn);
					smsInfo.setOutSmsNo(outSmsNo);
					smsInfo.setSmsType(type);
					smsInfo.setSmsPhone(keySn);
					smsInfo.setSmsContent(content);
					smsInfo.setApplyId(applyInfo.getApplyId());
					
					returnMap = getSendChannel(keySn, sms, "batchSend", mobile, content, sendTo, smsInfo, applyInfo);
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
			// 参数不全
			returnMap.put("keySn", keySn);
			returnMap.put("returnCode", ReturnCode.CODE200002.getCode());
			returnMap.put("returnName", ReturnCode.CODE200002.getCnName() + check);
		}
		
		return returnMap;
	}
	
	private Map<String, Object> getSendChannel(String keySn, String sms, String mothod, String mobile, String content, String sendTo, SmsInfo smsInfo, ApplyInfo applyInfo) throws Exception{
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
														        	 applyInfo.getNotifyUrl(), applyInfo.getUserReplyUrl()});
			        
			        smsInfo.setChannelId(channelId);//retrunMap.get("result")
		        	smsInfo.setSmsReturn(result);
		        	
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
			        }
			        smsInfo.setCreatedDate(new Date());
			        smsService.saveSmsInfo(smsInfo);
			        
			        BatchInfo batchInfo = new BatchInfo();
			        batchInfo.setBatchId(keySn);
			        batchInfo.setPhone(mobile);
			        batchService.saveBatchInfo(batchInfo);
//		        	retrunMap = new ZhuTong_YanImpl().getUseInfo(keySn, channelInfo.getUserName(), channelInfo.getPassWord(), channelInfo.getChannelUrl());
			        break;
				} else {
					// 未找到通道 或 通道禁用,开始循环下一个通道
					continue;
				}
			}
			if(null == returnMap){
				returnMap = new HashMap<String, Object>();
				returnMap.put("keySn", keySn);
				returnMap.put("returnCode", ReturnCode.CODE200005.getCode());
				returnMap.put("returnName", ReturnCode.CODE200005.getCnName());
			}
		} else {
			returnMap = new HashMap<String, Object>();
			returnMap.put("keySn", keySn);
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


}
