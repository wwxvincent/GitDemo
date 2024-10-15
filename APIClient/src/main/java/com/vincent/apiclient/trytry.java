package com.vincent.apiclient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;

public class trytry {
    public static void main(String[] args) {


        // 确保股票代码以 "0." 开头
        String secid = "0." + "300059";

        // 构建 URL
        String urlString = "http://73.push2delay.eastmoney.com/api/qt/stock/details/sse?fields1=f1,f2,f3,f4&fields2=f51,f52,f53,f54,f55&mpi=2000&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&pos=-0&secid=" + secid + "&wbp2u=%7C0%7C0%7C0%7Cweb";

        try {
            // 创建 HttpClient 实例
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10)) // 设置连接超时
                    .build();

            // 创建 HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .timeout(Duration.ofSeconds(10)) // 设置响应超时
                    .GET() // GET 请求
                    .build();

            // 发送请求并获取响应
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 打印返回的数据
            System.out.println("股票信息: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
