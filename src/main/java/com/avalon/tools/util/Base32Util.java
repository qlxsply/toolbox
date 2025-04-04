package com.avalon.tools.util;

public class Base32Util {

    /**
     * 长整型数字转小写32进制字符串
     *
     * @param val 长整形数值
     * @return 小写32进制字符串
     */
    public static String toLowStr(long val) {
        return toStr(val, LOWERCASE_CODE, 0);
    }

    /**
     * 长整型数字转小写32进制字符串
     *
     * @param val      长整形数值
     * @param minScale 最小字符串长度，如果实际长度不足，则在前面填充0
     * @return 小写32进制字符串
     */
    public static String toLowStr(long val, int minScale) {
        return toStr(val, LOWERCASE_CODE, minScale);
    }

    /**
     * 小写32进制字符串转长整型数字
     *
     * @param str 小写32进制字符串
     * @return 长整形数值
     */
    public static long longFromLowStr(String str) {
        return longFromStr(str, LOWERCASE_INDEX);
    }

    /**
     * 长整型数字转大写32进制字符串
     *
     * @param val 长整形数值
     * @return 大写32进制字符串
     */
    public static String toUpStr(long val) {
        return toStr(val, UPPERCASE_CODE, 0);
    }

    /**
     * 长整型数字转大写32进制字符串
     *
     * @param val      长整形数值
     * @param minScale 最小字符串长度，如果实际长度不足，则在前面填充0
     * @return 大写32进制字符串
     */
    public static String toUpStr(long val, int minScale) {
        return toStr(val, UPPERCASE_CODE, minScale);
    }

    /**
     * 大写32进制字符串转长整型数字
     *
     * @param str 大写32进制字符串
     * @return 长整形数值
     */
    public static long longFromUpStr(String str) {
        return longFromStr(str, UPPERCASE_INDEX);
    }

    /**
     * 整型数字转小写32进制字符串
     *
     * @param val 整形数值
     * @return 小写32进制字符串
     */
    public static String toLowStr(int val) {
        return toStr(val, LOWERCASE_CODE, 0);
    }

    /**
     * 整型数字转小写32进制字符串
     *
     * @param val      整形数值
     * @param minScale 最小字符串长度，如果实际长度不足，则在前面填充0
     * @return 小写32进制字符串
     */
    public static String toLowStr(int val, int minScale) {
        return toStr(val, LOWERCASE_CODE, minScale);
    }

    /**
     * 小写32进制字符串转整型数字
     *
     * @param str 小写32进制字符串
     * @return 整形数值
     */
    public static int intFromLowStr(String str) {
        return intFromStr(str, LOWERCASE_INDEX);
    }

    /**
     * 整型数字转大写32进制字符串
     *
     * @param val 整形数值
     * @return 大写32进制字符串
     */
    public static String toUpStr(int val) {
        return toStr(val, UPPERCASE_CODE, 0);
    }

    /**
     * 整型数字转大写32进制字符串
     *
     * @param val      整形数值
     * @param minScale 最小字符串长度，如果实际长度不足，则在前面填充0
     * @return 大写32进制字符串
     */
    public static String toUpStr(int val, int minScale) {
        return toStr(val, UPPERCASE_CODE, minScale);
    }

    /**
     * 大写32进制字符串转整型数字
     *
     * @param str 大写32进制字符串
     * @return 整形数值
     */
    public static int intFromUpStr(String str) {
        return intFromStr(str, UPPERCASE_INDEX);
    }

    /**
     * 32个小写自定义字符元素，移除了4个容易和数字混淆的字母 g l o q
     */
    private final static char[] LOWERCASE_CODE = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f', 'h', 'i', 'j', 'k', 'm', 'n', 'p', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /**
     * 32个大写自定义字符元素，移除了4个容易和数字混淆的字母 I O U V
     */
    private final static char[] UPPERCASE_CODE = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'W', 'X', 'Y', 'Z'};

    /**
     * 小写字符索引数组，用于快速查找字符索引
     */
    private final static int[] LOWERCASE_INDEX = new int[128];

    /**
     * 大写字符索引数组，用于快速查找字符索引
     */
    private final static int[] UPPERCASE_INDEX = new int[128];

    // 初始化字符索引数组
    static {
        for (int i = 0; i < LOWERCASE_CODE.length; i++) {
            LOWERCASE_INDEX[LOWERCASE_CODE[i]] = i;
        }
        for (int i = 0; i < UPPERCASE_CODE.length; i++) {
            UPPERCASE_INDEX[UPPERCASE_CODE[i]] = i;
        }
    }

    /**
     * 转换长整型数字至自定义32进制字符串，如果有需要可以在字符串前面填充0
     *
     * @param val      输入长整型数字
     * @param code     自定义字符串，不是标准32进制字符顺序
     * @param minScale 返回字符串的最小长度
     * @return 自定义32进制字符串
     */
    private static String toStr(long val, char[] code, int minScale) {
        int mag = Long.SIZE - Long.numberOfLeadingZeros(val);
        int cl = Math.max(((mag + 4) / 5), minScale);
        char[] buf = new char[cl];
        int mask = (1 << 5) - 1;
        do {
            buf[--cl] = code[((int) val) & mask];
            val >>>= 5;
        } while (val != 0 && cl > 0);
        while (cl > 0) {
            buf[--cl] = code[0];
        }
        return new String(buf);
    }

    /**
     * 转换整型数字至自定义32进制字符串，如果有需要可以在字符串前面填充0
     *
     * @param val      输入整型数字
     * @param code     自定义字符串，不是标准32进制字符顺序
     * @param minScale 返回字符串的最小长度
     * @return 自定义32进制字符串
     */
    private static String toStr(int val, char[] code, int minScale) {
        int mag = Integer.SIZE - Integer.numberOfLeadingZeros(val);
        int cl = Math.max(((mag + 4) / 5), minScale);
        char[] buf = new char[cl];
        int mask = (1 << 5) - 1;
        do {
            buf[--cl] = code[val & mask];
            val >>>= 5;
        } while (val != 0 && cl > 0);
        while (cl > 0) {
            buf[--cl] = code[0];
        }
        return new String(buf);
    }

    /**
     * 将自定义32进制字符串转换为长整型数字
     *
     * @param str   自定义32进制字符串
     * @param index 字符索引数组
     * @return 长整型数字
     */
    private static long longFromStr(String str, int[] index) {
        long result = 0;
        for (char c : str.toCharArray()) {
            result = (result << 5) | index[c];
        }
        return result;
    }

    /**
     * 将自定义32进制字符串转换为整型数字
     *
     * @param str   自定义32进制字符串
     * @param index 字符索引数组
     * @return 整型数字
     */
    private static int intFromStr(String str, int[] index) {
        int result = 0;
        for (char c : str.toCharArray()) {
            result = (result << 5) | index[c];
        }
        return result;
    }

}
