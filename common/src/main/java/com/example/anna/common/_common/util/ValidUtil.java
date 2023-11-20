package com.example.anna.common._common.util;

import org.springframework.util.StringUtils;

public class ValidUtil {

    private static final String NUMBER_ALPHABET_PATTERN = "^[a-zA-Z\\d]+$";
    private static final String NUMERIC_PATTERN = "^[\\+\\-]{0,1}[\\d]+$";
    private static final String FLOAT_PATTERN = "^[\\+\\-]{0,1}[\\d]+[\\.][0-9]+$";
    private static final String MAIL_PATTERN = "^[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+$";

    private ValidUtil() {
    }

    /**
     * 값이 영문 알파벳, 숫자가 아닌 경우
     */
    public static boolean isAlphaNumber(String object) {
        return isMatched(object, NUMBER_ALPHABET_PATTERN);
    }


    /**
     * * 값이 숫자가 아닌 경우
     */
    public static boolean isNumeric(String object) {
        return isMatched(object, NUMERIC_PATTERN);
    }


    /**
     * 값이 float, double이 아닌 경우
     */
    public static boolean isFloat(String object) {
        return isMatched(object, FLOAT_PATTERN);
    }


    public static boolean isMail(String object) {
        return isMatched(object, MAIL_PATTERN);
    }

    /**
     * 패턴 매치, 해당 패턴과 일치 하지 않는 경우
     *
     * @param object
     */

    private static boolean isMatched(String object, String pattern) {
        return StringUtils.hasText(object) && object.matches(pattern);

    }


}
