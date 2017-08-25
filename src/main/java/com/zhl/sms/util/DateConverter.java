package com.zhl.sms.util;
/*
 * DateConverter.java
 * Copyright org.javaplus, Inc. All rights reserved.
 * org.javaplus PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 日期的转换
 */
public class DateConverter {

    private static final Logger logger = Logger.getLogger(DateConverter.class.getName());
    // 日期格式化字符
    public static final String DF_YMD = "yyyy-MM-dd";
    // 日期格式化字符
    public static final String DF_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    // 日期格式化字符
    public static final String DF_YMDHMS_B = "yyyyMMdd-HHmmss";
    // 日期格式化字符
    public static final String DF_YMDHMS_ZH = "yyyy年MM月dd日 HH:mm:ss";
    /**
     * 年
     */
    private String year;
    /**
     * 月
     */
    private String month;
    /**
     * 日
     */
    private String day;

    /**
     * 日期解析出年、月、日
     *
     * @param inputDate String
     */
    public void dateParse(String inputDate) {
        //将inputDate 中的 '-' 替换成 ' ' (空格)
        inputDate = inputDate.replace('-', ' ');

        //解析出子串
        StringTokenizer date = new StringTokenizer(inputDate);
        year = date.nextToken().trim();
        month = date.nextToken().trim();
        day = date.nextToken().trim();
    }

    /**
     * get day
     *
     * @return String day
     *
     */
    public String getDay() {
        return day;
    }

    /**
     * get month
     *
     * @return String month
     *
     */
    public String getMonth() {
        return month;
    }

    /**
     * get year
     *
     * @return String year
     *
     */
    public String getYear() {
        return year;
    }
    
    /**
   	 * 获取?小时以前时间
   	 * @return
   	 */
   	public static Date getDateNextHour(int hour){
   		DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   		Calendar curr = Calendar.getInstance();
   		curr.set(Calendar.HOUR,curr.get(Calendar.HOUR) - hour);
   		Date date = curr.getTime();
   		return date;
   	}
   	
   	/**
   	 * 获取1天以前时间
   	 * @return
   	 * @throws ParseException 
   	 */
   	public static Date getDateNextDay(int day) throws ParseException{
   		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
   		Calendar curr = Calendar.getInstance();
   		curr.set(Calendar.DATE,curr.get(Calendar.DATE) - day);
//   		Date date = curr.getTime();
   		Date date = f.parse(f.format(curr.getTime()));
   		return date;
   	}
   	
   	/**
   	 * 获取1天以前的日期，只要天
   	 * @return
   	 * @throws ParseException 
   	 */
   	public static String getNextDayNo(int day) throws ParseException{
   		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
   		Calendar curr = Calendar.getInstance();
   		curr.set(Calendar.DATE,curr.get(Calendar.DATE) - day);
   		return String.valueOf(curr.get(Calendar.DAY_OF_MONTH));
   	}
   	
   	/**
   	 * 获取天
   	 * @return
   	 * @throws ParseException 
   	 */
   	public static String getDayNo(Date date) throws ParseException{
   		Calendar curr = new GregorianCalendar();
   		curr.setTime(date);
   		return String.valueOf(curr.get(Calendar.DAY_OF_MONTH));
   	}
   	
    /**
	 * 获取1月以后日期
	 * @return
	 */
	public static Date getDateNextMonth(){
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.MONTH,curr.get(Calendar.MONTH)+1);
		Date date = curr.getTime();
		return date;
	}
	
	/**
	 * 获取1月以后日期
	 * @return
	 * @throws ParseException 
	 */
	public static Date getDateNextMonth(int count) throws ParseException{
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.MONTH,curr.get(Calendar.MONTH)+ 1 + count);
//		curr.set(Calendar.MONTH,curr.get(Calendar.MONTH)+ 1 + count);
//		Date date = curr.getTime();
		Date date = f.parse(f.format(curr.getTime()));
		return date;
	}
	
	/**
	 * 根据传入具体日期 ，获取下月的这个日期 
	 * @param count	增加的月
	 * @param day	固定的日期
	 * @return
	 * @throws ParseException
	 */
    public static Date getDateNextMonth(int count, int day) throws ParseException {  
    	DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.MONTH,curr.get(Calendar.MONTH)+ 1 + count);
		curr.set(Calendar.DAY_OF_MONTH, day);
		Date date = f.parse(f.format(curr.getTime()));
        return date;
    }  
    
