package cn.csix.common.utils.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

/**
 * @Author: xjd
 * @Date: 2020/5/26 15:12
 * @Description:
 **/
public class CommonHttpUtils {

    public static String post(String url, NameValuePair[] params) throws IOException {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setContentCharset("UTF8");
        postMethod.addParameters(params);
        httpClient.executeMethod(postMethod);
        String responseBody = postMethod.getResponseBodyAsString();
        postMethod.releaseConnection();
        return responseBody;
    }

    public static String get(String url) throws IOException {
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setContentCharset("UTF8");
        httpClient.executeMethod(getMethod);
        String responseBody = getMethod.getResponseBodyAsString();
        getMethod.releaseConnection();
        return responseBody;
    }
}
