package com.piyoro.personalweb.common.util;

import com.piyoro.personalweb.common.consts.CommonConst;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String getCurrentDate(String pattern) {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getCurrentTimeStamp() {
        return getCurrentDate(CommonConst.DATE_PATERRN_YYYYMMDDHHMMSSSSS);
    }

    public static String dateFormat(String date, String format) {
        return DateUtil.dateFormat(date, CommonConst.DATE_PATERRN_YYYYMMDDHHMMSSSSS, CommonConst.DATE_FORMAT_YYYYMMDDHHMMSSSSS);
    }

    public static String dateFormat(String date, String pattern, String format) {
        return DateUtil.strToLdt(date, pattern).format(DateTimeFormatter.ofPattern(format));
    }

    public static String dateFormat(LocalDateTime ldt, String format) {
        return ldt.format(DateTimeFormatter.ofPattern(format));
    }

    public static LocalDateTime strToLdt(String date, String pattern) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    public static String addDay(String date, int interval, String pattern, String format) {
        return DateUtil.addDay(DateUtil.strToLdt(date, pattern), interval, format);
    }

    public static String addDay(LocalDateTime ldt, int interval, String format) {
        if(interval > 0) {
            ldt = ldt.plusDays(interval);
        } else if(interval < 0) {
            ldt = ldt.minusDays(interval);
        }
        return DateUtil.dateFormat(ldt, format);
    }

    /**
     * 현재일시 가 파라미터 시작~종료 사이 여부 반환
     * @param startDt 시작일시 YYYYMMDDHHMMSS
     * @param endDt 종료일 YYYYMMDDHHMMSS
     * @return 0: 사이 음수: 이전 양수: 이후
     */
    public static int between(String startDt, String endDt) {
        LocalDateTime startDate = LocalDateTime.parse(startDt, DateTimeFormatter.ofPattern(CommonConst.DATE_PATERRN_YYYYMMDDHHMMSS));
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.parse(endDt, DateTimeFormatter.ofPattern(CommonConst.DATE_PATERRN_YYYYMMDDHHMMSS));
        int r = now.compareTo(startDate);
        if(r < 0) {
            return r;
        }
        r = now.compareTo(endDate);
        if(r > 0) {
            return r;
        }
        return 0;
    }

    /**
     * 현재일자와 비교
     * @param compareDt
     * @return -1 : 이전 , 0 : 동일 , 1 : 이후
     */
    public static int compare(String compareDt) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.parse(compareDt, DateTimeFormatter.ofPattern(CommonConst.DATE_PATERRN_YYYYMMDDHHMMSS));
        int r = now.compareTo(endDate);
        return r;
    }

    public static int compare(String startDt, String endDt) {
        LocalDateTime startDate = LocalDateTime.parse(startDt, DateTimeFormatter.ofPattern(CommonConst.DATE_PATERRN_YYYYMMDDHHMMSS));
        LocalDateTime endDate = LocalDateTime.parse(endDt, DateTimeFormatter.ofPattern(CommonConst.DATE_PATERRN_YYYYMMDDHHMMSS));
        int r = startDate.compareTo(endDate);
        return r;
    }

}