//	/**
//	 * 获取1月以后日期
//	 * @return
//	 */
//	public static String getDateNextMonth(){
//		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar curr = Calendar.getInstance();
//		curr.set(Calendar.MONTH,curr.get(Calendar.MONTH)+1);
//		Date date = curr.getTime();
//		return f.format(date);
//	}
	
	/**
	 * 返回系统当前时间(精确到秒),
	 * 
	 * @return 以yyyy-MM-dd HH:mm:ss为格式的当前系统时间
	 */
	public static String getDateToBim() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	
	/**
	 * 返回系统当前时间(精确到秒),
	 * 
	 * @return 以yyyy-MM-dd HH:mm:ss为格式的当前系统时间
	 */
	public static String getDateToStr() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(date);
	}
	
	/**
	 * 返回系统当前时间(精确到日),
	 * 
	 * @return 以yyyy-MM-dd HH:mm:ss为格式的当前系统时间
	 */
	public static String getDateToDay() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(date);
	}

    //----------------------------------------------------------- 比较日期 <<< S
    /**
     * 得到两个日期相差的毫秒
     *
     * @param baseDate 基日期（初减）
     * @param opDate 要比较的日期（要减去的）
     * @return 得到两个日期相差的毫秒
     */
    public static long getMillisDiff(Date baseDate, Date opDate) {
        return baseDate.getTime() - opDate.getTime();
    }

    /**
     * 得到两个日期相差的秒
     *
     * @param baseDate 基日期（初减）
     * @param opDate 要比较的日期（要减去的）
     * @return 得到两个日期相差的秒
     */
    public static long getSecsDiff(Date baseDate, Date opDate) {
        return getMillisDiff(baseDate, opDate) / 1000;
    }

    /**
     * 得到两个日期相差的分
     *
     * @param baseDate 基日期（初减）
     * @param opDate 要比较的日期（要减去的）
     * @return 得到两个日期相差的分
     */
    public static long getMinsDiff(Date baseDate, Date opDate) {
        return getMillisDiff(baseDate, opDate) / (60 * 1000);
    }

    /**
     * 得到两个日期相差的小时
     *
     * @param baseDate 基日期（初减）
     * @param opDate 要比较的日期（要减去的）
     * @return 得到两个日期相差的小时
     */
    public static long getHoursDiff(Date baseDate, Date opDate) {
        return getMillisDiff(baseDate, opDate) / (60 * 60 * 1000);
    }

    /**
     * 得到两个日期相差的天数
     *
     * @param baseDate 基日期（初减）
     * @param opDate 要比较的日期（要减去的）
     * @return 得到两个日期相差的天数
     */
    public static long getDayDiff(Date baseDate, Date opDate) {
        return getMillisDiff(baseDate, opDate) / (24 * 60 * 60 * 1000);
    }

    /**
     * 得到两个日期相差的月份
     *
     * @param baseDate 基日期（初减）
     * @param opDate 要比较的日期（要减去的）
     * @return 得到两个日期相差的月份
     */
    public static int getMonthDiff(Date baseDate, Date opDate) {
        Calendar baseCalendar = new GregorianCalendar();
        Calendar opCalendar = new GregorianCalendar();
        baseCalendar.setTime(baseDate);
        opCalendar.setTime(opDate);
        int baseYear = baseCalendar.get(Calendar.YEAR);
        int baseMonth = baseCalendar.get(Calendar.MONTH);
        int opYear = opCalendar.get(Calendar.YEAR);
        int opMonth = opCalendar.get(Calendar.MONTH);
        return (baseYear - opYear) * 12 + (baseMonth - opMonth);
    }

    /**
     * 得到两个日期相差的年
     *
     * @param baseDate 基日期（初减）
     * @param opDate 要比较的日期（要减去的）
     * @return 得到两个日期相差的年
     */
    public static int getYearDiff(Date baseDate, Date opDate) {
        Calendar baseCalendar = new GregorianCalendar();
        Calendar opCalendar = new GregorianCalendar();
        baseCalendar.setTime(baseDate);
        opCalendar.setTime(opDate);
        int baseYear = baseCalendar.get(Calendar.YEAR);
        int opYear = opCalendar.get(Calendar.YEAR);
        return baseYear - opYear;
    }

    /**
     * 得到两个时间的差值以中文显示
     *
     * @param endDate 结束时间
     * @param stateDate 开始时间
     * @return 得到两个时间的差值以中文显示
     */
    public static String getTimeDiff(Date endDate, Date stateDate) {
        String timeDiff = "";
        if (endDate != null && stateDate != null) {
            long runTime = endDate.getTime() - stateDate.getTime();
            long _d = 24 * 60 * 60 * 1000;
            long _h = 60 * 60 * 1000;
            long _m = 60 * 1000;
            long _s = 1000;
            if (runTime < _s) {
                return runTime + "毫秒";
            }
            if (runTime > _d) {
                long d = runTime / _d;
                runTime = runTime % _d;
                timeDiff += d + "天";
            }
            if (runTime > _h) {
                long h = runTime / _h;
                runTime = runTime % _h;
                timeDiff += h + "时";
            }
            if (runTime > _m) {
                long m = runTime / _m;
                runTime = runTime % _m;
                timeDiff += m + "分";
            }
            if (runTime > _s) {
                long s = runTime / _s;
                timeDiff += s + "秒";
            }
        }
        return timeDiff;
    }
    
