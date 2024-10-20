package com.vincent.apiclient.doris.directDeal;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 10/18/24
 * @Description: use for catch data from different url by given
 */
public class PullAllData {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(getAllStockData());
        System.out.println();
    }
    public static List<String> getAllStockData() {
        // 定义 API URL
        String url2 = "http://8.push2delay.eastmoney.com/api/qt/clist/get?cb=jQuery112403408140944676902_1605794350039&pn=1&pz=6000&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:0+t:6,m:0+t:13,m:0+t:80,m:1+t:2,m:1+t:23&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f22,f11,f62,f128,f136,f115,f152&_=1605794350063";
        try {
            // 创建 HttpClient 实例
            HttpClient client = HttpClient.newHttpClient();

            // 创建 HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url2))
                    .GET() // GET 请求
                    .build();

            // 发送请求并获取响应
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // 获取响应内容
            String responseBody = response.body();


            // 去除 JSONP 包裹
            String jsonString = responseBody.substring(responseBody.indexOf("(") + 1, responseBody.lastIndexOf(")"));

            // 解析 JSON 数据并提取 `data` 部分
            org.json.JSONObject jsonObject = new org.json.JSONObject(jsonString);
            org.json.JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONArray diffArray = dataObject.getJSONArray("diff"); // 获取 diff 数组
            List<String> result = new ArrayList<>();
            for (int i = 0; i < diffArray.length(); i++) {
                JSONObject entity = diffArray.getJSONObject(i);
                String code = entity.getString("f12");
                String market = entity.getInt("f13")+"";
                result.add(market+"."+code);
//                System.out.println(result.get(i));
            }
            return result;
//
//            // 创建一个新的 JSON 对象用于存储 diff 数组
//            org.json.JSONObject resultObject = new JSONObject();
//            resultObject.put("diff", diffArray); // 将 diff 数组添加到新的 JSON 对象中
//
//            // 将结果 JSON 对象转换为字符串
//            String resultJsonString = resultObject.toString(4); // 4 为缩进级别
//
//            String result = resultJsonString.replaceFirst("^\\{\\s*\"diff\":\\s*\\[", "[").replaceFirst("\\]\\s*\\}$", "]");
//
//            return result;




        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
