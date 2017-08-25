package com.zhl.sms.controller.notify;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.pub.util.uuid.KeySn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhl.sms.gateway.NotifyGetwayService;
import com.zhl.sms.service.IApplyService;
import com.zhl.sms.service.ISmsService;
import com.zhl.sms.util.CheckUtil;
import com.zhl.sms.util.Util;


/**
 * 易信通——用户短信回复通知接收类
 * @author 刘熙
 */
@Controller
@RequestMapping(value = "/notify")
public class YiXinTongNotifyController {
	
	private Logger logger = LoggerFactory.getLogger(YiXinTongNotifyController.class);

	@Resource(name = "smsService")
	private ISmsService smsService;
	@Resource(name = "notifyGetwayService")
	private NotifyGetwayService notifyGetwayService;

	@RequestMapping(value = "/yxtMsgNotify")
	public String yxtMsgNotify(@RequestParam(value="reportType") String reportType, 
								@RequestParam(value="timestamp") String timestamp, 
								@RequestParam(value="sentNum", required=false) String sentNum, 
								@RequestParam(value="others", required=false) String others, 
								@RequestParam(value="feeNum", required=false) String feeNum, 
								@RequestParam(value="failMobiles", required=false) String failMobiles, 
								@RequestParam(value="failDesc", required=false) String failDesc, 
								@RequestParam(value="mobile", required=false) String mobile, 
								@RequestParam(value="content", required=false) String content, 
								@RequestParam(value="status", required=false) String status, 
								@RequestParam(value="md5") String md5) {
		
		String ret = "";
		String keySn = KeySn.getKey();
		String check = yxtMsgNotify(reportType, timestamp, md5);
		if (null == check) {
			
			if("0".equals(reportType)){
				// 审核未通过
				
				
			} else if("1".equals(reportType)){
				// 发送完毕
				
			} else if("3".equals(reportType)){
				// 上行短信
				logger.info("reportType-------->" + reportType);
				logger.info("mobile-------->" + mobile);
				logger.info("content-------->" + content);
				logger.info("timestamp-------->" + timestamp);
				logger.info("others-------->" + others);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("reportType", reportType);
				map.put("mobile", mobile);
				map.put("content", content);
				map.put("timestamp", timestamp);
				String mapStr = Util.getUrlParamsByMap(map);
				
				logger.info("md5-------->" + md5);
				logger.info("Testmd5-------->" + DigestUtils.md5Hex(mapStr));

			} else if("4".equals(reportType)){
				// 号码到达状态
				
			}
			
			
			
			
//			SmsInfo smsInfo = smsService.querySmsInfoByInSmsNoAndPhone(inSmsNo, smsPhone);
//			if(null != smsInfo){
//				
//				ApplyInfo merInfo = applyService.queryByApplyId(smsInfo.getApplyId());
//				if(null != merInfo){
//					
//					ret = notifyGetwayService.msgReply(keySn, merInfo.getUserReplyUrl(), 
//												 merInfo.getApplyKey(), smsInfo.getOutSmsNo(), 
//												 inSmsNo, smsPhone, userReturnContent);
//					
//					smsService.updateSmsUserReturn(smsInfo.getSmsId(), userReturnContent);
//				}
//			}
		}
		return ret;
	}

	private String yxtMsgNotify(String reportType, String timestamp, String md5) {
		if (!CheckUtil.verifyParam(reportType)) {
			return "reportType";
		}
		if (!CheckUtil.verifyParam(timestamp)) {
			return "timestamp";
		}
		if (!CheckUtil.verifyParam(md5)) {
			return "md5";
		}
		return null;
	}

}
