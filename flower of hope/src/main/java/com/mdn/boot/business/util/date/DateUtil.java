package com.danfan.boot.business.util.date;

import com.danfan.boot.framework.constant.SystemConstant;
import com.danfan.boot.framework.exception.BaseException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.IntFunction;

/**
 * DataUtil
 * 
 * @author MengDaNai
 * @version v1.0
 * @Date 2019年11月19日
 */
public class DateUtil {

	public static void main(String[] args) throws ParseException {
		// 获取当前时间->Date
		Date date = getDate();
		System.out.println(date);
		// 获取当前时间->String
		String dateTime = getDateTime();
		System.out.println(dateTime);
		// 获取当前时间->LocalDateTime
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		// String转Date
		Date stringToDate = stringToDate("2019-10-10 00:00:00");
		System.out.println(stringToDate);
		// String转LocalDateTime
		LocalDateTime stringToLocalDateTime = stringToLocalDateTime(getDateTime());
		System.out.println(stringToLocalDateTime);
		// Date转String
		String dateToString = dateToString(getDate());
		System.out.println(dateToString);
		// Date转LocalDateTime
		LocalDateTime dateToLocalDateTime = dateToLocalDateTime(getDate());
		System.out.println(dateToLocalDateTime);
		// LocalDateTime转String
		String localDateTimeToString = localDateTimeToString(LocalDateTime.now());
		System.out.println(localDateTimeToString);
		// LocalDateTime转Date
		Date localDateTimeToDate = localDateTimeToDate(LocalDateTime.now());
		System.out.println(localDateTimeToDate);
		// Date格式转换
		Date dateFormat = dateFormat(getDate(), YYYY_MM_DD_HH_MM_SS);
		System.out.println(dateFormat);
		// String格式转换
		String stringFormat = stringFormat(getDateTime(), YYYY_MM_DD_HH_MM_SS);
		System.out.println(stringFormat);
		// LocalDateTime格式转换
		LocalDateTime localDateTimeFormat = localDateTimeFormat(LocalDateTime.now(), YYYY_MM_DD_HH_MM_SS);
		System.out.println(localDateTimeFormat);
		// 获取小时数
		int hour = getHour(LocalDateTime.now());
		System.out.println(hour);
		// 获取分钟数
		int minute = getMinute(LocalDateTime.now());
		System.out.println(minute);
		// 获取日期
		int day = getDay(LocalDateTime.now());
		System.out.println(day);
		// 今天星期几
		int week = getWeek(LocalDateTime.now());
		System.out.println(week);
		// 现在是几月
		int month = getMonth(LocalDateTime.now());
		System.out.println(month);
		// 现在几几年
		int year = getYear(LocalDateTime.now());
		System.out.println(year);
		// 今天是今年的第多少天
		int dayOfYear = getDayOfYear(LocalDateTime.now());
		System.out.println(dayOfYear);
	}

	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 获取当前时间
	 * 
	 * @author MengDaNai
	 * @Date 2019年7月19日 下午5:40:50
	 * @return String
	 */
	public static String getDateTime() {
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
		return datetime.format(sdf);
	}

	/**
	 * 获取当前时间
	 * 
	 * @author MengDaNai
	 * @Date 2019年7月19日 下午5:41:00
	 * @param pattern 时间格式 yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String getDateTime(String pattern) {
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern(pattern);
		return datetime.format(sdf);
	}

	/**
	 * 获取当前时间
	 * 
	 * @author MengDaNai
	 * @Date 2019年7月19日 下午5:41:15
	 * @return Date
	 */
	public static Date getDate() {
		String dateTime = getDateTime();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
		LocalDateTime startTime = LocalDateTime.parse(dateTime, formatter);
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdtStart = startTime.atZone(zoneId);
		return Date.from(zdtStart.toInstant());
	}

	/**
	 * StringToDate
	 * 
	 * @author MengDaNai
	 * @Date 2019年7月19日 下午5:41:22
	 * @param dateTime
	 * @return Date
	 */
	public static Date stringToDate(String dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
		LocalDateTime startTime = LocalDateTime.parse(dateTime, formatter);
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdtStart = startTime.atZone(zoneId);
		return Date.from(zdtStart.toInstant());
	}

	/**
	 * 字符串转LocalDate
	 * 
	 * @Author SHISHI
	 * @Date 2019/10/9 11:39
	 * @param: date 时间字符串
	 * @Return
	 */
	public static LocalDate stringToLocalDate(String date) {
		return dateToLocalDate(stringToDate(date));
	}

