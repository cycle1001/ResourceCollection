package com.allen.res.tools.compresss;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 数据压缩工具类
 */
public class CompressUtils {

    public static String compresss(String str){
        if (str == null || str.length() == 0) {
            return str;
        }
        String outStr = "";
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes());
            gzip.close();
            outStr = Base64Utils.encode(out.toByteArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return outStr;
    }

    public static String decompresss(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(Base64Utils.decode(str)));
            byte[] buffer = new byte[256];
            int n;
            while ((n = gis.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return new String(out.toByteArray());
    }

    /**
     * @param input 需要压缩的字符串
     * @return 压缩后的字符串
     * @throws IOException IO
     */
    public static String compress(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPOutputStream gzipOs = new GZIPOutputStream(out);
            gzipOs.write(input.getBytes());
            gzipOs.close();
            return out.toString("ISO-8859-1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
    /**
     * @param zippedStr 压缩后的字符串
     * @return 解压缩后的
     * @throws IOException IO
     */
    public static String uncompress(String zippedStr) {
        if (zippedStr == null || zippedStr.length() == 0) {
            return zippedStr;
        }
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayInputStream in = new ByteArrayInputStream(zippedStr
                    .getBytes("ISO-8859-1"));
            GZIPInputStream gzipIs = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = gzipIs.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            // toString()使用平台默认编码，也可以显式的指定如toString("GBK")
            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zippedStr;
    }
}
