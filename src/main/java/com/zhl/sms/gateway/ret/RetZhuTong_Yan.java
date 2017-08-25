package com.zhl.sms.gateway.ret;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.zhl.sms.gateway.RetAnalysis;
import com.zhl.sms.pojo.enums.ReturnCode;

/**
 * 助通验证码类短信返回值解析
 * @author 刘熙
 *
 */
public class RetZhuTong_Yan implements RetAnalysis  {
	
	private Logger logger = Logger.getLogger(RetZhuTong_Yan.class);
	
	@Override
	public Map<String,Object> send(String smsId, String outSmsNo, String result){
		logger.info("开始解析：【助通_验证码】服务，【服务编号】:"+smsId+"【返回参数】：" + result);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(result.contains(",")){
			
			String[] ss = StringUtils.split(result, ",");
			
			switch (Integer.parseInt(ss[0])) {
			case 1:
				returnMap.put("inSmsNo", ss[1]);
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE111111.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE111111.getCnName());
				break;
				
			case 2:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410001.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410001.getCnName());
				break;
				
			case 3:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410002.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410002.getCnName());
				break;
			case 4:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410003.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410003.getCnName());
				break;
			case 5:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410004.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410004.getCnName());
				break;
			case 6:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410005.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410005.getCnName());
				break;
			case 7:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410006.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410006.getCnName());
				break;
			case 10:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410007.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410007.getCnName());
				break;
			case 11:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410008.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410008.getCnName());
				break;
			case 12:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410009.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410009.getCnName());
				break;
			case 13:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410010.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410010.getCnName());
				break;
			case 14:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410011.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410011.getCnName());
				break;
			case 15:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410012.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410012.getCnName());
				break;
			case 17:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410013.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410013.getCnName());
				break;
			case 18:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410014.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410014.getCnName());
				break;
			case 19:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410015.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410015.getCnName());
				break;
			case 20:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410016.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410016.getCnName());
				break;
			case 21:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410017.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410017.getCnName());
				break;
			case 22:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410018.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410018.getCnName());
				break;
			case 23:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410019.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410019.getCnName());
				break;
			case 24:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410020.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410020.getCnName());
				break;
			case 25:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410021.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410021.getCnName());
				break;
			case 26:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410022.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410022.getCnName());
				break;
			case 27:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410023.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410023.getCnName());
				break;
			case 28:
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410024.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410024.getCnName());
				break;
			case 29:
				returnMap.put("inSmsNo", ss[1]);
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410025.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410025.getCnName());
				break;
			case 30:
				returnMap.put("inSmsNo", ss[1]);
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410026.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410026.getCnName());
				break;
			case 31:
				returnMap.put("inSmsNo", ss[1]);
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410027.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410027.getCnName());
				break;
			case 32:
				returnMap.put("inSmsNo", ss[1]);
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410028.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410028.getCnName());
				break;
			case 33:
				returnMap.put("inSmsNo", ss[1]);
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410029.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410029.getCnName());
				break;
			case 34:
				returnMap.put("inSmsNo", ss[1]);
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410030.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410030.getCnName());
				break;
			case 35:
				returnMap.put("inSmsNo", ss[1]);
				returnMap.put("smsNo", smsId);
				returnMap.put("outSmsNo", outSmsNo);
				returnMap.put("returnCode", ReturnCode.CODE410031.getCode());
				returnMap.put("returnMsg", ReturnCode.CODE410031.getCnName());
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
		logger.info("解析完成：【助通_验证码】服务，【服务编号】:"+smsId+"【返回参数】：" + returnMap.toString());
		return returnMap;
	}

	@Override
	public Map<String, Object> batchSend(String smsId, String outSmsNo, String result) {
		return null;
	}

}
