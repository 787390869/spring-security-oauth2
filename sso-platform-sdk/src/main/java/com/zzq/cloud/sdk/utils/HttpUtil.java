package com.zzq.cloud.sdk.utils;

import com.alibaba.fastjson.JSON;
import com.zzq.cloud.sdk.exception.PlatformSdkException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;

import java.nio.charset.StandardCharsets;

/**
 * HttpUtils
 *
 * @author lry
 */
@Slf4j
public class HttpUtil {

    private static final int HTTP_RESPONSE_CODE_OK = 200;
    private static final String CONTENT_TYPE_KEY = "Content-Type";
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    /**
     * 网络请求
     *
     * @param url url
     * @return response body
     */
    public static String sendGet(String url) {
        try {
            Connection.Response response = Jsoup.connect(url).ignoreContentType(true).method(Connection.Method.GET).execute();
            if (HTTP_RESPONSE_CODE_OK == response.statusCode()) {
                return response.charset(StandardCharsets.UTF_8.name()).body();
            }

            throw new RuntimeException("Request net failure: " + response.statusCode() + "=" + response.statusMessage());
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new PlatformSdkException(0, "Request exception: " + e.getMessage(), e);
        }
    }

    /**
     * 网络请求
     *
     * @param url url
     * @return response body
     */
    public static String sendPost(String url, Object body) {
        try {
            Connection connection = HttpConnection.connect(url).ignoreContentType(true);
            Connection.Request request = connection.request();
            request.header(CONTENT_TYPE_KEY, CONTENT_TYPE);
            request.postDataCharset(StandardCharsets.UTF_8.name());
            request.method(Connection.Method.POST);
            request.ignoreContentType(true);
            request.ignoreHttpErrors(true);
            request.requestBody(JSON.toJSONString(body));
            Connection.Response response = connection.execute();
            if (HTTP_RESPONSE_CODE_OK != response.statusCode()) {
                throw new PlatformSdkException("Request net failure: " + response.statusCode() + "=" + response.statusMessage());
            }

            return response.charset(StandardCharsets.UTF_8.name()).body();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new PlatformSdkException(0, "Request exception: " + e.getMessage(), e);
        }
    }

}
