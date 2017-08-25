package com.zhl.sms.gateway.ret;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.zhl.sms.gateway.RetAnalysis;
import com.zhl.sms.pojo.enums.ReturnCode;

/**
 * 助通营销类类短信返回值解析
 * @author 刘熙
 *
 */
public class RetZhuTong_Ying implements RetAnalysis {
	
	private Logger logger = Logger.getLogger(RetZhuTong_Ying.class);
	
	@Override
	public Map<String,Object> send(String smsId, String outSmsNo, String result){
		logger.info("开始解析：【助通_营销】服务，【服务编号】:"+smsId+"【返回参数】：" + result);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(result.contains(",")){
			
			String[] ss = StringUtils.split(result, ",");
			
			switch (Integer.parseInt(ss[0])) {
			case -1:
				returnMap.put("inSmsNo", ss[1]);
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410032.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410032.getCnName());
				break;
			case 1:
				returnMap.put("inSmsNo", ss[1]);
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE111111.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE111111.getCnName());
				break;
			case 11:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410033.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410033.getCnName());
				break;
			case 12:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410034.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410034.getCnName());
				break;
			case 13:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410009.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410009.getCnName());
				break;
			case 15:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410035.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410035.getCnName());
				break;
			case 16:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410021.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410021.getCnName());
				break;
			case 17:
				returnMap.put("inSmsNo", ss[1]);
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410025.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410025.getCnName());
				break;
			case 18:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410010.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410010.getCnName());
				break;
			case 19:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410036.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410036.getCnName());
				break;
			case 20:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410037.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410037.getCnName());
				break;
			case 21:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410014.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410014.getCnName());
				break;
			case 22:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410038.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410038.getCnName());
				break;
			case 28:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410039.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410039.getCnName());
				break;
			case 29:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410040.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410040.getCnName());
				break;
			case 98:
				returnMap.put("inSmsNo", ss[1]);
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410041.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410041.getCnName());
				break;
			case 99:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410017.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410017.getCnName());
				break;
			default:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", result);
				returnMap.put("returnMsg", result);
				break;
			}
		} else {
			returnMap.put("smsNo", smsId);
			returnMap.put("outSmsNo", outSmsNo);
			returnMap.put("returnCode", result);
			returnMap.put("returnMsg", result);
		}
		logger.info("解析完成：【助通_营销】服务，【服务编号】:"+smsId+"【返回参数】：" + returnMap.toString());
		return returnMap;
	}
	
	@Override
	public Map<String,Object> batchSend(String smsId, String outSmsNo, String result){
		logger.info("开始解析：【助通_营销】服务，【服务编号】:"+smsId+"【返回参数】：" + result);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(result.contains(",")){
			
			String[] ss = StringUtils.split(result, ",");
			
			switch (Integer.parseInt(ss[0])) {
			case -1:
				returnMap.put("inSmsNo", ss[1]);
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410032.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410032.getCnName());
				break;
			case 1:
				returnMap.put("inSmsNo", ss[1]);
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE111111.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE111111.getCnName());
				break;
			case 11:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410033.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410033.getCnName());
				break;
			case 12:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410034.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410034.getCnName());
				break;
			case 18:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410010.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410010.getCnName());
				break;
			case 19:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410036.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410036.getCnName());
				break;
			case 20:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410037.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410037.getCnName());
				break;
			case 23:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410042.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410042.getCnName());
				break;
			case 28:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410039.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410039.getCnName());
				break;
			case 29:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410040.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410040.getCnName());
				break;
			case 98:
				returnMap.put("inSmsNo", ss[1]);
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410041.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410041.getCnName());
				break;
			case 99:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410017.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410017.getCnName());
				break;
			default:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", result);
				returnMap.put("returnMsg", result);
				break;
			}
		} else {
			returnMap.put("smsNo", smsId);
			returnMap.put("outSmsNo", outSmsNo);
			returnMap.put("returnCode", result);
			returnMap.put("returnMsg", result);
		}
		logger.info("解析完成：【助通_营销】服务，【服务编号】:"+smsId+"【返回参数】：" + returnMap.toString());
		return returnMap;
	}

}