	/**
	 * stringToLocalDateTime
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月19日 上午9:48:54
	 * @param dateTime
	 * @return LocalDateTime
	 */
	public static LocalDateTime stringToLocalDateTime(String dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
		return LocalDateTime.parse(dateTime, formatter);
	}

	/**
	 * dateToString
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月19日 上午9:55:47
	 * @param date
	 * @return String
	 */
	public static String dateToString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		return dateFormat.format(date);
	}

	/**
	 * dateToLocalDateTime
	 * 
	 * @Author SHISHI
	 * @Date 2019/7/18 11:43
	 * @param date
	 * @Return java.time.LocalDateTime
	 */
	public static LocalDateTime dateToLocalDateTime(Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		return LocalDateTime.ofInstant(instant, zone);
	}

	/**
	 * localDateTimeToString
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月19日 上午10:34:42
	 * @param localDateTime
	 * @return String
	 */
	public static String localDateTimeToString(LocalDateTime localDateTime) {
		return localDateTime.format(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));
	}

	/**
	 * localDateTimeToDate
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月19日 上午10:36:57
	 * @param localDateTime
	 * @return Date
	 */
	public static Date localDateTimeToDate(LocalDateTime localDateTime) {
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdtStart = localDateTime.atZone(zoneId);
		return Date.from(zdtStart.toInstant());
	}

	/**
	 * date转型
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月19日 上午11:04:12
	 * @param date    时间
	 * @param pattern 时间格式 yyyy-MM-dd HH:mm:ss
	 * @throws ParseException
	 * @return Date
	 */
	public static Date dateFormat(Date date, String pattern) throws ParseException {
		String dateToString = dateToString(date);
		return new SimpleDateFormat(pattern).parse(dateToString);
	}

	/**
	 * string转型
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月19日 上午11:07:09
	 * @param time    时间
	 * @param pattern 时间格式 yyyy-MM-dd HH:mm:ss
	 * @throws ParseException
	 * @return String
	 */
	public static String stringFormat(String time, String pattern) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateToString(dateFormat.parse(time));
	}

	/**
	 * localDateTime转型
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月19日 上午11:12:53
	 * @param localDateTime 时间
	 * @param pattern       时间格式 yyyy-MM-dd HH:mm:ss
	 * @throws ParseException
	 * @return LocalDateTime
	 */
	public static LocalDateTime localDateTimeFormat(LocalDateTime localDateTime, String pattern) throws ParseException {
		String localDateTimeToString = localDateTimeToString(localDateTime);
		return dateToLocalDateTime(new SimpleDateFormat(pattern).parse(localDateTimeToString));
	}

	/**
	 * 把Date转换为localDate
	 * 
	 * @Author SHISHI
	 * @Date 2019/7/18 11:41
	 * @param date
	 * @Return java.time.LocalDate
	 */
	public static LocalDate dateToLocalDate(Date date) {
		return dateToLocalDateTime(date).toLocalDate();
	}

	/**
	 * 获取分钟数
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月19日 下午2:16:35
	 * @param localDateTime
	 * @return int
	 */
	public static int getMinute(LocalDateTime localDateTime) {
		return localDateTime.getMinute();
	}

	/**
	 * 获取小时数
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月19日 上午11:40:44
	 * @param localDateTime
	 * @return int
	 */
	public static int getHour(LocalDateTime localDateTime) {
		return localDateTime.getHour();
	}

	/**
	 * 获取日期
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月19日 下午2:44:30
	 * @param localDateTime
	 * @return int
	 */
	public static int getDay(LocalDateTime localDateTime) {
		return localDateTime.getDayOfMonth();
	}

	/**
	 * 获取星期1-7
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月19日 下午2:54:08
	 * @param localDateTime
	 * @return int
	 */
	public static int getWeek(LocalDateTime localDateTime) {
		return localDateTime.getDayOfWeek().getValue();
	}

	/**
	 * 获取月份
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月19日 下午2:57:43
	 * @param localDateTime
	 * @return int
	 */
	public static int getMonth(LocalDateTime localDateTime) {
		return localDateTime.getMonth().getValue();
	}

	/**
	 * 获取年份
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月19日 下午3:01:02
	 * @param localDateTime
	 * @return int
	 */
	public static int getYear(LocalDateTime localDateTime) {
		return localDateTime.getYear();
	}

	/**
	 * 获取今天是今年的第多少天
	 * 
	 * @author MengDaNai
	 * @Date 2019年9月19日 下午3:12:40
	 * @param localDateTime
	 * @return int
	 */
	public static int getDayOfYear(LocalDateTime localDateTime) {
		return localDateTime.getDayOfYear();
	}

	/**
	 * 计算时间差
	 * 
	 * @Author SHISHI->MengDaNai
	 * @Date 2019/7/18 14:14
	 * @param oldTime 过去时间
	 * @param nowTime 当前时间
	 * @param type    要计算的时间差类型
	 * @Return long 技术按结果
	 */
	public static long differenceTime(Date oldTime, Date nowTime, TimeTypeEnum type) {
		LocalDateTime oldLocalDateTime = dateToLocalDateTime(oldTime);
		LocalDateTime nowLocalDateTime = dateToLocalDateTime(nowTime);
		switch (type) {
		case YEAR:
			return oldLocalDateTime.until(nowLocalDateTime, ChronoUnit.YEARS);
		case MONTH:
			return oldLocalDateTime.until(nowLocalDateTime, ChronoUnit.MONTHS);
		case DAY:
			return Duration.between(oldLocalDateTime, nowLocalDateTime).toDays();
		case HOUR:
			return Duration.between(oldLocalDateTime, nowLocalDateTime).toHours();
		case MINUTE:
			return Duration.between(oldLocalDateTime, nowLocalDateTime).toMinutes();
		case SECOND:
			return Duration.between(oldLocalDateTime, nowLocalDateTime).toMillis() / 1000;
		case MILLI:
			return Duration.between(oldLocalDateTime, nowLocalDateTime).toMillis();
		default:
			throw new BaseException(SystemConstant.OPERATION_FAILED_CODE);
		}
	}

	/**
	 * 比较日期大小
	 * <p>
	 * time1 = "2018-01-01 00:00:00" time2 = "2019-01-01 00:00:00" return true
	 * 时间1小于时间2
	 * </p>
	 * 
	 * @Author SHISHI
	 * @Date 2019/7/18 14:22
	 * @param time1
	 * @param time2
	 * @Return boolean
	 */
	public static boolean compareTime(Date time1, Date time2) {
		LocalDateTime localDateTime1 = dateToLocalDateTime(time1);
		LocalDateTime localDateTime2 = dateToLocalDateTime(time2);
		return localDateTime1.isBefore(localDateTime2);
	}

	/**
	 * 对时间加减(不支持乘，除，毫秒级)
	 * 
	 * @Author SHISHI->MengDaNai
	 * @Date 2019/7/18 14:55
	 * @param time              时间
	 * @param num               计算的数量
	 * @param timeTypeEnum      计算的时间类型
	 * @param calculateTypeEnum 计算的类型
	 * @Return java.util.Date
	 */
	public static Date calculatingTime(Date time, CalculateTypeEnum calculateTypeEnum, int num,
			TimeTypeEnum timeTypeEnum) {
		LocalDateTime localDateTime = dateToLocalDateTime(time);
		switch (timeTypeEnum) {
		case YEAR:
			return calculation(num, calculateTypeEnum, localDateTime::plusYears, localDateTime::minusYears);
		case MONTH:
			return calculation(num, calculateTypeEnum, localDateTime::plusMonths, localDateTime::minusMonths);
		case DAY:
			return calculation(num, calculateTypeEnum, localDateTime::plusDays, localDateTime::minusDays);
		case HOUR:
			return calculation(num, calculateTypeEnum, localDateTime::plusHours, localDateTime::minusHours);
		case MINUTE:
			return calculation(num, calculateTypeEnum, localDateTime::plusMinutes, localDateTime::minusMinutes);
		case SECOND:
			return calculation(num, calculateTypeEnum, localDateTime::plusSeconds, localDateTime::minusSeconds);
		default:
			throw new BaseException(SystemConstant.OPERATION_FAILED_CODE);
		}
	}

	/**
	 * 执行加还是减
	 * 
	 * @Author SHISHI
	 * @Date 2019/7/18 15:15
	 * @param num           计算数量
	 * @param calculateType 计算类型
	 * @param addDeal       加方法
	 * @param subDeal       减方法
	 * @Return java.time.LocalDateTime
	 */
	private static Date calculation(int num, CalculateTypeEnum calculateTypeEnum, IntFunction<LocalDateTime> addDeal,
			IntFunction<LocalDateTime> subDeal) {
		ZoneId zoneId = ZoneId.systemDefault();
		switch (calculateTypeEnum) {
		case ADD:
			return Date.from(addDeal.apply(num).atZone(zoneId).toInstant());
		case SUBTRACT:
			return Date.from(subDeal.apply(num).atZone(zoneId).toInstant());
		default:
			throw new BaseException(SystemConstant.OPERATION_FAILED_CODE);
		}
	}

}
