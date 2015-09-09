package com.wu.cacu;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/** httpClient 工具类
 * Created by xbwuc on 2015/9/7.
 */
public class HttpClientUtil {
    static String Url ="http://web.36wu.com/MobileService.asmx?op=GetMobileOwnership?mobile=18523949152&authkey=80730114c0354fe5ac8308921c69229d&format=json";
    private static CloseableHttpClient httpClient =null;
    private static CloseableHttpResponse httpResponse=null;
    /**
     * httpget 用户向web服务器发送get请求
     * @param url 服务器地址
     */
    public static void httpGet(String url){
        httpClient =HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
//        GetMethod
        try {
            httpResponse =httpClient.execute(get);
            HttpEntity entity = httpResponse.getEntity();
            System.out.println(httpResponse.getStatusLine()); //打印状态码
            if (entity != null) {
                System.out.print(entity.getContentLength());
                // 打印响应内容
                System.out.println("Response content: " + EntityUtils.toString(entity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        System.out.print();
    }
    public static void main(String [] args){
//        httpGet(Url);
        httpGet("http://www.weather.com.cn/data/cityinfo/101040100.html"); //天气查询接口
    }
}
