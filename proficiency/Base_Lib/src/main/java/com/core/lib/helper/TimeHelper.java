package com.core.lib.helper;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * TimeHelper:时间帮助类
 * 
 * @author linhuan 2015年7月15日上午9:18:06
 */
public class TimeHelper {
	
	public static final String YYYYMMDD = "yyyy年MM月dd日";
	public static final String MMDD = "MM-dd";
	public static final String YYYYMMDDTWO = "yyyy-MM-dd";
	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String HHMM = "HH:mm";
	public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
	public static final String YYYYMMDDHHMMWRITING = "yyyy年MM月dd日 HH:mm";
	public static final String YYYY = "yyyy";
	public static final String MONTY = "MM";
	public static final String DD = "dd";
	public static final String HH = "HH";
	public static final String MM = "mm";

	/**
	 * getTimestamp:获取时间戳
	 * 
	 * @return
	 * 
	 * @author linhuan 2015年7月15日上午9:18:47
	 */
	public static long getTimestamp() {
		return System.currentTimeMillis() / 1000;
	}

	/**
	 * date转GMT
	 *
	 * @param date
	 * @return
     */
	public static String dateToGMT(Date date) {
		Locale aLocale = Locale.US;
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", new DateFormatSymbols(aLocale));
		fmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		return fmt.format(date);
	}

	/**
	 * date转time
	 *
	 * @param date
	 * @return
	 */
	public static String dateToTime(Date date, String timeFormat) {
		SimpleDateFormat format = new SimpleDateFormat(timeFormat);
		return format.format(date);
	}
	
	/**
	 * timeToData:时间戳转时间字符串
	 * 
	 * @param timeFormat
	 * @return
	 * 
	 * @author linhuan 2015年7月16日上午11:46:21
	 */
	public static String timeToData(String timeFormat) {
		SimpleDateFormat format = new SimpleDateFormat(timeFormat);
		return format.format(new Date(System.currentTimeMillis()));
	}
	
	/**
	 * timeToData:时间戳转时间字符串
	 * 
	 * @param time
	 * @param timeFormat
	 * @return
	 * 
	 * @author linhuan 2015年7月16日上午11:46:21
	 */
	public static String timeToData(long time, String timeFormat) {
		SimpleDateFormat format = new SimpleDateFormat(timeFormat);
		return format.format(new Date(time * 1000L));
	}
	
	/**
	 * timeToData:时间戳转时间字符串
	 * 
	 * @param time
	 * @param timeFormat
	 * @return
	 * 
	 * @author linhuan 2015年7月16日上午11:46:21
	 */
	public static int timeToInt(long time, String timeFormat) {
		SimpleDateFormat format =  new SimpleDateFormat(timeFormat);
		return Integer.valueOf(format.format(new Date(time * 1000L)));
	}
	
	/**
	 * getTime:字符串转时间戳
	 * 
	 * @param timeFormat
	 * @return
	 * 
	 * @author linhuan 2015-11-26下午3:25:11
	 */
	public static String getTime(String userTime, String timeFormat) {  
		String re_time = "";  
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);  
			Date d = sdf.parse(userTime);
			long l = d.getTime();  
			String str = String.valueOf(l);  
			re_time = str.substring(0, 10);  
		} catch (ParseException e) {}  
		return re_time;  
	}

	/**
	 * getTime:字符串转时间戳
	 *
	 * @param timeFormat
	 * @return
	 *
	 * @author linhuan 2015-11-26下午3:25:11
	 */
	public static long getTimeLong(String userTime, String timeFormat) {
		long l = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
			Date d = sdf.parse(userTime);
			l = d.getTime() / 1000;
		} catch (ParseException e) {}
		return l;
	}

	/**
	 * 字符串转换成日期
	 * @param str
	 * @return date
	 */
	public static Date strToDate(String str, String timeForma) {

		SimpleDateFormat format = new SimpleDateFormat(timeForma);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取当天0点时间戳
	 *
	 * @return
     */
	public static int getTimesmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return (int) (cal.getTimeInMillis() / 1000);
	}

	/**
	 * 获取当天0点时间戳
	 *
	 * @return
	 */
	public static int getTimesThisYear() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return (int) (cal.getTimeInMillis() / 1000);
	}

	/**
	 * 获取周几
	 *
	 * @param date
	 * @return
     */
	public static String getWeek(Date date){
		String[] weeks = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if(week_index<0){
			week_index = 0;
		}
		return weeks[week_index];
	}

	public static String getDatePoor(Date startDate, Date endDate) {

		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - startDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		// long sec = diff % nd % nh % nm / ns;

		if (0 < day) {
			if (0 < hour) {
				return day + "d" + hour + "h";
			} else {
				return day + "d";
			}
		} else {
			if (0 < hour) {
				if (0 < min) {
					return hour + "h" + min + "m";
				} else {
					return hour + "h";
				}
			} else {
				return min + "m";
			}
		}
	}

	public static String timeAddMoreDay(String time, int day, String timeFormat) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);

			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(time));
			cd.add(Calendar.DATE, day);						// 增加一天
			//cd.add(Calendar.MONTH, n);					// 增加一个月

			return sdf.format(cd.getTime());

		} catch (Exception e) {
			return time;
		}
	}

	public static boolean spitCurrent(Date endDate, int spitDay) {

		long nd = 1000 * 24 * 60 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - (new Date()).getTime();
		// 计算差多少天
		long day = diff / nd;

		return spitDay > day;
	}

	public static boolean spitCurrent(Date startDate, String endDate, String timeFormat) {
		return spitCurrent(startDate, strToDate(endDate, timeFormat));
	}

	public static boolean spitCurrent(Date startDate, Date endDate) {
		return 0 < (endDate.getTime() - startDate.getTime());
	}

}
