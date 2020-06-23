package com.cqx.brooklyn.prometheusdingtalk.parser;

import org.springframework.util.StringUtils;

public class UltramanParser {

    private static final String expPrefix = "<exp";
    private static final String expSuffix = ">";


    public static String preParse(String value) {
        StringBuilder sb = new StringBuilder(value);
        int index = sb.indexOf(expPrefix);
        if (index == -1) {
            return value;
        }
        while (index != -1) {
            int endIndex = findEndIndex(sb, index);
            if (endIndex != -1) {

            }
        }
        return value;
    }

    /**
     * 查找截止符号
     *
     * @param buf
     * @param startIndex
     * @return
     */
    private static int findEndIndex(CharSequence buf, int startIndex) {
        int index = startIndex + expPrefix.length();
        int withinNestedPlaceholder = 0;
        while (index < buf.length()) {
            if (StringUtils.substringMatch(buf, startIndex, expSuffix)) {
                if (withinNestedPlaceholder > 0) {
                    withinNestedPlaceholder--;
                    index = index + expSuffix.length();
                } else {
                    return index;
                }
            } else if (StringUtils.substringMatch(buf, startIndex, expPrefix)) {
                withinNestedPlaceholder++;
                index = index + expPrefix.length();
            } else {
                index++;
            }
        }
        return index;
    }


}
