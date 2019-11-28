package com.course.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpClient {

    @Test
    public void test1() throws IOException {
        //用于存放我们的结果
        String result;
        //创建一个get请求用于访问当前的百度地址
        HttpGet get=new HttpGet("http://www.baidu.com");
        //创建一个Http客户端，用于访问HttpServer端
        HttpClient client=new DefaultHttpClient();
        //通过当前客户端执行当前的get请求，并获得相应结果
        HttpResponse response=client.execute(get);
        result=EntityUtils.toString(response.getEntity(),"UTF-8");
        System.out.println(result);
    }

    @Test
    public void test2() throws IOException {
        String result;
        HttpGet get=new HttpGet("http://www.baidu.com");
        HttpClient client=new DefaultHttpClient();
        HttpResponse response=client.execute(get);
        result=EntityUtils.toString(response.getEntity());
        System.out.println(result);
        }
    }

