package com.zhl.sms.controller.notify;

import javax.annotation.Resource;

import org.pub.util.uuid.KeySn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhl.sms.gateway.NotifyGetwayService;
import com.zhl.sms.pojo.ApplyInfo;
import com.zhl.sms.pojo.SmsInfo;
import com.zhl.sms.service.IApplyService;
import com.zhl.sms.service.ISmsService;
import com.zhl.sms.util.CheckUtil;


/**
 * 助通——用户短信回复通知接收类
 * @author 刘熙
 */
@Controller
@RequestMapping(value = "/notify")
public class ZhuTongNotifyController {

	@Resource(name = "smsService")
	private ISmsService smsService;
	@Resource(name = "applyService")
	private IApplyService applyService;
	@Resource(name = "notifyGetwayService")
	private NotifyGetwayService notifyGetwayService;

	@RequestMapping(value = "/msgReply")
	public String userReply(@RequestParam("msgid") String inSmsNo, 
							@RequestParam("mobile") String smsPhone, 
							@RequestParam("content") String userReturnContent) {
		
		String ret = "";
		String keySn = KeySn.getKey();
		String check = checkUserReply(inSmsNo, smsPhone, userReturnContent);
		if (null == check) {
			SmsInfo smsInfo = smsService.querySmsInfoByInSmsNoAndPhone(inSmsNo, smsPhone);
			if(null != smsInfo){
				
				ApplyInfo applyInfo = applyService.queryByApplyId(smsInfo.getApplyId());
				if(null != applyInfo){
					
					ret = notifyGetwayService.msgReply(keySn, applyInfo.getUserReplyUrl(), 
												 applyInfo.getApplyKey(), smsInfo.getOutSmsNo(), 
												 inSmsNo, smsPhone, userReturnContent);
					
					smsService.updateSmsUserReturn(smsInfo.getSmsId(), userReturnContent);
				}
			}
		}
		return ret;
	}

	private String checkUserReply(String inSmsNo, String smsPhone, String userReturnContent) {
		if (!CheckUtil.verifyParam(inSmsNo)) {
			return "msgid";
		}
		if (!CheckUtil.verifyParam(smsPhone)) {
			return "mobile";
		}
		if (!CheckUtil.verifyParam(userReturnContent)) {
			return "content";
		}
		return null;
	}
	
	private String checkMsgReceipt(String param) {
		if (!CheckUtil.verifyParam(param)) {
			return "param";
		}
		return null;
	}

}
