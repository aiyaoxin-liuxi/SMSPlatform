package com.zhl.sms.util;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.pub.util.security.MapKeyComparator;


public class Util {
	
	/**
	 * 空字符串转null
	 * @param str
	 * @return
	 */
	public static String EmptyToNull(String str){
		return null == str ? "" : str.trim();
	}
	
	/**
	 * 空bigdecimal转为0
	 * @param b
	 * @return
	 */
	public static BigDecimal EmptyToNull(BigDecimal b){
		return null == b ? new BigDecimal(0) : b;
	}
	
	/**
	 * BigDecimal比较————大于等于
	 * 
	 * 		左大于等于右返回true
	 * @param val1	
	 * @param val2
	 * @return
	 */
	public static boolean BigDecimalGreaterThanAndEqual(BigDecimal val1, BigDecimal val2){
		if(val1.compareTo(val2) == 1 || val1.compareTo(val2) == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * BigDecimal比较————小于等于
	 * 
	 * 		左小于等于右返回true
	 * @param val1	
	 * @param val2
	 * @return
	 */
	public static boolean BigDecimalLessThanAndEqual(BigDecimal val1, BigDecimal val2){
		if(val1.compareTo(val2) == -1 || val1.compareTo(val2) == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * BigDecimal比较————大于
	 * 
	 * 		左大于右返回true
	 * @param val1	
	 * @param val2
	 * @return
	 */
	public static boolean BigDecimalGreaterThan(BigDecimal val1, BigDecimal val2){
		return val1.compareTo(val2) == 1 ? true : false;
	}
	
	/**
	 * BigDecimal比较————小于
	 * 
	 * 		左小于右返回true
	 * @param val1	
	 * @param val2
	 * @return
	 */
	public static boolean BigDecimalLessThan(BigDecimal val1, BigDecimal val2){
		return val1.compareTo(val2) == -1 ? true : false;
	}
	
	/**
	 * BigDecimal比较————等于
	 * 
	 * 		左等于右返回true
	 * 
	 * @param val1	
	 * @param val2
	 * @return
	 */
	public static boolean BigDecimalequal(BigDecimal val1, BigDecimal val2){
		return val1.compareTo(val2) == 0 ? true : false;
	}
	
	/**
	 * 将url参数转换成map
	 * 
	 * @param param
	 *            aa=11&bb=22&cc=33
	 * @return
	 */
	public static Map<String, Object> getUrlParams(String param) {
		Map<String, Object> map = new HashMap<String, Object>(0);
		if (StringUtils.isBlank(param)) {
			return map;
		}
		String[] params = param.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}

	/**
	 * 将map转换成url
	 * 
	 * @param map
	 * @return
	 */
	public static String getUrlParamsByMap(Map<String, Object> map) {
		if (map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sb.append(entry.getKey() + "=" + entry.getValue());
			sb.append("&");
		}
		String s = sb.toString();
		if (s.endsWith("&")) {
			s = StringUtils.substringBeforeLast(s, "&");
		}
		return s;
	}
	
	 /** 
     * 使用 Map按key进行排序 
     * @param map 
     * @return 
     */  
    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {  
        if (map == null || map.isEmpty()) {  
            return null;  
        }
        Map<String, Object> sortMap = new TreeMap<String, Object>(new MapKeyComparator());  
        sortMap.putAll(map);  
        return sortMap;  
    }
    
    /**
     * stirng 转 list
     * @return
     */
    public static List<String> String2List(String strs){
    	String[] ss = StringUtils.split(strs, ",");
		return Arrays.asList(ss);
    }
    
    public static boolean dDirectory(String dir){     
        if(!dir.endsWith(File.separator)){     
            dir = dir+File.separator;     
        }     
        File dirFile = new File(dir);     
        if(!dirFile.exists() || !dirFile.isDirectory()){     
            return false;     
        }     
        boolean flag = true;     
        File[] files = dirFile.listFiles();     
        for(int i=0;i<files.length;i++){     
            if(files[i].isFile()){     
                flag = dFile(files[i].getAbsolutePath());     
                if(!flag){     
                    break;     
                }     
            }     
            else{     
                flag = dDirectory(files[i].getAbsolutePath());     
                if(!flag){     
                    break;     
                }     
            }     
        }     
        if(!flag){     
            return false;     
        }     
        if(dirFile.delete()){     
            return true;     
        }else{     
            return false;     
        }     
    }  
    public static boolean dFile(String fileName){     
        File file = new File(fileName);     
        if(file.isFile() && file.exists()){     
            file.delete();     
            return true;     
        }else{     
            return false;     
        }     
    }   
	
	
}
