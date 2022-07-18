package com.piyoro.personalweb.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;

public class StringUtil {

    public static boolean isNull(String src) {
        return src == null;
    }

    public static boolean isNotNull(String src) {
        return src != null;
    }

    /**
     * 문자열 이 null 이거나 빈문자열 여부 반환
     * @param src 
     * @param check 원본 문자열 trim 여부
     * @return
     */
    public static boolean isEmpty(String src, boolean check) {
        if(check) return StringUtils.isBlank(src);
        else return StringUtils.isEmpty(src);
    }

    /**
     * src 가 null 이어도 NullPointerException이 발생하지 않는다.
     * @param src
     * @param target
     * @return
     */
    public static boolean equals(String src, String target) {
        return StringUtils.equals(src, target);
    }
    /**
     * @param src 원본 문자열
     * @return
     */
    public static String defStr(String src) {
        if(isNull(src)) return "";
        return src;
    }

    /**
     * @param src 원본 문자열
     * @param def null 일 경우 디폴트 문자열
     * @return
     */
    public static String defStr(String src, String def) {
        return defStr(src, def, false);
    }

    /**
     * @param src 원본 문자열
     * @param def 디폴트 문자열
     * @param check true : 빈문자열 포함 false : 빈문자열 미포함
     * @return
     */
    public static String defStr(String src, String def, boolean check) {
        return defStr(src, def, check, false);
    }

    /**
     * @param src 원본 문자열
     * @param def 디폴트 문자열
     * @param check true : 빈문자열 포함 false : 빈문자열 미포함
     * @param trim  빈문자열 포함시 trim 여부
     * @return
     */
    public static String defStr(String src, String def, boolean check, boolean trim) {
        if(check) {
            if(trim) return StringUtils.defaultIfBlank(src, def);
            else return StringUtils.defaultIfEmpty(src, def);
        }
        else return StringUtils.defaultString(src, def);
    }

    public static String rightPadding(String src, int size, String pad) {
        return StringUtils.rightPad(src, size, pad);
    }

    public static String leftPadding(String src, int size, String pad) {
        return StringUtils.leftPad(src, size, pad);
    }

    /**
     * @param size
     * @param padChar
     * @return String
     */
    public static String getFixedRandomStringId(int size, String padChar) {
        int randomNum = (int) Math.round(1000 * Math.random());
        return leftPadding(String.valueOf(randomNum), size, padChar);
    }

    public static String decimalFormat(double d) {
        DecimalFormat formatter = new DecimalFormat("###,###.##");
        return formatter.format(d);
    }

    public static String decimalFormat(int i) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(i);
    }
}