//    /**
//     * 比较日期是否在？日以前
//     *
//     * @param baseDate 基日期（初减）
//     * @param opDate 要比较的日期（要减去的）
//     * @return 得到两个日期相差的年
//     */
//    public static int getDayDiff(Date baseDate, int opDate) {
//        Calendar baseCalendar = new GregorianCalendar();
//        baseCalendar.setTime(baseDate);
//        int baseDay = baseCalendar.get(Calendar.DAY_OF_YEAR);
//        int baseDay = baseCalendar.get(Calendar.);
//        return baseDay - opDate;
//    }
    //----------------------------------------------------------- 比较日期 E >>>
    //----------------------------------------------------------- 日期转换 <<< S

    /**
     * 将Date或java.sql.Date日期类型转换为 "yyyy-mm-dd" 格式的字符串
     *
     * @param dateObj 日期
     * @return 格式化后的字符串
     */
    public static String date2String(Object dateObj) {
        return date2String(dateObj, DF_YMD);
    }

    /**
     * 将Date或java.sql.Date日期类型转换为格式字符串
     *
     * @param dateObj 日期
     * @param format String
     * @return 格式化后的字符串
     */
    public static String date2String(Object dateObj, String format) {
        if (dateObj == null) {
            return "";
        }
        if (Date.class.isInstance(dateObj)) {
            return date2StringUtil((Date) dateObj, format);
        } else if (java.sql.Date.class.isInstance(dateObj)) {
            return date2StringSql((java.sql.Date) dateObj, format);
        }
        return "";
    }

    /**
     * 将Date日期类型转换为 "yyyy-mm-dd" 格式的字符串
     *
     * @param date 日期
     * @return 格式化后的字符串
     */
    public static String date2StringUtil(Date date) {
        return date2StringUtil(date, DF_YMDHMS);
    }

    /**
     * 将Date日期类型转换为格式字符串
     *
     * @param date 日期
     * @param format String
     * @return 格式化后的字符串
     */
    public static String date2StringUtil(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将java.sql.Date日期类型转换为 "yyyy-mm-dd" 格式的字符串
     *
     * @param date 日期
     * @return 格式化后的字符串
     */
    public static String date2StringSql(java.sql.Date date) {
        return date2StringSql(date, DF_YMD);
    }

    /**
     * 将java.sql.Date日期类型转换为格式字符串
     *
     * @param date 日期
     * @param format String
     * @return 格式化后的字符串
     */
    public static String date2StringSql(java.sql.Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    
    /**
     * 将"yyyy-mm-dd" 格式的字符串转换为java.sql.Date日期类型
     *
     * @param date String
     * @return java.sql.Date
     */
    public static java.sql.Date string2Date(String date) {
        return string2Date(date, DF_YMD);
    }
    

    /**
     * 将"yyyy-mm-dd" 格式的字符串转换为java.sql.Date日期类型
     *
     * @param date String
     * @return java.sql.Date
     * @throws ParseException 
     */
    public static Date string2UtilDate(String str) throws ParseException {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.parse(str);
    }

    /**
     * 将格式字符串转换为java.sql.Date日期类型
     *
     * @param date String
     * @param format String
     * @return java.sql.Date
     */
    public static java.sql.Date string2Date(String date, String format) {
        if (date == null || date.equals("")) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return new java.sql.Date(sdf.parse(date).getTime());
        } catch (ParseException ex) {
            logger.log(Level.WARNING, "{0}{1}", new Object[]{ex.getMessage(), ex});
            return null;
        }
    }
    //----------------------------------------------------------- 日期转换 E >>>
    
    public static void main(String[] args) {
		System.out.println(getDateToStr());
	}
}
