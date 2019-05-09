package com.dhxh.guns.modular.dhxh.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class SignUtil {
    public static final Log logger = LogFactory.getLog(SignUtil.class);

    public static String sign(String data,String id,String key) {
        if (data == null || data.isEmpty()) {
            return null;
        }
//        StringBuilder buf = new StringBuilder();
//        TreeMap<String, Object> map = new TreeMap<String, Object>(data);
//
//        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, Object> entry = it.next();
//            Object k = entry.getKey();
//            if ("class".equals(k) || "key".equals(k) || "sign".equals(k)) {
//                continue;
//            }
//            Object v = entry.getValue(); //  非空
//            if (v == null || "".equals(v.toString())) {
//                continue;
//            }
//            buf.append(k);
//            buf.append("=");
//            buf.append(v);
//            buf.append("&");
//        }
//        buf.append(id);
//        buf.append("&" + key);
//        logger.debug(buf.toString());
        String sign ="";
        try {
            sign =  Base64.getEncoder().encodeToString(EncryptUtil.md5(data+"&"+id+"&"+key).getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sign;
    }
}
