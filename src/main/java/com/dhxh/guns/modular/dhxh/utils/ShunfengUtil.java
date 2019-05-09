package com.dhxh.guns.modular.dhxh.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ShunfengUtil {
    public static final Log logger = LogFactory.getLog(ShunfengUtil.class);

    /**
     * 可以处理中文乱码，
     */
    public static String postXml(String url, String json,String sign) {
        StringBuilder sb = new StringBuilder();
        HttpPost httpPost = new HttpPost(url+sign);
        HttpEntity entity = null;
        try {
            if(json.equals(new String(json.getBytes("iso8859-1"), "iso8859-1")))
            {
                json=new String(json.getBytes("iso8859-1"),"utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
        try {

            HttpClient client = new DefaultHttpClient();
            StringEntity payload = new StringEntity(json, "UTF-8");
            httpPost.setEntity(payload);
            HttpResponse response = client.execute(httpPost);
            entity = response.getEntity();
            String text;
            if (entity != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                while ((text = bufferedReader.readLine()) != null) {
                    sb.append(text);
                }

            }
        } catch (Exception e) {
            logger.error("与[" + url + "]通信过程中发生异常,堆栈信息如下", e.getCause());
        } finally {
            try {
                EntityUtils.consume(entity);
            } catch (IOException ex) {
                ex.printStackTrace();
                logger.error("net io exception");
            }
        }
        return sb.toString();
    }

}
