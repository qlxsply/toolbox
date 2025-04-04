package com.avalon.tools.util;

import java.util.Arrays;

/**
 * author: avalon
 * date: 2025-03-16
 */
public class Base62Util {

    /**
     * 长整型数字转 Base62 字符串
     *
     * @param val 长整型数值
     * @return Base62 字符串
     */
    public static String toStr(long val) {
        return toStr(val, 0);
    }

    /**
     * 长整型数字转 Base62 字符串（带最小长度）
     *
     * @param val      长整型数值
     * @param minScale 最小字符串长度，不足时左侧补0
     * @return Base62 字符串
     */
    public static String toStr(long val, int minScale) {
        return toBase62(val, minScale);
    }

    /**
     * Base62 字符串转长整型数字
     *
     * @param str Base62 字符串
     * @return 长整型数值
     */
    public static long longFromStr(String str) {
        return parseBase62(str);
    }

    /**
     * 整型数字转 Base62 字符串
     *
     * @param val 整型数值
     * @return Base62 字符串
     */
    public static String toStr(int val) {
        return toStr((long) val, 0);
    }

    /**
     * 整型数字转 Base62 字符串（带最小长度）
     *
     * @param val      整型数值
     * @param minScale 最小字符串长度，不足时左侧补0
     * @return Base62 字符串
     */
    public static String toStr(int val, int minScale) {
        return toStr((long) val, minScale);
    }

    /**
     * Base62 字符串转整型数字
     *
     * @param str Base62 字符串
     * @return 整型数值
     */
    public static int intFromStr(String str) {
        long result = parseBase62(str);
        if (result > Integer.MAX_VALUE) {
            throw new NumberFormatException("Exceeds Integer.MAX_VALUE");
        }
        return (int) result;
    }

    // Base62 字符集（字典序严格递增）
    private static final char[] BASE62_CODE = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'};

    // 快速索引表（ASCII码对应下标，-1表示非法字符）
    private static final int[] BASE62_INDEX = new int[128];

    static {
        Arrays.fill(BASE62_INDEX, -1);
        for (int i = 0; i < BASE62_CODE.length; i++) {
            BASE62_INDEX[BASE62_CODE[i]] = i;
        }
    }

    /**
     * 编码核心逻辑
     */
    private static String toBase62(long val, int minScale) {
        if (val == 0) {
            return minScale > 0 ? repeat(minScale) : "0";
        }
        if (val < 0) {
            throw new IllegalArgumentException("Negative numbers not supported");
        }

        // 计算所需字符长度
        int length = calculateLength(val, minScale);
        char[] buf = new char[length];
        int ptr = length - 1;

        // 逐位填充字符
        while (val != 0) {
            int remainder = (int) (val % 62);
            buf[ptr--] = BASE62_CODE[remainder];
            val /= 62;
        }

        // 左侧补0
        while (ptr >= 0) {
            buf[ptr--] = '0';
        }

        return new String(buf);
    }

    /**
     * 解码核心逻辑
     */
    private static long parseBase62(String str) {
        long result = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 128 || BASE62_INDEX[c] == -1) {
                throw new IllegalArgumentException("Invalid Base62 character: " + c);
            }
            result = result * 62 + BASE62_INDEX[c];
            if (result < 0) {
                throw new ArithmeticException("Overflow: value exceeds Long.MAX_VALUE");
            }
        }
        return result;
    }

    /**
     * 计算所需字符串长度
     */
    private static int calculateLength(long val, int minScale) {
        int length = 0;
        long temp = val;
        do {
            temp /= 62;
            length++;
        } while (temp != 0);
        return Math.max(length, minScale);
    }

    /**
     * 生成重复字符字符串
     */
    private static String repeat(int count) {
        char[] arr = new char[count];
        Arrays.fill(arr, '0');
        return new String(arr);
    }

}