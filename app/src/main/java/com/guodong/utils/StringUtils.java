package com.guodong.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 * Created by LSQ108 on 2017/10/29.
 */

public class StringUtils {
    /**
     * 检查电话号码输入的合法性是否是电话号码
     *
     * @param phone 电话号码
     * @return
     */
    public static boolean checkPhoneNumber(String phone) {
        //        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        if(phone==null||"".equals(phone)) return false;
        Pattern p = Pattern.compile("[1][34578]\\d{9}");

        Matcher m = p.matcher(phone);

        return m.matches();
    }

    /**
     * 检查字符串是否为NULL
     *
     * @param data
     * @return
     */
    public static boolean checkNullString(String data) {
        if (data == null || "".equals(data))
            return true;
        return false;
    }

    /**
     * 判断给定的字符串是否为null或者是空的
     *
     * @param string 给定的字符串
     */
    public static boolean isEmpty(String string) {
        return string == null || "".equals(string.trim());
    }

    public static boolean isEmpty(CharSequence str) {

        return (str == null || str.length() == 0);
    }

    /**
     * 将二进制转化为16进制字符串
     *
     * @param b 二进制字节数组
     * @return String
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    public static int stringToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception ex) {
        }
        return 0;
    }

    /**
     * Convert hex string to byte[]
     *
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


}

