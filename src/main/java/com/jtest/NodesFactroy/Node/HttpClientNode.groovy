package com.jtest.NodesFactroy.Node

import com.jtest.NodesFactroy.NodeConfig.NodeConfig
import com.google.gson.Gson
import com.jtest.testframe.ITestImpl
import com.jtest.utility.THttpClientUtil
import org.apache.http.HttpEntity
import org.apache.http.entity.ContentType
import org.springframework.web.client.RestTemplate

/**
 * Created by Administrator on 2016-04-02.
 */
class HttpClientNode extends NodeConfig {
    //String name;
    //String type;
    RestTemplate restTemplate = new RestTemplate();
    String url;
    String contentType;
    String data;
    transient Map<String,String> header=new HashMap<>();
      public void setHeader(String key,String value){
        header.put(key,value);
        httpClientUtil.setHeader();
    }
    public void clearHeader(String key,String value){
        header.clear();//(key,value);
        httpClientUtil.clearHeader();
    }

    transient THttpClientUtil httpClientUtil = new THttpClientUtil();
    transient ITestImpl testimpl = new ITestImpl();

    public byte[] postForObject(String url, String data) {
        return restTemplate.postForObject(url, data, byte[].class);
    }

    public String get(String url) {
        httpClientUtil.get(url, contentType);
    }

    public String get(String url, String contentType) {
        httpClientUtil.get(url, contentType);
    }

    public String get(Map<String, String> mapHeader, String url, String contentType) {
        httpClientUtil.get(mapHeader, url, contentType);
    }

    public String post(String url, String data, String mt) {
        httpClientUtil.post(url, data, mt);
    }
    public String postHeader(Map<String, String> mapHeader, String url, String data,
                        String mt) {
        httpClientUtil.post(mapHeader, url, data, mt);
    }

    public String post(Map<String, String> mapHeader, String url, String data, String mt) {
        httpClientUtil.post(mapHeader, url, data, mt);
    }

    public String post(String url, byte[] data, ContentType ct) {
        httpClientUtil.post(url, data, ct);
    }

    public String post(String url, HttpEntity entity) {
        httpClientUtil.post(url, entity);
    }


    public String post() {
        httpClientUtil.post(url, data, contentType);
    }

    public String rest(String url, String data, String mt) {
        httpClientUtil = new THttpClientUtil(url);
        httpClientUtil.rest(url, data, mt);
    }


    public void checkStatusCode(int exp) {
        testimpl.checkEQ(200, httpClientUtil.getStatusCode());
    }

    String toString() {
        return new Gson().toJson(this);
    }
}
