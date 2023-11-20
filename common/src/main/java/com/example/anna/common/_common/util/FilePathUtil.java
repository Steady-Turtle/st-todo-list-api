package com.example.anna.common._common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FilePathUtil {

    private static final String SLASH_WINDOW = "/";
    private static final String SLASH_UNIX = "\\";
    private static final String DOUBLE_SLASH = "//";
    private static final String ENCODED_AMP = "&amp;";
    private static final String RECOVERED_AMP = "&";
    private static final String ENCODED_SINGLEQUOTE = "&#39;";
    private static final String RECOVERED_SINGLEQUOTE = "'";

    private FilePathUtil() {
    }

    /**
     * 버트로 만든 파일 만들어지는 경로
     *
     * @param baseDir
     * @param schoolCode
     * @param enterYear
     * @param recruitPartSeq
     * @param applNo
     * @return
     */
    public static String getMakeDirectoryFullPath(String baseDir,
                                                  String schoolCode, String enterYear, Integer recruitPartSeq,
                                                  Long applNo) {
        return new StringBuilder()
                .append(baseDir).append("/")
                .append(schoolCode).append("/")
                .append(enterYear).append("/")
                .append(recruitPartSeq).append("/")
                .append(String.valueOf(applNo))
                .toString();
    }

    public static String removeSeparators(String rawOriginalFileName) {
        int index = rawOriginalFileName.lastIndexOf(SLASH_UNIX);
        String tmpFileName = rawOriginalFileName.substring(index + 1, rawOriginalFileName.length());

        index = tmpFileName.lastIndexOf(SLASH_WINDOW);

        return tmpFileName.substring(index + 1, tmpFileName.length());

    }

    /**
     * localdatetime 을 시분초 String 으로 반환
     * 파일경로에 자주 사용되는 패턴
     *
     * @return
     */
    public static String getUploadDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    public static String recoverAmpersand(String path) {
        return path.replace(ENCODED_AMP, RECOVERED_AMP)
                .replace(ENCODED_AMP, RECOVERED_AMP)
                .replace(ENCODED_AMP, RECOVERED_AMP);
    }

    public static String recoverSingleQuote(String path) {
        return path.replace(ENCODED_SINGLEQUOTE, RECOVERED_SINGLEQUOTE);
    }

}
