package com.zhl.sms.util;

import java.math.BigDecimal;

import com.mysql.jdbc.StringUtils;

import freemarker.template.utility.StringUtil;

public class CheckUtil {
	
	/**
	 * 非空验证
	 * @param obj
	 * @return
	 */
	public static boolean verifyParam(Object obj){
		if(obj instanceof String){
			String str = (String) obj;
			if(null != str && !"".equals(str.trim())){
				return true;
			}
		} else if(obj instanceof BigDecimal){
			if(null != obj && !"".equals(obj)){
				BigDecimal b = (BigDecimal)obj;
				// 大于0
				if(Util.BigDecimalGreaterThanAndEqual(b, new BigDecimal(0))){
					return true;
				} else {
					return false;
				}
			}
		} else {
			if(null != obj && !"".equals(obj)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 检查短信内容是否存在标签
	 * 		当没有标签时，暂加上的是【中互联】标签
	 * @return
	 */
	public static String checkContentTag(String content){
		if(content.indexOf("【") == -1 && content.indexOf("】") == -1){
			return new StringBuilder().append("【中互联】").append(content).toString();
		} else {
			return content;
		}
	}

}
