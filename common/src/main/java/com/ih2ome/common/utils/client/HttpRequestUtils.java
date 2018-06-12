package com.ih2ome.common.utils.client;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author 李世成
 * @email: lishicheng@innjia.com
 * @date: 2017/8/28
 */
public class HttpRequestUtils {
    public static String put(String url) {
        HttpPut httpPut = new HttpPut(url);
        return excute(httpPut);
    }

    public static String get(String url) {
        HttpGet httpGet = new HttpGet(url);
        return excute(httpGet);
    }

    public static String getSSL(String url) {
        HttpGet httpGet = new HttpGet(url);
        return excuteSSL(httpGet);
    }

    public static String get(String url, Map<String, String> params) {
        HttpGet httpGet = new HttpGet(url);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            httpGet.addHeader(entry.getKey(), entry.getValue());
        }
        return excute(httpGet);
    }

    public static String post(String url) {
        HttpPost httpPost = new HttpPost(url);
        return excute(httpPost);
    }

    public static String post(String url, String data) {
        StringEntity entity = new StringEntity(data, Charset.defaultCharset());
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);
        return excute(httpPost);
    }

    public static String postJson(String url, String jsonData) {
        StringEntity entity = new StringEntity(jsonData, Charset.defaultCharset());
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setEntity(entity);
        return excute(httpPost);
    }

    public static String postSSL(String url, Map<String, Object> params) {
        List<NameValuePair> list = new ArrayList<>();
        for (Map.Entry<String, Object> p : params.entrySet()) {
            list.add(new BasicNameValuePair(p.getKey(), ObjectUtils.toString(p.getValue(), "")));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, Charset.forName("utf-8"));
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);
        return excuteSSL(httpPost);
    }

    public static String post(String url, Map<String, Object> params) {
        List<NameValuePair> list = new ArrayList<>();
        for (Map.Entry<String, Object> p : params.entrySet()) {
            list.add(new BasicNameValuePair(p.getKey(), ObjectUtils.toString(p.getValue(), "")));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, Charset.forName("utf-8"));
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);
        return excute(httpPost);
    }

    public static String put(String url, Map<String, Object> params) {
        HttpPut httpPut = new HttpPut(url);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            httpPut.addHeader(entry.getKey(), ObjectUtils.toString(entry.getValue()));
        }
        return excute(httpPut);
    }

    private static String excute(HttpRequestBase http) {
        HttpEntity entity = null;
        try {
            HttpResponse resp = ClientHttp.createSyncClient().execute(http);
            entity = resp.getEntity();

            InputStreamReader reader = new InputStreamReader(entity.getContent(), "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            StringBuffer sbs = new StringBuffer();
            String str = null;
            while ((str = br.readLine()) != null) {
                sbs.append(str);
            }
            return ObjectUtils.toString(sbs);
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            EntityUtils.consumeQuietly(entity);
        }
    }

    private static String excuteSSL(HttpRequestBase http) {
        HttpEntity entity = null;
        try {
            HttpResponse resp = ClientHttp.createSyncSSLClientDefault().execute(http);
            entity = resp.getEntity();

            InputStreamReader reader = new InputStreamReader(entity.getContent(), "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            StringBuffer sbs = new StringBuffer();
            String str = null;
            while ((str = br.readLine()) != null) {
                sbs.append(str);
            }
            return ObjectUtils.toString(sbs);
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            EntityUtils.consumeQuietly(entity);
        }
    }
}
