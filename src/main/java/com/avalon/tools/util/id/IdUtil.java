package com.avalon.tools.util.id;

import com.avalon.tools.util.Base32Util;
import com.avalon.tools.util.Base62Util;

import java.security.SecureRandom;

public class IdUtil {

    /**
     * 生成16个字符的自定义32进制随机ID（80bit，无序）
     *
     * @return ID
     */
    public static String random32() {
        //生成安全随机数，长度为10的字节数组
        SecureRandom sr = IdUtil.Holder.numberGenerator;
        byte[] data = new byte[10];
        sr.nextBytes(data);
        //80比特已经超过long，高40位和低40位分开存储。初始值为1，确保生成字符串长度为8
        long msb = 1L;
        long lsb = 1L;
        for (int i = 0; i < 5; i++) {
            msb = (msb << 8) | (data[i] & 0xff);
        }
        for (int i = 5; i < 10; i++) {
            lsb = (lsb << 8) | (data[i] & 0xff);
        }
        //转32进制字符串，移除初始数值
        return Base32Util.toLowStr(msb).substring(1) + Base32Util.toLowStr(lsb).substring(1);
    }

    /**
     * 生成12个字符的自定义62进制【时间戳+随机】ID
     *
     * @return ID
     */
    public static String tsRandom62() {
        var ts = System.currentTimeMillis() - OFFSET_TS;
        var prefix = Base62Util.toStr(ts, 7);
        var sr = IdUtil.Holder.numberGenerator;
        //62^5=916132832
        var randomInt = sr.nextInt(916_132_832) + 1;
        var suffix = Base62Util.toStr(randomInt, 5);
        return prefix + suffix;
    }

    /**
     * 偏移时间戳
     */
    private static final long OFFSET_TS = 1735660800000L;

    /**
     * Holder 类是一个静态内部类，它的初始化是线程安全的。
     * 当类加载时，Holder 内部的 SecureRandom 实例就会被初始化，并且在整个应用程序生命周期中都保持不变。
     * 通过 SecureRandom 实例的唯一性确保所有的随机数都使用相同的种子生成，从而提高了随机数的质量和安全性。
     */
    private static class Holder {
        static final SecureRandom numberGenerator = new SecureRandom();
    }

}
