package com.example.blog.utils;

/**
 * @Author mayn
 * @Date 2019/9/12 15:15
 */
public class SubStringUtils {
    /**
     * 截取起始和终止之间的数据，不包含起始和终止
     * @param str
     * @param strStart
     * @param strEnd
     * @return
     */
    public static String subString(String str, String strStart, String strEnd) {

        /* 找出指定的2个字符在 该字符串里面的 位置 */
        int strStartIndex = str.indexOf(strStart) + strStart.length();
        int strEndIndex = str.indexOf(strEnd);

        /* index为负数 即表示该字符串中没有该字符 */
        if (strStartIndex < 0) {
            return "字符串 :" + str + "中不存在 " + strStart + ", 无法截取目标字符串";
        }
        if (strEndIndex < 0) {
            return "字符串 :" + str + "中不存在 " + strEnd + ", 无法截取目标字符串";
        }
        /* 开始截取 */
        String result = str.substring(strStartIndex, strEndIndex);
        return result;
    }
}
